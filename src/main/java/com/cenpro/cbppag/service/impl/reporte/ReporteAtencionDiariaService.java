package com.cenpro.cbppag.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IReporteAtencionDiariaMapper;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.cenpro.cbppag.model.reporte.ReporteAtencionDiaria;
import com.cenpro.cbppag.service.IReporteAtencionDiariaService;
import com.cenpro.cbppag.utilitario.StringsUtils;

@Service
public class ReporteAtencionDiariaService implements IReporteAtencionDiariaService
{
    private @Autowired IReporteAtencionDiariaMapper reporteAtencionDiariaMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ReporteAtencionDiaria> buscarAtencionesDiarias(
            CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria)
    {
        String verbo = StringsUtils.concatenarCadena(
                criterioBusquedaReporteAtencionDiaria.getIdTipoExamenMedico(), "_",
                criterioBusquedaReporteAtencionDiaria.getTipoReporte());
        criterioBusquedaReporteAtencionDiaria.setVerbo(verbo);
        return reporteAtencionDiariaMapper.buscarAtencionesDiarias(criterioBusquedaReporteAtencionDiaria);
    }
}
