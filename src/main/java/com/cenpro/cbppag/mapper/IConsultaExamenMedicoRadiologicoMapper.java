package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaRadiologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoRadiologico;

public interface IConsultaExamenMedicoRadiologicoMapper
{
    public List<ConsultaRadiologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoRadiologico criterioBusquedaAdministrativaExamenMedicoRadiologico);
}