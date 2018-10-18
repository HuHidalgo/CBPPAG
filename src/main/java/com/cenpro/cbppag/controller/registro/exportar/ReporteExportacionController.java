package com.cenpro.cbppag.controller.registro.exportar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cenpro.cbppag.model.reporte.ReporteDeuda;
import com.cenpro.cbppag.model.reporte.ReportePago;
import com.cenpro.cbppag.service.IReporteDeudaService;
import com.cenpro.cbppag.service.IReportePagoService;
import com.cenpro.cbppag.utilitario.ReporteUtilYarg;

@RequestMapping("/registro/reporte")
public @Controller class ReporteExportacionController
{
	private @Autowired IReporteDeudaService reporteDeudaService;
	private @Autowired IReportePagoService ReportePagoService;
	
    @GetMapping(params = "accion=exportar")
    public ModelAndView descargarReporteDeuda(ModelMap model, ModelAndView modelAndView,
    		ReporteDeuda reporte)
    {
        List<ReporteDeuda> reporteGeneral = reporteDeudaService.buscarDeudas(reporte);
        Map<String, Object> params = new HashMap<>();
        params.put("param1", reporteGeneral);
        params.put("param2", reporte);
        params.put("param3", reporteGeneral);

        // HOJA DE REPORTE
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
        
        model.addAttribute(ReporteUtilYarg.PARAM_TEMPLATE, "reporteDeuda");
        model.addAttribute(ReporteUtilYarg.PARAM_NOMBRE_REPORTE, "Reporte de Deudas");
        model.addAttribute(ReporteUtilYarg.PARAM_REPORTE_PARAMETERS, params);
        modelAndView = new ModelAndView("yargView", model);
        return modelAndView;
    }
    
    @GetMapping(params = "accion=exportar2")
    public ModelAndView descargarReportePago(ModelMap model, ModelAndView modelAndView,
    		ReportePago reporte)
    {
        List<ReportePago> reporteGeneral = ReportePagoService.buscarPagos(reporte);
        Map<String, Object> params = new HashMap<>();
        params.put("param1", reporteGeneral);
        params.put("param2", reporte);
        params.put("param3", reporteGeneral);

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

        model.addAttribute(ReporteUtilYarg.PARAM_TEMPLATE, "reportePago");
        model.addAttribute(ReporteUtilYarg.PARAM_NOMBRE_REPORTE, "Reporte de Ingresos por Concepto");
        model.addAttribute(ReporteUtilYarg.PARAM_REPORTE_PARAMETERS, params);
        modelAndView = new ModelAndView("yargView", model);
        return modelAndView;
    }
}