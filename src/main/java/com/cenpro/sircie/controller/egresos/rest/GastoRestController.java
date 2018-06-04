package com.cenpro.sircie.controller.egresos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.model.egresos.Gastos;
import com.cenpro.sircie.service.IGastoService;

@RequestMapping("/egresos/gastos")
public @RestController class GastoRestController {
	
	private @Autowired IGastoService gastoService;
	
	//@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarTodos")
	public List<Gastos> buscarTodos(){
		
		return gastoService.buscarTodos();
	}
	
	//@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
	@PostMapping
	public ResponseEntity<?> registrarGasto(@RequestBody Gastos gastos){
		int idGastos = gastoService.registrarGastos(gastos);
		return ResponseEntity.ok(gastoService.buscarPorId(idGastos));
	}
	
	//@Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
	@PutMapping
	public ResponseEntity<?> actualizarGasto(@RequestBody Gastos gastos){
		gastoService.actualizarGastos(gastos);
		return ResponseEntity.ok(gastoService.buscarPorId(gastos.getIdGasto()));
	}
}
