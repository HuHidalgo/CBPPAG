package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.UnidadPartida;

public interface IUnidadPartidaService extends IMantenibleService<UnidadPartida> 
{
	public List<UnidadPartida> buscarPartidas(String codigoUnidad);
}
