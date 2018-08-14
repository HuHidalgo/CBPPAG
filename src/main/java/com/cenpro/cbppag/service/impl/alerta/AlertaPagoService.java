package com.cenpro.cbppag.service.impl.alerta;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IAlertaPagoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.alerta.AlertaPago;
import com.cenpro.cbppag.service.IAlertaPagoService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class AlertaPagoService extends MantenibleService<AlertaPago> implements IAlertaPagoService{
	@SuppressWarnings("unused")
	private IAlertaPagoMapper alertaPagoMapper;
	
	public AlertaPagoService(@Qualifier("IAlertaPagoMapper") IMantenibleMapper<AlertaPago> mapper)
    {
        super(mapper);
        this.alertaPagoMapper = (IAlertaPagoMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<AlertaPago> buscarPorFecha(String fechaActual) {
		AlertaPago alerta = AlertaPago.builder().fechaActual(fechaActual).build();
		return this.buscar(alerta, Verbo.GET_ALERTA_PAGO);
	}
}
