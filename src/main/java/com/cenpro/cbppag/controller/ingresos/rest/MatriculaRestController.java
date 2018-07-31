package com.cenpro.cbppag.controller.ingresos.rest;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.service.IMatriculaService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.EnviarCorreoUtil;

@RequestMapping("/ingresos/matricula")
public @RestController class MatriculaRestController {
	private @Autowired IAlertaService alertaService;
	private @Autowired IMatriculaService matriculaService;
	private final EnviarCorreoUtil enviar = new EnviarCorreoUtil();
	
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
		System.out.println("Nombre : "+matricula.getVoucher());
		/*
		try {
			//matricula.setPdf(new File(matricula.getNombreArchivo()));
			matricula.setArchivo(new FileInputStream(matricula.getPdf()));
			byte[] fileContent = new byte[(int) matricula.getPdf().length()];
			matricula.getArchivo().read(fileContent);			
			matricula.getVoucher().setBytes(matricula.getPdf().length(), fileContent);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Blob archivo;
		try {
			System.out.println("longitud2 : "+matricula.getBytesLeidos());
			archivo = new SerialBlob(matricula.getBytesLeidos());
			matricula.setVoucher(archivo);
			
			System.out.println("longitud : "+matricula.getVoucher().length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
        matriculaService.registrarMatricula(matricula);
        
        /*enviar.setAlerta(alertaService);
        enviar.mensajeRegistroMatricula(matricula);*/
		return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }
	
}