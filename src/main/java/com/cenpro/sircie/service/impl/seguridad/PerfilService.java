package com.cenpro.sircie.service.impl.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IPerfilMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.seguridad.Perfil;
import com.cenpro.sircie.service.IPerfilService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class PerfilService extends MantenibleService<Perfil> implements IPerfilService
{
    @SuppressWarnings("unused")
    private IPerfilMapper perfilMapper;

    public PerfilService(@Qualifier("IPerfilMapper") IMantenibleMapper<Perfil> mapper)
    {
        super(mapper);
        this.perfilMapper = (IPerfilMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Perfil> buscarTodos()
    {
        return this.buscar(new Perfil(), Verbo.GETS);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarPerfil(Perfil perfil) {
    	this.registrar(perfil);
	}

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarPerfil(Perfil perfil) {
    	this.actualizar(perfil);	
	}

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarPerfil(Perfil perfil) {
    	this.eliminar(perfil);
	}
}