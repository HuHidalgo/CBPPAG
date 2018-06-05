package com.cenpro.cbppag.service.impl.ingresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IReporteIngresosMapper;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.cbppag.model.ingresos.ReporteIngresos;
import com.cenpro.cbppag.service.IReporteIngresosService;

@Service
public class ReporteIngresosService implements IReporteIngresosService {
	
	private @Autowired IReporteIngresosMapper reporteIngresosMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteIngresos> buscarIngresosDiarios(CriterioBusquedaIngresos criterioBusquedaIngresos) {
		
		return reporteIngresosMapper.buscarIngresosDiarios(criterioBusquedaIngresos);
	}

}
