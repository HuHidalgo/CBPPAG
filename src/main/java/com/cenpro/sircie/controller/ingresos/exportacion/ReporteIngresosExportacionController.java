package com.cenpro.sircie.controller.ingresos.exportacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cenpro.sircie.controller.ingresos.rest.ReporteIngresoRestController;
import com.cenpro.sircie.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.sircie.model.ingresos.ReporteIngresos;
import com.cenpro.sircie.service.IReporteIngresosService;
import com.cenpro.sircie.utilitario.ReporteUtilYarg;

@RequestMapping("/ingresos/reporte")
public @Controller class ReporteIngresosExportacionController
{
    private @Autowired IReporteIngresosService reporteIngresosService;

    @GetMapping(params = "accion=exportar")
    public ModelAndView descargarReporteIngresos(ModelMap model, ModelAndView modelAndView,
            CriterioBusquedaIngresos criterioBusquedaIngresos)
    {
    	ReporteIngresoRestController rep = new ReporteIngresoRestController();
        // DETALLE
    	
        List<ReporteIngresos> atencionIngresosDetalle = rep.reporteDetalleIngresosConceptos();

        // GENERAL
     
        List<ReporteIngresos> atencionIngresosGeneral = rep.reporteGeneralIngresosConceptos();

        Map<String, Object> params = new HashMap<>();
        params.put("param1", atencionIngresosDetalle);
        params.put("param2", criterioBusquedaIngresos);
        params.put("param3", atencionIngresosGeneral);

        // HOJA DETALLE
        model.addAttribute("rb_titulo", ReporteUtilYarg.buildReportBand("Titulo"));
        model.addAttribute("rb_criterioBusqueda", ReporteUtilYarg.buildReportBand(
                "CriteriosBusqueda", "CriterioBusqueda", "parameter=param2 $", "json"));
        model.addAttribute("rb_total", ReporteUtilYarg.buildReportBand("Total"));
        model.addAttribute("rb_encabezado", ReporteUtilYarg.buildReportBand("Encabezado"));
        model.addAttribute("rb_datos",
                ReporteUtilYarg.buildReportBand("Datos", "Datos", "parameter=param1 $", "json"));

        // HOJA GENERAL
        model.addAttribute("rb_tituloGeneral", ReporteUtilYarg.buildReportBand("TituloGeneral"));
        model.addAttribute("rb_criterioBusquedaGeneral",
                ReporteUtilYarg.buildReportBand("CriteriosBusquedaGeneral",
                        "CriterioBusquedaGeneral", "parameter=param2 $", "json"));
        model.addAttribute("rb_totalGeneral", ReporteUtilYarg.buildReportBand("TotalGeneral"));
        model.addAttribute("rb_encabezadoGeneral",
                ReporteUtilYarg.buildReportBand("EncabezadoGeneral"));
        model.addAttribute("rb_datosGeneral", ReporteUtilYarg.buildReportBand("DatosGeneral",
                "DatosGeneral", "parameter=param3 $", "json"));

        model.addAttribute(ReporteUtilYarg.PARAM_TEMPLATE, "reporteIngresosConceptos");
        model.addAttribute(ReporteUtilYarg.PARAM_NOMBRE_REPORTE, "Reporte de Ingresos por Concepto");
        model.addAttribute(ReporteUtilYarg.PARAM_REPORTE_PARAMETERS, params);
        modelAndView = new ModelAndView("yargView", model);
        return modelAndView;
    }
}