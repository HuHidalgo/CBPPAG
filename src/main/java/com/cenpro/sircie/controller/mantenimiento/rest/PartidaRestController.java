package com.cenpro.sircie.controller.mantenimiento.rest;

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

import com.cenpro.sircie.model.mantenimiento.Partida;
import com.cenpro.sircie.service.IPartidaService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/partida")
public @RestController class PartidaRestController 
{

	private @Autowired IPartidaService partidaService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Partida> buscarTodos()
    {
        return partidaService.buscarTodos();
    }

	@GetMapping("/tarea/{codigoTarea}")
    public List<Partida> buscarPorCodigoTarea(@PathVariable Integer codigoTarea)
    {
        return partidaService.buscarPorCodigoTarea(codigoTarea);
    }
	
    @PostMapping
    public ResponseEntity<?> registrarPartida(@RequestBody Partida partida)
    {
    	partidaService.registrarPartida(partida);
        return ResponseEntity.ok(partidaService.buscarPorCodigoTarea(partida.getCodigoTarea()));
    }

    @PutMapping
    public ResponseEntity<?> actualizarPartida(@RequestBody Partida partida)
    {
    	partidaService.actualizarPartida(partida);
        return ResponseEntity.ok(partidaService.buscarPorCodigoTarea(partida.getCodigoTarea()));
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarPartida(@RequestBody Partida partida)
    {
    	partidaService.eliminarPartida(partida);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
