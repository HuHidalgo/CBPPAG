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
import com.cenpro.sircie.aspecto.enumeracion.Dato;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;
import com.cenpro.sircie.model.egresos.Egresos;
import com.cenpro.sircie.model.egresos.ProgramacionGastos;
import com.cenpro.sircie.service.IMultiTabDetService;
import com.cenpro.sircie.service.IRegistroEgresosService;

@Audit(tipo = Tipo.Ingresos, datos = Dato.Ingresos)
@RequestMapping("/egresos/egresos")
public @RestController class RegistroEgresosRestController {
	
	private @Autowired IRegistroEgresosService egresosService;
		
	@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarTodos")
	public List<Egresos> buscarTodos(){
		return egresosService.buscarTodos();
	}
	
	@GetMapping("/detalle/{idProgramacionGasto}")
    public List<Egresos> buscarPorIdProgramacionGasto(@PathVariable int idProgramacionGasto)
    {
        return egresosService.buscarPorIdProgramacionGasto(idProgramacionGasto);
    }
	
	@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
	@PostMapping
	public ResponseEntity<?> registrarEgresos(@RequestBody Egresos egresos){
		int idEgreso = egresosService.registrarEgresos(egresos);
		return ResponseEntity.ok(egresosService.buscarPorId(idEgreso));
	}
	
	@Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
	@PutMapping
	public ResponseEntity<?> actualizarEgresos(@RequestBody Egresos egresos){
		egresosService.actualizarEgresos(egresos);
		return ResponseEntity.ok(egresosService.buscarPorId(egresos.getIdEgreso()));
	}

}
