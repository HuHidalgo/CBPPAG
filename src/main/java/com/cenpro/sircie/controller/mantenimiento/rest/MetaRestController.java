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

import com.cenpro.sircie.model.mantenimiento.Meta;
import com.cenpro.sircie.service.IMetaService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/meta")
public @RestController class MetaRestController 
{
	private @Autowired IMetaService metaService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Meta> buscarTodos()
    {
        return metaService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> registrarMeta(@RequestBody Meta meta)
    {
        metaService.registrarMeta(meta);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }

    @PutMapping
    public ResponseEntity<?> actualizarMeta(@RequestBody Meta meta)
    {
        metaService.actualizarMeta(meta);
        return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarMeta(@RequestBody Meta meta)
    {
    	metaService.eliminarMeta(meta);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
