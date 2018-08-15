package com.cenpro.cbppag.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IReporteDeudaMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.reporte.ReporteDeuda;
import com.cenpro.cbppag.service.IReporteDeudaService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ReporteDeudaService extends MantenibleService<ReporteDeuda>  implements IReporteDeudaService{
	@SuppressWarnings("unused")
	private IReporteDeudaMapper reporteDeudaMapper;

	public ReporteDeudaService(@Qualifier("IReporteDeudaMapper") IMantenibleMapper<ReporteDeuda> mapper)
    {
        super(mapper);
        this.reporteDeudaMapper = (IReporteDeudaMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteDeuda> buscarDeudas(ReporteDeuda reporteDeuda) {
		return this.buscar(reporteDeuda, Verbo.GETS);
	}
}
