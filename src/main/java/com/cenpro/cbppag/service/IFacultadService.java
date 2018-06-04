package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.Facultad;

public interface IFacultadService extends IMantenibleService<Facultad>
{
    public List<Facultad> buscarTodos();
    
    public List<Facultad> buscarTodosT();
    
    public void registrarFacultad(Facultad facultad);
    
    public void actualizarFacultad(Facultad facultad);
    
    public void eliminarFacultad(Facultad facultad);
}