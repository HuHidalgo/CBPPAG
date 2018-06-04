package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IMetaMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Meta;
import com.cenpro.sircie.service.IMetaService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class MetaService extends MantenibleService<Meta> implements IMetaService
{
	@SuppressWarnings("unused")
    private IMetaMapper metaMapper;
	
	public MetaService(@Qualifier("IMetaMapper") IMantenibleMapper<Meta> mapper)
    {
        super(mapper);
        this.metaMapper = (IMetaMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Meta> buscarTodos() {
		return this.buscar(new Meta(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Meta> buscarPorId() {
		return this.buscar(new Meta(), Verbo.GET);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarMeta(Meta meta) {
		this.registrar(meta);	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarMeta(Meta meta) {
		this.actualizar(meta);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarMeta(Meta meta) {
		this.eliminar(meta);
	}

}
