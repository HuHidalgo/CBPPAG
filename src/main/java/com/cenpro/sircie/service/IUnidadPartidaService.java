package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.UnidadPartida;

public interface IUnidadPartidaService extends IMantenibleService<UnidadPartida> 
{
	public List<UnidadPartida> buscarPartidas(String codigoUnidad);
}
