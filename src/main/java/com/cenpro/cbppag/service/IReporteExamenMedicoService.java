package com.cenpro.cbppag.service;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.cenpro.cbppag.model.reporte.ReporteExamenMedicoRegular;

public interface IReporteExamenMedicoService
{
    public ReporteExamenMedicoRegular buscarReporteExamenMedicoRegular(
            CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico);
}