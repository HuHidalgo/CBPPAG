package com.cenpro.sircie.service.impl.ingresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IRegistroIngresoMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.ingresos.Ingresos;
import com.cenpro.sircie.service.IRegistroIngresoService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class RegistroIngresoService extends MantenibleService<Ingresos> implements IRegistroIngresoService{
	
	@SuppressWarnings("unused")
	private IRegistroIngresoMapper ingresosMapper;
	
	public RegistroIngresoService(@Qualifier("IRegistroIngresoMapper") IMantenibleMapper<Ingresos> mapper) {
		
		super(mapper);
		this.ingresosMapper = (IRegistroIngresoMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Ingresos> buscarTodos() {
		
		return this.buscar(new Ingresos(), Verbo.GETS);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Ingresos> buscarPorId(int codIngreso)
    {
		Ingresos ingreso = Ingresos.builder().idIngreso(codIngreso).build();
        return this.buscar(ingreso, Verbo.GET);
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarIngresos(Ingresos ingresos) {
		
		List<Ingresos> ingreso = this.registrarAutoIncrementable(ingresos);
		System.out.println(ingreso);
        if (!ingreso.isEmpty() && ingreso.get(0).getIdIngreso() != null)
        {
            return ingreso.get(0).getIdIngreso();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarIngresos(Ingresos ingresos) {
		
		this.actualizar(ingresos);
	}

}
