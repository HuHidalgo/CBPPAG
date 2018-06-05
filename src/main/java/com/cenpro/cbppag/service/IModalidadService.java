package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.Modalidad;

public interface IModalidadService extends IMantenibleService<Modalidad> {
	
	public List<Modalidad> buscarTodos();
    
    public List<Modalidad> buscarModalidades();
    
    public void registrarModalidad(Modalidad modalidad);
    
    public void actualizarModalidad(Modalidad modalidad);
    
    public void eliminarModalidad(Modalidad modalidad);

}
