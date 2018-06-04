package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaPsicologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoPsicologico;

public interface IConsultaExamenMedicoPsicologicoService
{
    public List<ConsultaPsicologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoPsicologico criterioBusquedaAdministrativaExamenMedicoPsicologico);
}