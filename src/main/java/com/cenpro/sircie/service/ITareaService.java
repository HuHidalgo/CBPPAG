package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.Tarea;

public interface ITareaService extends IMantenibleService<Tarea>
{
	public List<Tarea> buscarTodos();
    
	public List<Tarea> buscarPorCodigoUnidadNroMeta(String codigoUnidad, Integer nroMeta, Integer codigoTarea);
	
    public List<Tarea> buscarPorId();
    
    public List<Tarea> buscarMeta(Integer codigoTarea);
    
    public List<Tarea> buscarUnidad(String codigoUnidad);
    
    public List<Tarea> buscarPartida(String codigoPartida);
    
    public void registrarTarea(Tarea tarea);
    
    public void actualizarTarea(Tarea tarea);
    
    public void eliminarTarea(Tarea tarea);
}
