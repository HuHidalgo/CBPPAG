package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaLaboratorioAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoLaboratorio;

public interface IConsultaExamenMedicoLaboratorioService
{
    public List<ConsultaLaboratorioAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoLaboratorio criterioBusquedaAdministrativaExamenMedicoLaboratorio);
}