package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.reporte.ReporteDeuda;

public interface IReporteDeudaService extends IMantenibleService<ReporteDeuda>{
	public List<ReporteDeuda> buscarDeudas(ReporteDeuda reporteDeuda);
}
