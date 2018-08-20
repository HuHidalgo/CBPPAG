package com.cenpro.cbppag.controller.ingresos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.reporte.ReporteDeuda;
import com.cenpro.cbppag.model.reporte.ReportePago;
import com.cenpro.cbppag.service.IReporteDeudaService;
import com.cenpro.cbppag.service.IReportePagoService;

@RequestMapping("/ingresos/reporte")
public @RestController class ReporteRestController {
	private @Autowired IReporteDeudaService reporteDeudaService;
	private @Autowired IReportePagoService iReportePagoService;
	
	@GetMapping(params = "accion=buscarDeuda")
    public List<ReporteDeuda> buscarDeudas(ReporteDeuda reporte)
    {
        return reporteDeudaService.buscarDeudas(reporte);
    }
	
	@GetMapping(params = "accion=buscarPago")
    public List<ReportePago> buscarPagos(ReportePago reporte)
    {
        return iReportePagoService.buscarPagos(reporte);
    }
}
