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
import com.cenpro.cbppag.service.excepcion.MantenimientoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
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
	public List<Especializacion> buscarPorId(String idEspecializacion) {
		Especializacion especializacion = Especializacion.builder().idEspecializacion(idEspecializacion).build();
		return this.buscar(especializacion, Verbo.GET);
	}
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String registrarEspecializacion(Especializacion especializacion) {
		List<Especializacion> especializaciones = this.registrarAutoIncrementable(especializacion);
		if (!especializaciones.isEmpty() && especializaciones.get(especializaciones.size()-1).getIdEspecializacion()!= null){
            return especializaciones.get(especializaciones.size()-1).getIdEspecializacion();
        } else {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }
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
	public List<Especializacion> buscarEspecializaciones(String idModalidad, Integer nroCiclo) {
		Especializacion especializacion = Especializacion.builder().idModalidad(idModalidad).numCiclo(nroCiclo).build();
		return this.buscar(especializacion, Verbo.GET_MODA);
	}

	@Override
	public List<Especializacion> buscarCosto(String idEspecializacion, Integer nroCiclo) {
		Especializacion especializacion = Especializacion.builder().idEspecializacion(idEspecializacion).numCiclo(nroCiclo).build();
		return this.buscar(especializacion, Verbo.GET_COSTO);
	}
	
}
