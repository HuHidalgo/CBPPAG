package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.Especializacion;

public interface IEspecializacionService extends IMantenibleService<Especializacion>
{
	public List<Especializacion> buscarTodos();
    	
    public List<Especializacion> buscarPorId();
    
    public List<Especializacion> buscarModalidad(String idModalidad);
    
    public List<Especializacion> buscarCosto(String idEspecializacion);
    
    public void registrarEspecializacion(Especializacion especializacion);
    
    public void actualizarEspecializacion(Especializacion especializacion);
    
    public void eliminarEspecializacion(Especializacion especializacion);
}
