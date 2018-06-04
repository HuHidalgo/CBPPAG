package com.cenpro.sircie.controller.egresos.rest;

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
import com.cenpro.sircie.model.egresos.Egresos;
import com.cenpro.sircie.model.egresos.ProgramacionGastos;
import com.cenpro.sircie.model.mantenimiento.MultiTabDet;
import com.cenpro.sircie.service.IProgramacionGastoService;
import com.cenpro.sircie.service.IRegistroEgresosService;
import com.cenpro.sircie.validacion.IdTabla;

@RequestMapping("/egresos/planificacion/programacionGastos")
public @RestController class ActProgramacionGastoRestController {
	
	private @Autowired IProgramacionGastoService programacionGastoService;
	
	//@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarTodos")
	public List<ProgramacionGastos> buscarTodos(){
		
		return programacionGastoService.buscarTodos();
	}
	
}
