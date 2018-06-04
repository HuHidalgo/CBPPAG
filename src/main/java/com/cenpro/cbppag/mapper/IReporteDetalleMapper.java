package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.cenpro.cbppag.model.reporte.ReporteDetalleResultadoAlumnoRegular;

public interface IReporteDetalleMapper
{
    public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
            CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno);
}