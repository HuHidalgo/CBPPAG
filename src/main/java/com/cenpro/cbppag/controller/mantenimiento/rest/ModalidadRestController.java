package com.cenpro.cbppag.controller.mantenimiento.rest;

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

import com.cenpro.cbppag.model.mantenimiento.Modalidad;
import com.cenpro.cbppag.service.IModalidadService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/modalidad")
public @RestController class ModalidadRestController {
	
	private @Autowired IModalidadService modalidadService;

    @GetMapping(params = "accion=buscarTodos")
    public List<Modalidad> buscarTodos()
    {
        return modalidadService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> registrarModalidad(@RequestBody Modalidad modalidad)
    {
        String idModalidad = modalidadService.registrarModalidad(modalidad);
        return ResponseEntity.ok(modalidadService.buscarPorId(idModalidad));
    }

    @PutMapping
    public ResponseEntity<?> actualizarModalidad(@RequestBody Modalidad modalidad)
    {
        modalidadService.actualizarModalidad(modalidad);
        return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarModalidad(@RequestBody Modalidad modalidad)
    {
        modalidadService.eliminarModalidad(modalidad);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }

}
