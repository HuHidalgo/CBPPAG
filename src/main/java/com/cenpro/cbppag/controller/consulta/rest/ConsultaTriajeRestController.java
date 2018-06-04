package com.cenpro.cbppag.controller.consulta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaTriaje;
import com.cenpro.cbppag.model.movimiento.ExamenMedicoTriaje;
import com.cenpro.cbppag.service.IConsultaTriajeService;
import com.cenpro.cbppag.service.IExamenMedicoTriajeService;

@RequestMapping("/consulta/triaje")
public @RestController class ConsultaTriajeRestController
{
    private @Autowired IConsultaTriajeService consultaTriajeService;
    private @Autowired IExamenMedicoTriajeService examenMedicoTriajeService;

    @GetMapping(value = "/administrativa", params = "accion=buscarPorCriterioBusquedaAdministrativa")
    public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaTriaje criterioBusquedaAdministrativaTriaje)
    {
        return consultaTriajeService.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaTriaje);
    }
    
    @GetMapping(params = "accion=buscarResultadoRegularPorNumeroRegistro")
    public List<ExamenMedicoTriaje> buscarResultadoRegularPorNumeroRegistro(CriterioBusquedaAdministrativaTriaje criterioBusqueda){
        return examenMedicoTriajeService.buscarResultadoRegularPorNumeroRegistro(criterioBusqueda.getNumeroRegistro());
    }

}