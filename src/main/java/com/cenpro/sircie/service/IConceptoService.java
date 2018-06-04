package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.Concepto;

public interface IConceptoService extends IMantenibleService<Concepto>{
	
	public List<Concepto> buscarTodos();
    
	public List<Concepto> buscarPorId(Integer idConcepto);
	
    public List<Concepto> buscarPorCodigoUnidad(String codigoUnidad);
    
	public List<Concepto> buscarPorCodigoUnidadIdConcepto(String codigoUnidad, Integer idConcepto);
    
    public int registrarConcepto(Concepto concepto);
    
    public void actualizarConcepto(Concepto concepto);
    
    public void eliminarConcepto(Concepto concepto);
}
