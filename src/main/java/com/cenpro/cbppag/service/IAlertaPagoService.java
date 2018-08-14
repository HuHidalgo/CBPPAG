package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.alerta.AlertaPago;

public interface IAlertaPagoService extends IMantenibleService<AlertaPago> {
	public List<AlertaPago> buscarPorFecha(String fechaActual);
}
