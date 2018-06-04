package com.cenpro.sircie.controller.ingresos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.aspecto.enumeracion.Dato;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;
import com.cenpro.sircie.model.ingresos.Ingresos;
import com.cenpro.sircie.service.IRegistroIngresoService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@Audit(tipo = Tipo.Ingresos, datos = Dato.Ingresos)
@RequestMapping("/ingresos/ingresos")
public @RestController class RegistroIngresoRestController {
	
	private @Autowired IRegistroIngresoService ingresosService;
		
	@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarTodos")
	public List<Ingresos> buscarTodos(){
		
		return ingresosService.buscarTodos();
	}
	
	@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
	@PostMapping
	public ResponseEntity<?> registrarIngresos(@RequestBody Ingresos ingresos){
		int idIngreso = ingresosService.registrarIngresos(ingresos);
		return ResponseEntity.ok(ingresosService.buscarPorId(idIngreso));
	}
	
	@Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
	@PutMapping
	public ResponseEntity<?> actualizarIngresos(@RequestBody Ingresos ingresos){
		ingresosService.actualizarIngresos(ingresos);
		return ResponseEntity.ok(ingresosService.buscarPorId(ingresos.getIdIngreso()));
	}

}
