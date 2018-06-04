package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.sircie.model.ingresos.ReporteIngresos;

public interface IReporteIngresosService {
	
    public List<ReporteIngresos> buscarIngresosDiarios(CriterioBusquedaIngresos criterioBusquedaIngresos);

}
