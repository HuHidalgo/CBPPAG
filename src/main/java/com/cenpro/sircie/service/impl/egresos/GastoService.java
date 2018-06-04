package com.cenpro.sircie.service.impl.egresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IGastoMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.egresos.Gastos;
import com.cenpro.sircie.service.IGastoService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class GastoService extends MantenibleService<Gastos> implements IGastoService{
	
	@SuppressWarnings("unused")
	private IGastoMapper gastosMapper;
	
	public GastoService(@Qualifier("IGastoMapper") IMantenibleMapper<Gastos> mapper) {
		
		super(mapper);
		this.gastosMapper = (IGastoMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Gastos> buscarTodos() {
		
		return this.buscar(new Gastos(), Verbo.GETS);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Gastos> buscarPorId(int codGasto)
    {
		Gastos gasto = Gastos.builder().idGasto(codGasto).build();
        return this.buscar(gasto, Verbo.GET);
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarGastos(Gastos gastos) {
		
		List<Gastos> gasto = this.registrarAutoIncrementable(gastos);
        if (!gasto.isEmpty() && gasto.get(0).getIdGasto() != null){
            return gasto.get(0).getIdGasto();
        } else{
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarGastos(Gastos gastos) {
		
		this.actualizar(gastos);
	}
}
