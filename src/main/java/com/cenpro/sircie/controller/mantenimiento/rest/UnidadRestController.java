package com.cenpro.sircie.controller.mantenimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.model.mantenimiento.Unidad;
import com.cenpro.sircie.service.IUnidadService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/unidad")
public @RestController class UnidadRestController {
	
	private @Autowired IUnidadService unidadService;

    @GetMapping(params = "accion=buscarTodos")
    public List<Unidad> buscarTodosT()
    {
        return unidadService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> registrarUnidad(@RequestBody Unidad unidad)
    {
        unidadService.registrarUnidad(unidad);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }

    @PutMapping
    public ResponseEntity<?> actualizarUnidad(@RequestBody Unidad unidad)
    {
        unidadService.actualizarUnidad(unidad);
        return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarUnidad(@RequestBody Unidad unidad)
    {
        unidadService.eliminarUnidad(unidad);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }

}
