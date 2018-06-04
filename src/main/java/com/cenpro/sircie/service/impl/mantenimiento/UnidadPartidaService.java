package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IUnidadPartidaMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.UnidadPartida;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;
import com.cenpro.sircie.service.IUnidadPartidaService;

@Service
public class UnidadPartidaService extends MantenibleService<UnidadPartida> implements IUnidadPartidaService
{
	@SuppressWarnings("unused")
	private IUnidadPartidaMapper unidadPMapper;
		
	public UnidadPartidaService(@Qualifier("IUnidadPartidaMapper") IMantenibleMapper<UnidadPartida> mapper) {
		super(mapper);
		this.unidadPMapper = (IUnidadPartidaMapper) mapper;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<UnidadPartida> buscarPartidas(String codigoUnidad) {
		UnidadPartida up = UnidadPartida.builder().codigoUnidad(codigoUnidad).build();
		return this.buscar(up, Verbo.GET_UNI_PARTIDA);
	}

}
