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

import com.cenpro.cbppag.model.mantenimiento.Alerta;
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/alerta")
public @RestController class AlertaRestController {
	
	private @Autowired IAlertaService alertaService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Alerta> buscarTodos()
    {
        return alertaService.buscarTodos();
    }

	@GetMapping("/{tipoAlerta}")
    public List<Alerta> verificarAlumno(@PathVariable String tipoAlerta)
    {
        return alertaService.buscarPorId(tipoAlerta);
    }
	
    @PostMapping
    public ResponseEntity<?> registrarAlerta(@RequestBody Alerta alerta)
    {	
    	alertaService.registrarAlerta(alerta);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }

    @PutMapping
    public ResponseEntity<?> actualizarAlerta(@RequestBody Alerta alerta)
    {
    	alertaService.actualizarAlerta(alerta);
        return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarAlerta(@RequestBody Alerta alerta)
    {
    	alertaService.eliminarAlerta(alerta);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
    
}
