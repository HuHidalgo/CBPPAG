package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.Alerta;

public interface IAlertaService extends IMantenibleService<Alerta>{
	
	public List<Alerta> buscarTodos();
    
    public List<Alerta> buscarPorId(String codigoAlerta);
    
    public List<Alerta> buscarMensaje(String tipoAlerta);
    
    public String registrarAlerta(Alerta alerta);
    
    public void actualizarAlerta(Alerta alerta);
    
    public void eliminarAlerta(Alerta alerta);
    
}
