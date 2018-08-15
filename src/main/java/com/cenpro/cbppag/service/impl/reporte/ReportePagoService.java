package com.cenpro.cbppag.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IReportePagoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.reporte.ReportePago;
import com.cenpro.cbppag.service.IReportePagoService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ReportePagoService extends MantenibleService<ReportePago> implements IReportePagoService{
	@SuppressWarnings("unused")
	private IReportePagoMapper reportePagoMapper; 
	
	public ReportePagoService(@Qualifier("IReportePagoMapper") IMantenibleMapper<ReportePago> mapper)
    {
        super(mapper);
        this.reportePagoMapper = (IReportePagoMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReportePago> buscarPagos(ReportePago reportePago) {
		return this.buscar(reportePago, Verbo.GETS);
	}

}
