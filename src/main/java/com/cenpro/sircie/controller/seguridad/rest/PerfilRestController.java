package com.cenpro.sircie.controller.seguridad.rest;

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

import com.cenpro.sircie.model.seguridad.Perfil;
import com.cenpro.sircie.service.IPerfilService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/seguridad/perfil")
public @RestController class PerfilRestController {
	
	private @Autowired IPerfilService perfilService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Perfil> buscarTodos()
    {
        return perfilService.buscarTodos();
    }
	
	@PostMapping
    public ResponseEntity<?> registrarPerfil(@RequestBody Perfil perfil)
    {
        perfilService.registrarPerfil(perfil);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }

    @PutMapping
    public ResponseEntity<?> actualizarPerfil(@RequestBody Perfil perfil)
    {
        perfilService.actualizarPerfil(perfil);
        return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarMeta(@RequestBody Perfil perfil)
    {
    	perfilService.eliminarPerfil(perfil);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
