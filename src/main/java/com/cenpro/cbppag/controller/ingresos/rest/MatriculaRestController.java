package com.cenpro.cbppag.controller.ingresos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.service.IMatriculaService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;

@RequestMapping("/ingresos/matricula")
public @RestController class MatriculaRestController {
	
	private @Autowired IMatriculaService matriculaService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Matricula> buscarTodos()
    {
        return matriculaService.buscarTodos();
    }
	
	@GetMapping("/{codAlumno}")
    public List<Matricula> verificarAlumno(@PathVariable String documento)
    {
		System.out.println("DNI : "+documento);
        return matriculaService.buscarAlumno(documento);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarMatricula(@RequestBody Matricula matricula)
    {
		matriculaService.registrarMatricula(matricula);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }
	
}
