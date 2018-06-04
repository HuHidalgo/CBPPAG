package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.cenpro.cbppag.model.reporte.ReporteDetalleResultadoAlumnoRegular;

public interface IReporteDetalleService
{
    public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
            CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno);
}