package com.cenpro.cbppag.controller.mantenimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.mantenimiento.Especializacion;
import com.cenpro.cbppag.service.IEspecializacionService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/especializacion")
public @RestController class EspecializacionRestController 
{
	private @Autowired IEspecializacionService especializacionService;

	@GetMapping("/modalidad/{idModalidad}/{nroCiclo}")
    public List<Especializacion> buscarEspecializaciones(@PathVariable String idModalidad, @PathVariable Integer nroCiclo)
    {
        return especializacionService.buscarEspecializaciones(idModalidad, nroCiclo);
    }
	
	@GetMapping("/costo/{idEspecializacion}/{nroCiclo}")
    public List<Especializacion> buscarCosto(@PathVariable String idEspecializacion, @PathVariable Integer nroCiclo)
    {
        return especializacionService.buscarCosto(idEspecializacion, nroCiclo);
    }
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Especializacion> buscarTodos()
    {
        return especializacionService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> registrarEspecializacion(@RequestBody Especializacion especializacion)
    {	
        String idEspecializacion = especializacionService.registrarEspecializacion(especializacion);
        return ResponseEntity.ok(especializacionService.buscarPorId(idEspecializacion));
    }

    @PutMapping
    public ResponseEntity<?> actualizarEspecializacion(@RequestBody Especializacion especializacion)
    {
    	String idEspecializacion = especializacion.getIdEspecializacion();
        especializacionService.actualizarEspecializacion(especializacion);
        return ResponseEntity.ok(especializacionService.buscarPorId(idEspecializacion));
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarEspecializacion(@RequestBody Especializacion especializacion)
    {
    	especializacionService.eliminarEspecializacion(especializacion);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
