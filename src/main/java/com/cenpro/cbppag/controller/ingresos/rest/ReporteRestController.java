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
	
	@GetMapping("deuda/{cadena}")
    public List<ReporteDeuda> buscarDeudas(@PathVariable String cadena)
    {
		int pos1 = cadena.indexOf("-");
		String modalidad = cadena.substring(0, pos1);
		String cadena2 = cadena.substring(pos1+1);
		
		int pos2 = cadena2.indexOf("-");
		String especializacion = cadena2.substring(0, pos2);
		String cadena3 = cadena2.substring(pos2+1);
		
		int pos3 = cadena3.indexOf("-");
		String codigoAlumno = cadena3.substring(0, pos3);
		String numeroCiclo = cadena3.substring(pos3+1);
		
		ReporteDeuda reporte = new ReporteDeuda();
		if(!modalidad.equals("nulo")) {
			reporte.setIdModalidad(modalidad);
		}
		if(!especializacion.equals("nulo")) {
			reporte.setIdEspecializacion(especializacion);;
		}
		if(!codigoAlumno.equals("nulo")) {
			reporte.setCodigoAlumno(codigoAlumno);
		}
		if(!numeroCiclo.equals("nulo")) {
			reporte.setNumeroCiclo(Integer.parseInt(numeroCiclo));
		}
		
        return reporteDeudaService.buscarDeudas(reporte);
    }
	
	@GetMapping("pago/{cadena}")
    public List<ReportePago> buscarPagos(@PathVariable String cadena)
    {
		int pos1 = cadena.indexOf("-");
		String modalidad = cadena.substring(0, pos1);
		String cadena2 = cadena.substring(pos1+1);
		
		int pos2 = cadena2.indexOf("-");
		String especializacion = cadena2.substring(0, pos2);
		String cadena3 = cadena2.substring(pos2+1);
		
		int pos3 = cadena3.indexOf("-");
		String codigoAlumno = cadena3.substring(0, pos3);
		String numeroCiclo = cadena3.substring(pos3+1);
		
		ReportePago reporte = new ReportePago();
		if(!modalidad.equals("nulo")) {
			reporte.setIdModalidad(modalidad);
		}
		if(!especializacion.equals("nulo")) {
			reporte.setIdEspecializacion(especializacion);;
		}
		if(!codigoAlumno.equals("nulo")) {
			reporte.setCodigoAlumno(codigoAlumno);
		}
		if(!numeroCiclo.equals("nulo")) {
			reporte.setNumeroCiclo(Integer.parseInt(numeroCiclo));
		}
		
        return iReportePagoService.buscarPagos(reporte);
    }
}
