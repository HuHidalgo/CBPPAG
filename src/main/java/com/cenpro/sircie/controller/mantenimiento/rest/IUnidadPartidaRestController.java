package com.cenpro.sircie.controller.mantenimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.model.mantenimiento.UnidadPartida;
import com.cenpro.sircie.service.IUnidadPartidaService;

@RequestMapping("/mantenimiento/partidas")
public  @RestController class IUnidadPartidaRestController {
	
	private @Autowired IUnidadPartidaService unidadPService;
	
	@GetMapping("/unidad/{codigoUnidad}")
    public List<UnidadPartida> buscarPartidas(@PathVariable String codigoUnidad)
    {
		System.out.println(codigoUnidad);
        return unidadPService.buscarPartidas(codigoUnidad);
    }
}
