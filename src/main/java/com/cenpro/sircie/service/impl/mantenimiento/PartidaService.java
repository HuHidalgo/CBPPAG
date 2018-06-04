package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IPartidaMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Partida;
import com.cenpro.sircie.model.mantenimiento.Unidad;
import com.cenpro.sircie.service.IPartidaService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class PartidaService extends MantenibleService<Partida> implements IPartidaService
{
	@SuppressWarnings("unused")
    private IPartidaMapper partidaMapper;
	
	public PartidaService(@Qualifier("IPartidaMapper") IMantenibleMapper<Partida> mapper)
    {
        super(mapper);
        this.partidaMapper = (IPartidaMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Partida> buscarTodos() {
		return this.buscar(new Partida(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Partida> buscarPorCodigoTarea(Integer codigoTarea) {

		Partida partida = Partida.builder().codigoTarea(codigoTarea).build();
		return this.buscar(partida, Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarPartida(Partida partida) {
		this.registrar(partida);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarPartida(Partida partida) {
		this.actualizar(partida);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarPartida(Partida partida) {
		this.eliminar(partida);
	}
}
