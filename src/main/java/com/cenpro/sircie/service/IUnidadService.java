package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.Unidad;

public interface IUnidadService extends IMantenibleService<Unidad> {
	
	public List<Unidad> buscarTodos();
    
    public List<Unidad> buscarUnidades();
    
    public void registrarUnidad(Unidad unidad);
    
    public void actualizarUnidad(Unidad unidad);
    
    public void eliminarUnidad(Unidad unidad);

}
