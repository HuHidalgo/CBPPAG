package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IEspecializacionMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Especializacion;
//import com.cenpro.cbppag.model.mantenimiento.Unidad;
import com.cenpro.cbppag.service.IEspecializacionService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class EspecializacionService extends MantenibleService<Especializacion> implements IEspecializacionService
{
	@SuppressWarnings("unused")
    private IEspecializacionMapper tareaMapper;
	
	public EspecializacionService(@Qualifier("IEspecializacionMapper") IMantenibleMapper<Especializacion> mapper)
    {
        super(mapper);
        this.tareaMapper = (IEspecializacionMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Especializacion> buscarTodos() {
		return this.buscar(new Especializacion(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Especializacion> buscarPorId() {
		return this.buscar(new Especializacion(), Verbo.GET);
	}
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarEspecializacion(Especializacion especializacion) {
		this.registrar(especializacion);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarEspecializacion(Especializacion especializacion) {
		this.actualizar(especializacion);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarEspecializacion(Especializacion especializacion) {
		this.eliminar(especializacion);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Especializacion> buscarModalidad(String idModalidad) {
		Especializacion especializacion = Especializacion.builder().idModalidad(idModalidad).build();
		return this.buscar(especializacion, Verbo.GET_MODA);
	}
	
}
