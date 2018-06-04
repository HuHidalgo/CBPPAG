package com.cenpro.cbppag.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IReporteDetalleMapper;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.cenpro.cbppag.model.reporte.ReporteDetalleResultadoAlumnoRegular;
import com.cenpro.cbppag.service.IReporteDetalleService;

@Service
public class ReporteDetalleService implements IReporteDetalleService
{
    private @Autowired IReporteDetalleMapper reporteDetalleMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
            CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno)
    {
        return reporteDetalleMapper
                .buscarResultadoExamenMedicoRegular(criterioBusquedaReporteDetalleResultadoAlumno);
    }
}