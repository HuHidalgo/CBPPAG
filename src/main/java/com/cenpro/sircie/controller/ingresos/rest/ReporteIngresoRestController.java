package com.cenpro.sircie.controller.ingresos.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.sircie.model.ingresos.ReporteIngresos;
import com.cenpro.sircie.service.IReporteIngresosService;

@RequestMapping("/ingresos/reporte")
public @RestController class ReporteIngresoRestController {

	private @Autowired IReporteIngresosService reporteIngresosService;
	
	static List<ReporteIngresos> listIngresos = new ArrayList<ReporteIngresos>();
	static List<ReporteIngresos> listIngresos1 = new ArrayList<ReporteIngresos>();
	static List<ReporteIngresos> listIngresos2 = new ArrayList<ReporteIngresos>();
	static List<ReporteIngresos> listIngresos3 = new ArrayList<ReporteIngresos>();
	

	@GetMapping(params = "accion=buscar")
	public List<ReporteIngresos> buscarIngresosDetalleConceptos(CriterioBusquedaIngresos criterioBusquedaIngresos) {
		int i = 0;
		
		if(criterioBusquedaIngresos.getVerbo().equals("DET_LIMPIAR_CONCEPTOS")) {
			listIngresos = new ArrayList<ReporteIngresos>();
			return listIngresos;
		}
		else {
			for(ReporteIngresos ri : reporteIngresosService.buscarIngresosDiarios(criterioBusquedaIngresos)) {
				if(listIngresos.isEmpty()) {
					listIngresos.add(ri);
				}
				else {
					for(ReporteIngresos ri2 : listIngresos) {
						if(ri.getIdConcepto() == ri2.getIdConcepto()) {
							i = 1;
							break;
						}
					}
					if(i == 0) {
						listIngresos.add(ri);
					}
				}
			}
			return listIngresos;
		}
	}
	
	public List<ReporteIngresos> reporteDetalleIngresosConceptos(){
		return listIngresos;
	}
	
	@GetMapping(params = "accion=buscar1")
    public List<ReporteIngresos> buscarIngresosGeneralConceptos(CriterioBusquedaIngresos criterioBusquedaIngresos){
		int i = 0;
		
		if(criterioBusquedaIngresos.getVerbo().equals("GEN_LIMPIAR_CONCEPTOS")) {
			listIngresos1 = new ArrayList<ReporteIngresos>();
			return listIngresos1;
		}
		else {
			for(ReporteIngresos ri : reporteIngresosService.buscarIngresosDiarios(criterioBusquedaIngresos)) {
				if(listIngresos1.isEmpty()) {
					ri.setIngresoTotal(ri.getImporteEnero() + ri.getImporteFebrero() + ri.getImporteMarzo() +
							ri.getImporteAbril() + ri.getImporteMayo() + ri.getImporteJunio() + ri.getImporteJulio() +
							ri.getImporteAgosto() + ri.getImporteSetiembre() + ri.getImporteOctubre() +
							ri.getImporteNoviembre() + ri.getImporteDiciembre());
					listIngresos1.add(ri);
				}
				else {
					for(ReporteIngresos ri2 : listIngresos1) {
						if(ri.getIdReporteIngreso() == ri2.getIdReporteIngreso()) {
							i = 1;
							break;
						}
					}
					if(i == 0) {
						ri.setIngresoTotal(ri.getImporteEnero() + ri.getImporteFebrero() + ri.getImporteMarzo() +
								ri.getImporteAbril() + ri.getImporteMayo() + ri.getImporteJunio() + ri.getImporteJulio() +
								ri.getImporteAgosto() + ri.getImporteSetiembre() + ri.getImporteOctubre() +
								ri.getImporteNoviembre() + ri.getImporteDiciembre());
						listIngresos1.add(ri);
					}
				}
			}
			return listIngresos1;
		}
    }
	
	public List<ReporteIngresos> reporteGeneralIngresosConceptos(){
		return listIngresos1;
	}
	
	@GetMapping(params = "accion=buscar2")
    public List<ReporteIngresos> buscarIngresosDetalleCursos(CriterioBusquedaIngresos criterioBusquedaIngresos){
		int i = 0;
		
		if(criterioBusquedaIngresos.getVerbo().equals("DET_LIMPIAR_CURSOS")) {
			listIngresos2 = new ArrayList<ReporteIngresos>();
			return listIngresos2;
		}
		else {
			for(ReporteIngresos ri : reporteIngresosService.buscarIngresosDiarios(criterioBusquedaIngresos)) {
				if(listIngresos2.isEmpty()) {
					listIngresos2.add(ri);
				}
				else {
					for(ReporteIngresos ri2 : listIngresos2) {
						if(ri.getIdConcepto() == ri2.getIdConcepto()) {
							i = 1;
							break;
						}
					}
					if(i == 0) {
						listIngresos2.add(ri);
					}
				}
			}
			return listIngresos2;
		}
    }
	
	@GetMapping(params = "accion=buscar3")
    public List<ReporteIngresos> buscarIngresosGeneralCursos(CriterioBusquedaIngresos criterioBusquedaIngresos){
		
		if(criterioBusquedaIngresos.getVerbo().equals("GEN_LIMPIAR_CURSOS")) {
			listIngresos3 = new ArrayList<ReporteIngresos>();
			return listIngresos3;
		}
		else {
			for(ReporteIngresos ri : reporteIngresosService.buscarIngresosDiarios(criterioBusquedaIngresos)) {
				listIngresos3.add(ri);
			}
			return listIngresos3;
		}
    }
	
	@GetMapping(params = "accion=buscar4")
    public List<ReporteIngresos> buscarIngresosDetalleAportantes(CriterioBusquedaIngresos criterioBusquedaIngresos){
		return reporteIngresosService.buscarIngresosDiarios(criterioBusquedaIngresos);
    }
}
