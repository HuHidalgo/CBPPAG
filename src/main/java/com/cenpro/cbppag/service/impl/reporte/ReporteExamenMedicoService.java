package com.cenpro.cbppag.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IReporteExamenMedicoMapper;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.cenpro.cbppag.model.reporte.ReporteExamenMedicoRegular;
import com.cenpro.cbppag.service.IReporteExamenMedicoService;
import com.cenpro.cbppag.service.excepcion.ValorNoEncontradoException;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;

@Service
public class ReporteExamenMedicoService implements IReporteExamenMedicoService
{
    private @Autowired IReporteExamenMedicoMapper reporteExamenMedicoMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ReporteExamenMedicoRegular buscarReporteExamenMedicoRegular(
            CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico)
    {
        List<ReporteExamenMedicoRegular> reportesExamenMedicoRegular = reporteExamenMedicoMapper
                .buscarReporteExamenMedicoRegular(criterioBusquedaReporteExamenMedico);
        if (reportesExamenMedicoRegular.isEmpty())
        {
            throw new ValorNoEncontradoException(
                    ConstantesExcepciones.NO_EXISTE_REPORTE_EXAMEN_MEDICO_REGULAR);
        }
        return reportesExamenMedicoRegular.get(0);
    }
}