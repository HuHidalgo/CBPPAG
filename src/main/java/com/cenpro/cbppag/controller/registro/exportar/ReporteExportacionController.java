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
    	if(reporte.getNumeroCiclo() == 0) {
    		reporte.setCiclo("Todos");
    	}
    	else {
    		reporte.setCiclo(String.valueOf(reporte.getNumeroCiclo()));
    	}
    	
        List<ReporteDeuda> reporteGeneral = reporteDeudaService.buscarDeudas(reporte);
        
        if(reporte.getCodigoAlumno() == "") {
        	reporte.setCodigoAlumno("Todos");
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("param1", reporteGeneral);
        params.put("param2", reporte);

        //Hoja de reporte
        model.addAttribute("rb_titulo", ReporteUtilYarg.buildReportBand("titulo"));
        model.addAttribute("rb_criterioBusqueda", ReporteUtilYarg.buildReportBand(
                "criteriosBusqueda", "criteriosBusqueda", "parameter=param2 $", "json"));
        model.addAttribute("rb_encabezado", ReporteUtilYarg.buildReportBand("encabezadoResultado"));
        model.addAttribute("rb_datos",
                ReporteUtilYarg.buildReportBand("datosReporte", "datosReporte", "parameter=param1 $", "json"));
        
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
        if(reporte.getNumeroCiclo() == 0) {
    		reporte.setCiclo("Todos");
    	}
    	else {
    		reporte.setCiclo(String.valueOf(reporte.getNumeroCiclo()));
    	}
    	
        List<ReportePago> reporteGeneral = ReportePagoService.buscarPagos(reporte);
        
        if(reporte.getCodigoAlumno() == "") {
        	reporte.setCodigoAlumno("Todos");
        }
        
        Map<String, Object> params = new HashMap<>();
        params.put("param1", reporteGeneral);
        params.put("param2", reporte);

        //Hoja de reporte
        model.addAttribute("rb_titulo", ReporteUtilYarg.buildReportBand("titulo"));
        model.addAttribute("rb_criterioBusqueda", ReporteUtilYarg.buildReportBand(
                "criteriosBusqueda", "criteriosBusqueda", "parameter=param2 $", "json"));
        model.addAttribute("rb_encabezado", ReporteUtilYarg.buildReportBand("encabezadoResultado"));
        model.addAttribute("rb_datos",
                ReporteUtilYarg.buildReportBand("datosReporte", "datosReporte", "parameter=param1 $", "json"));
        
        model.addAttribute(ReporteUtilYarg.PARAM_TEMPLATE, "reportePago");
        model.addAttribute(ReporteUtilYarg.PARAM_NOMBRE_REPORTE, "Reporte de Pagos");
        model.addAttribute(ReporteUtilYarg.PARAM_REPORTE_PARAMETERS, params);
        modelAndView = new ModelAndView("yargView", model);
        
        return modelAndView;
    }
}