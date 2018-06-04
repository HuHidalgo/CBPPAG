package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IUnidadMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Unidad;
import com.cenpro.sircie.service.IUnidadService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class UnidadService extends MantenibleService<Unidad> implements IUnidadService{
	
	@SuppressWarnings("unused")
	private IUnidadMapper unidadMapper;
	
	private static final String GETS_UNI = "GETS_UNI";
		
	public UnidadService(@Qualifier("IUnidadMapper") IMantenibleMapper<Unidad> mapper) {
		super(mapper);
		this.unidadMapper = (IUnidadMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Unidad> buscarTodos() {
		
		return this.buscar(new Unidad(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Unidad> buscarUnidades() {
		
		return this.buscar(new Unidad(), GETS_UNI);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarUnidad(Unidad unidad) {

		this.registrar(unidad);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarUnidad(Unidad unidad) {

		this.actualizar(unidad);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarUnidad(Unidad unidad) {
		
		this.eliminar(unidad);
	}

}
