package com.cenpro.cbppag.controller.registro.rest;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.model.registro.Perfeccionamiento;
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.service.IPerfeccionamientoService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.HiloCorreo;

@RequestMapping("/registro/perfeccionamiento")
public @RestController class PerfeccionamientoRestController {
	private @Autowired IAlertaService alertaService;
	private @Autowired IPerfeccionamientoService pagoService;
	private HiloCorreo correo;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Perfeccionamiento> buscarTodos()
    {
        return pagoService.buscarTodos();
    }
	
	@GetMapping("/{tipoDoc}/{codAlumno}")
    public List<Perfeccionamiento> verificarAlumno(@PathVariable String tipoDoc, @PathVariable String codAlumno)
    {
        return pagoService.buscarAlumno(tipoDoc, codAlumno);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarPerfeccionamiento(@RequestBody Perfeccionamiento pago)
    {
		int idPerfeccionamiento = pagoService.registrarPerfeccionamiento(pago);
		correo = new HiloCorreo(alertaService, null, pago);
        correo.start();
		
		return ResponseEntity.ok(pagoService.buscarPorId(idPerfeccionamiento));
    }
	
	@PostMapping(value = "/uploadfile/", params = "accion=cargar")
    public void cargarVoucher(@RequestParam("uploadfile") MultipartFile file) {
		Blob archivo = null;
		try {			
			if(file.getBytes().length!=0) {
				archivo = new SerialBlob(file.getBytes());
				Perfeccionamiento pago = Perfeccionamiento.builder().voucher(archivo).build();
				pagoService.registrarPerfeccionamiento(pago);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/voucher/{idPerfeccionamiento}")
    public Perfeccionamiento recuperarVoucher(@PathVariable Integer idPerfeccionamiento)
    {
		List<Perfeccionamiento> lista = pagoService.recuperarVoucher(idPerfeccionamiento);
		String base64Encoded = DatatypeConverter.printBase64Binary(lista.get(0).getBytesLeidos());
        byte[] base64Decoded = DatatypeConverter.parseBase64Binary(base64Encoded);
		lista.get(0).setBytesLeidos(base64Decoded);
        return lista.get(0);
    }
	
	@PutMapping
	public ResponseEntity<?> actualizarPerfeccionamiento(@RequestBody Perfeccionamiento pago){
		pagoService.actualizarPerfeccionamiento(pago);
		return ResponseEntity.ok(pagoService.buscarPorId(pago.getIdPerfeccionamiento()));
	}
	
	@PostMapping(value = "/uploadfile/{idPerfeccionamiento}", params = "accion=actualizar")
    public void actualizarVoucher(@RequestParam("uploadfile") MultipartFile file, @PathVariable Integer idPerfeccionamiento) {
		if(file!=null) {
			Blob archivo = null;
			try {			
				if(file.getBytes().length!=0) {
					archivo = new SerialBlob(file.getBytes());
					Perfeccionamiento pago = Perfeccionamiento.builder().voucher(archivo).build();
					pago.setIdPerfeccionamiento(idPerfeccionamiento);
					pagoService.registrarPerfeccionamiento(pago);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
