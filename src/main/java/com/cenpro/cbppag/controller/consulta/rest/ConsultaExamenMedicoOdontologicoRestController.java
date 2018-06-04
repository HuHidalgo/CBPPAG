package com.cenpro.cbppag.controller.consulta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;
import com.cenpro.cbppag.model.movimiento.ExamenMedicoOdontologico;
import com.cenpro.cbppag.service.IConsultaExamenMedicoOdontologicoService;
import com.cenpro.cbppag.service.IExamenMedicoOdontologicoService;

@RequestMapping("/consulta/odontologia")
public @RestController class ConsultaExamenMedicoOdontologicoRestController
{
    private @Autowired IExamenMedicoOdontologicoService examenMedicoOdontologicoService;
    private @Autowired IConsultaExamenMedicoOdontologicoService consultaExamenMedicoOdontologicoService;

    @GetMapping(value = "/administrativa", params = "accion=buscarPorCriterioBusquedaAdministrativa")
    public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico)
    {
        return consultaExamenMedicoOdontologicoService.buscarPorCriterioBusquedaAdministrativa(
                criterioBusquedaAdministrativaExamenMedicoOdontologico);
    }

    @GetMapping(params = "accion=buscarResultadoRegularPorNumeroRegistroAnio")
    public List<ExamenMedicoOdontologico> buscarResultadoPorNumeroRegistro(
            CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusqueda)
    {
        return examenMedicoOdontologicoService.buscarResultadoPorNumeroRegistro(criterioBusqueda.getNumeroRegistro());
    }
}