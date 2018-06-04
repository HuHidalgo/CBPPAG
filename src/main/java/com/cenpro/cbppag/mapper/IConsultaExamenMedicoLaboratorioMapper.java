package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaLaboratorioAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoLaboratorio;

public interface IConsultaExamenMedicoLaboratorioMapper
{
    public List<ConsultaLaboratorioAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoLaboratorio criterioBusquedaAdministrativaExamenMedicoLaboratorio);
}