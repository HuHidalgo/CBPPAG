package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaRadiologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoRadiologico;

public interface IConsultaExamenMedicoRadiologicoService
{
    public List<ConsultaRadiologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoRadiologico criterioBusquedaAdministrativaExamenMedicoRadiologico);
}