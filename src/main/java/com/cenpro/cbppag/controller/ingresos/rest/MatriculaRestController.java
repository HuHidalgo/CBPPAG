package com.cenpro.cbppag.controller.ingresos.rest;

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

@RequestMapping("/ingresos/matricula")
public @RestController class MatriculaRestController {
	private @Autowired IAlertaService alertaService;
	private @Autowired IMatriculaService matriculaService;
	private HiloCorreo correo;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Matricula> buscarTodos()
    {
        return matriculaService.buscarTodos();
    }
	
	@GetMapping("/{codAlumno}")
    public List<Matricula> verificarAlumno(@PathVariable String codAlumno)
    {
        return matriculaService.buscarAlumno(codAlumno);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarMatricula(@RequestBody Matricula matricula)
    {
        String codigoMatricula = matriculaService.registrarMatricula(matricula);
        
        correo = new HiloCorreo(alertaService, matricula, null);
        correo.start();
        
        return ResponseEntity.ok(matriculaService.buscarPorId(codigoMatricula));
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
		Matricula matricula = Matricula.builder().voucher(archivo).build();
		matriculaService.cargarVoucher(matricula);
	}
	
	@GetMapping("/voucher/{codigoMatricula}")
    public Matricula recuperarVoucher(@PathVariable String codigoMatricula)
    {
		List<Matricula> lista = matriculaService.recuperarVoucher(codigoMatricula);
		String base64Encoded = DatatypeConverter.printBase64Binary(lista.get(0).getBytesLeidos());
        byte[] base64Decoded = DatatypeConverter.parseBase64Binary(base64Encoded);
		lista.get(0).setBytesLeidos(base64Decoded);
        return lista.get(0);
    }
	
	@PutMapping
	public ResponseEntity<?> actualizarMatricula(@RequestBody Matricula matricula){
		matriculaService.actualizarMatricula(matricula);
		return ResponseEntity.ok(matriculaService.buscarPorId(matricula.getCodigoMatricula()));
	}
}