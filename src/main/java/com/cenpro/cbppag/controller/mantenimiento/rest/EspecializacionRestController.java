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

	@GetMapping("/modalidad/{idModalidad}")
    public List<Especializacion> buscarModalidades(@PathVariable String idModalidad)
    {
        return especializacionService.buscarModalidad(idModalidad);
    }
	
	@GetMapping("/costo/{idEspecializacion}")
    public List<Especializacion> buscarCosto(@PathVariable String idEspecializacion)
    {
        return especializacionService.buscarCosto(idEspecializacion);
    }
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Especializacion> buscarTodos()
    {
        return especializacionService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> registrarEspecializacion(@RequestBody Especializacion especializacion)
    {	
        especializacionService.registrarEspecializacion(especializacion);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }

    @PutMapping
    public ResponseEntity<?> actualizarEspecializacion(@RequestBody Especializacion especializacion)
    {
        especializacionService.actualizarEspecializacion(especializacion);
        return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarEspecializacion(@RequestBody Especializacion especializacion)
    {
    	especializacionService.eliminarEspecializacion(especializacion);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
