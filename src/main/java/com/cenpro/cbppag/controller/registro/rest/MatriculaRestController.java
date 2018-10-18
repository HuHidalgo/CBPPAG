package com.cenpro.cbppag.controller.registro.rest;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Blob;
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
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.service.IMatriculaService;
import com.cenpro.cbppag.utilitario.HiloCorreo;

@RequestMapping("/registro/matricula")
public @RestController class MatriculaRestController {
	private @Autowired IAlertaService alertaService;
	private @Autowired IMatriculaService matriculaService;
	private HiloCorreo correo;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Matricula> buscarTodos()
    {
		return matriculaService.buscarTodos();
    }
	
	@GetMapping("/{tipoDoc}/{codAlumno}")
    public List<Matricula> verificarAlumno(@PathVariable String tipoDoc, @PathVariable String codAlumno)
    {
        return matriculaService.buscarAlumno(tipoDoc, codAlumno);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarMatricula(@RequestBody Matricula matricula)
    {
        Integer idMatricula = matriculaService.registrarMatricula(matricula);
        
        correo = new HiloCorreo(alertaService, matricula, null);
        correo.start();
        
        return ResponseEntity.ok(matriculaService.buscarPorId(idMatricula));
    }
	
	@PutMapping
	public ResponseEntity<?> actualizarMatricula(@RequestBody Matricula matricula){
		matriculaService.actualizarMatricula(matricula);
		return ResponseEntity.ok(matriculaService.buscarPorId(matricula.getIdMatricula()));
	}
	
	@PostMapping(value = "/uploadfile/", params = "accion=cargar")
    public void cargarVoucher(@RequestParam("uploadfile") MultipartFile file) {
		Blob archivo = null;
		try {			
			if(file.getBytes().length!=0) {
				archivo = new SerialBlob(file.getBytes());
				Matricula matricula = Matricula.builder().voucher(archivo).build();
				matriculaService.cargarVoucher(matricula);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/voucher/{idMatricula}")
    public Matricula recuperarVoucher(@PathVariable Integer idMatricula)
    {
		List<Matricula> lista = matriculaService.recuperarVoucher(idMatricula);
		String base64Encoded = DatatypeConverter.printBase64Binary(lista.get(0).getBytesLeidos());
        byte[] base64Decoded = DatatypeConverter.parseBase64Binary(base64Encoded);
		lista.get(0).setBytesLeidos(base64Decoded);
        return lista.get(0);
    }
	
	@PostMapping(value = "/uploadfile/{idMatricula}", params = "accion=actualizar")
    public void actualizarVoucher(@RequestParam("uploadfile") MultipartFile file, @PathVariable Integer idMatricula) {
		if(file!=null) {
			Blob archivo = null;
			try {			
				if(file.getBytes().length!=0) {
					archivo = new SerialBlob(file.getBytes());
					Matricula matricula = Matricula.builder().voucher(archivo).build();
					matricula.setIdMatricula(idMatricula);
					matriculaService.actualizarVoucher(matricula);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}