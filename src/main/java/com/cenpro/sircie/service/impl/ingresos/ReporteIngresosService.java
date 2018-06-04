package com.cenpro.sircie.service.impl.ingresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IReporteIngresosMapper;
import com.cenpro.sircie.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.sircie.model.ingresos.ReporteIngresos;
import com.cenpro.sircie.service.IReporteIngresosService;

@Service
public class ReporteIngresosService implements IReporteIngresosService {
	
	private @Autowired IReporteIngresosMapper reporteIngresosMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteIngresos> buscarIngresosDiarios(CriterioBusquedaIngresos criterioBusquedaIngresos) {
		
		return reporteIngresosMapper.buscarIngresosDiarios(criterioBusquedaIngresos);
	}

}
