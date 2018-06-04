package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaTriaje;

public interface IConsultaTriajeService
{
    public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaTriaje criterioBusquedaAdministrativaTriaje);
}