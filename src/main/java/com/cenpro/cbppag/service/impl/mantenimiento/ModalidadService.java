package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IModalidadMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Modalidad;
import com.cenpro.cbppag.service.IModalidadService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ModalidadService extends MantenibleService<Modalidad> implements IModalidadService{
	
	@SuppressWarnings("unused")
	private IModalidadMapper modalidadMapper;
	
	private static final String GETS_UNI = "GETS_UNI";
		
	public ModalidadService(@Qualifier("IModalidadMapper") IMantenibleMapper<Modalidad> mapper) {
		super(mapper);
		this.modalidadMapper = (IModalidadMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Modalidad> buscarTodos() {
		
		return this.buscar(new Modalidad(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Modalidad> buscarModalidades() {
		
		return this.buscar(new Modalidad(), GETS_UNI);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarModalidad(Modalidad modalidad) {

		this.registrar(modalidad);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarModalidad(Modalidad modalidad) {

		this.actualizar(modalidad);		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarModalidad(Modalidad modalidad) {
		
		this.eliminar(modalidad);
	}

}
