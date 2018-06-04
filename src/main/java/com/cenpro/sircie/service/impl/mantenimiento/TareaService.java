package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.ITareaMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Tarea;
import com.cenpro.sircie.model.mantenimiento.Unidad;
import com.cenpro.sircie.service.ITareaService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class TareaService extends MantenibleService<Tarea> implements ITareaService
{
	@SuppressWarnings("unused")
    private ITareaMapper tareaMapper;
	
	public TareaService(@Qualifier("ITareaMapper") IMantenibleMapper<Tarea> mapper)
    {
        super(mapper);
        this.tareaMapper = (ITareaMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Tarea> buscarTodos() {
		return this.buscar(new Tarea(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Tarea> buscarPorId() {
		return this.buscar(new Tarea(), Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Tarea> buscarPorCodigoUnidadNroMeta(String codigoUnidad, Integer nroMeta, Integer codigoTarea) {
		
		Tarea tarea = Tarea.builder().codigoUnidad(codigoUnidad).nroMeta(nroMeta).codigoTarea(codigoTarea).build();
		System.out.println(tarea);
		return this.buscar(tarea, Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarTarea(Tarea tarea) {
		this.registrar(tarea);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarTarea(Tarea tarea) {
		this.actualizar(tarea);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarTarea(Tarea tarea) {
		this.eliminar(tarea);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Tarea> buscarUnidad(String codigoUnidad) {
		Tarea tarea = Tarea.builder().codigoUnidad(codigoUnidad).build();
		return this.buscar(tarea, Verbo.GET_UNI);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Tarea> buscarPartida(String codigoPartida) {
		Tarea tarea = Tarea.builder().clasificador(codigoPartida).build();
		return this.buscar(tarea, Verbo.GET_PAR);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Tarea> buscarMeta(Integer codigoTarea) {
		Tarea tarea = Tarea.builder().codigoTarea(codigoTarea).build();
		return this.buscar(tarea, Verbo.GET_META);
	}
}
