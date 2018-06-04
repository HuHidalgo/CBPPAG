package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.cenpro.cbppag.model.reporte.ReporteExamenMedicoRegular;

public interface IReporteExamenMedicoMapper
{
    public List<ReporteExamenMedicoRegular> buscarReporteExamenMedicoRegular(
            CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico);
}