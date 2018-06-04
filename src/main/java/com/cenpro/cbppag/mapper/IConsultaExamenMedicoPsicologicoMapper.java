package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.consulta.administrativa.ConsultaPsicologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoPsicologico;

public interface IConsultaExamenMedicoPsicologicoMapper
{
    public List<ConsultaPsicologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoPsicologico criterioBusquedaAdministrativaExamenMedicoPsicologico);
}