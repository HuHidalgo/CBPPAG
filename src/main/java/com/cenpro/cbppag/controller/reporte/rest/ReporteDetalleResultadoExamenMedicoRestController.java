package com.cenpro.cbppag.controller.reporte.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.cenpro.cbppag.model.reporte.ReporteDetalleResultadoAlumnoRegular;
import com.cenpro.cbppag.service.IReporteDetalleService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ValidatorUtil;

@RequestMapping("/reporte/detalle")
public @RestController class ReporteDetalleResultadoExamenMedicoRestController
{
    private @Autowired IReporteDetalleService reporteDetalleService;

    @GetMapping(value = "/resultado/examenMedico/regular", params = "accion=buscar")
    public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
            @Validated CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        return reporteDetalleService
                .buscarResultadoExamenMedicoRegular(criterioBusquedaReporteDetalleResultadoAlumno);
    }
}