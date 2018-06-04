package com.cenpro.sircie.service.impl.presupuesto;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IPInicialMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.MultiTabCab;
import com.cenpro.sircie.model.presupuesto.Inicial;
import com.cenpro.sircie.service.IPInicialService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class PInicialService extends MantenibleService<Inicial> implements IPInicialService{
	
	@SuppressWarnings("unused")
    private IPInicialMapper pInicialMapper;
	
	public PInicialService(@Qualifier("IPInicialMapper") IMantenibleMapper<Inicial> mapper)
    {
        super(mapper);
        this.pInicialMapper = (IPInicialMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarPInicial(Inicial inicial) {
		List<Inicial> iniciales = this.registrarAutoIncrementable(inicial);
        if (!iniciales.isEmpty() && iniciales.get(0).getIdInicial() != null)
        {
        	System.out.println("Inicial (lista) : " + iniciales.get(0).getIdInicial());
            return iniciales.get(0).getIdInicial();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Inicial> buscarTodos() {
		return this.buscar(new Inicial(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Inicial> buscarPorId(Integer id) {
		System.out.println("LISTO : " + id);
		Inicial inicial = Inicial.builder().idInicial(id).build();
		return this.buscar(inicial, Verbo.GET);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarPInicial(Inicial inicial) {
		this.actualizar(inicial);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarPInicial(Inicial inicial) {
		this.eliminar(inicial);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Inicial> ultimoAño(String tipo) {
		Inicial inicial = Inicial.builder().tipo(tipo).build();
		return this.buscar(inicial, Verbo.GET_AÑO);
	}
	
}
