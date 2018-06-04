package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaTriaje;

public interface IConsultaTriajeMapper
{
    public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaTriaje criterioBusquedaAdministrativaTriaje);
}