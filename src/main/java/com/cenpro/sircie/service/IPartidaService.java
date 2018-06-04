package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.Partida;

public interface IPartidaService extends IMantenibleService<Partida>
{
	public List<Partida> buscarTodos();
    
    public List<Partida> buscarPorCodigoTarea(Integer codigoTarea);
    
    public void registrarPartida(Partida partida);
    
    public void actualizarPartida(Partida partida);
    
    public void eliminarPartida(Partida partida);
}

