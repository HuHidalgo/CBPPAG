package com.cenpro.cbppag.service.impl.registro;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IPagoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.registro.Pago;
import com.cenpro.cbppag.service.IPagoService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class PagoService extends MantenibleService<Pago> implements IPagoService{
	@SuppressWarnings("unused")
	private IPagoMapper pagoMapper;
	
	public PagoService(@Qualifier("IPagoMapper") IMantenibleMapper<Pago> mapper)
    {
        super(mapper);
        this.pagoMapper = (IPagoMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Pago> buscarTodos() {
		return this.buscar(new Pago(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Pago> buscarAlumno(String documento) {
		Pago pago = Pago.builder().codigoAlumno(documento).build();
		return this.buscar(pago, Verbo.VERIFICAR_AM);
	}

	@Override
	public void registrarPago(Pago pago) {
		this.registrar(pago);
	}

}
