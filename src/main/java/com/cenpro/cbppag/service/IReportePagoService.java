package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.reporte.ReportePago;

public interface IReportePagoService extends IMantenibleService<ReportePago>{
	public List<ReportePago> buscarPagos(ReportePago reportePago);
}
