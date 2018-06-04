package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;

public interface IConsultaExamenMedicoOdontologicoService
{
    public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico);
}