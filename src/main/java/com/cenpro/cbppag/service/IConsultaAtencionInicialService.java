package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.consulta.ConsultaAtencionInicial;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAtencionInicial;

public interface IConsultaAtencionInicialService
{
    public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialLaboratorioPorCriterioBusqueda(
            CriterioBusquedaAtencionInicial criterioBusqueda);

    public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialPsicologiaPorCriterioBusqueda(
            CriterioBusquedaAtencionInicial criterioBusqueda);

    public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialRadiologiaPorCriterioBusqueda(
            CriterioBusquedaAtencionInicial criterioBusqueda);

    public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialTriajePorCriterioBusqueda(
            CriterioBusquedaAtencionInicial criterioBusqueda);

    public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialOdontologiaPorCriterioBusqueda(
            CriterioBusquedaAtencionInicial criterioBusqueda);
    
    public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialMedicinaGeneralPorCriterioBusqueda(
            CriterioBusquedaAtencionInicial criterioBusqueda);
}