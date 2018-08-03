package com.cenpro.cbppag.controller.ingresos.rest;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenpro.cbppag.model.registro.Pago;
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.service.IPagoService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.HiloCorreo;

@RequestMapping("/ingresos/pago")
public @RestController class PagoRestController {
	private @Autowired IAlertaService alertaService;
	private @Autowired IPagoService pagoService;
	private HiloCorreo correo;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Pago> buscarTodos()
    {
        return pagoService.buscarTodos();
    }
	
	@GetMapping("/{codAlumno}")
    public List<Pago> verificarAlumno(@PathVariable String codAlumno)
    {
        return pagoService.buscarAlumno(codAlumno);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarPago(@RequestBody Pago pago)
    {
		pago.setMontoPagado(pago.getNroCuotasAPagar()*pago.getCostoCuota());
		pagoService.registrarPago(pago);
		
		correo = new HiloCorreo(alertaService, null, pago);
        correo.start();
        
		return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }
	
	@PostMapping(value = "/uploadfile/", params = "accion=cargar")
    public void cargarVoucher(@RequestParam("uploadfile") MultipartFile file) {
		Blob archivo = null;
		try {			
			archivo = new SerialBlob(file.getBytes());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pago pago = Pago.builder().voucher(archivo).build();
		pagoService.registrarPago(pago);
	}
	
	@GetMapping("/voucher/{codAlumno}")
    public List<Pago> recuperarVoucher(@PathVariable String codAlumno)
    {
		List<Pago> f = pagoService.recuperarVoucher(codAlumno);
		System.out.println(f.size());
		
		if(f.get(0) == null) {
			System.out.println("nulo");
		}
		else {
			System.out.println("objeto");
		}
		
		
        return pagoService.recuperarVoucher(codAlumno);
    }
}
