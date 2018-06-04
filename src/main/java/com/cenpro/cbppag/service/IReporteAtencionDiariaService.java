package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.cenpro.cbppag.model.reporte.ReporteAtencionDiaria;

public interface IReporteAtencionDiariaService
{
    public List<ReporteAtencionDiaria> buscarAtencionesDiarias(
            CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria);
}