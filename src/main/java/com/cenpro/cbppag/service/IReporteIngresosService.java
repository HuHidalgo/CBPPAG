package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.cbppag.model.ingresos.ReporteIngresos;

public interface IReporteIngresosService {
	
    public List<ReporteIngresos> buscarIngresosDiarios(CriterioBusquedaIngresos criterioBusquedaIngresos);

}
