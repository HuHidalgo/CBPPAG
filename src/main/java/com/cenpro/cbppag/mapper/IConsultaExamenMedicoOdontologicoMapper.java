package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;

public interface IConsultaExamenMedicoOdontologicoMapper
{
    public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico);
}