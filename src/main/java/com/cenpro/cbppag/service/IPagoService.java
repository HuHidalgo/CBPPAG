package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.registro.Pago;

public interface IPagoService extends IMantenibleService<Pago>{
	
	public List<Pago> buscarTodos();
    
    public List<Pago> buscarAlumno(String documento);
	
    public void registrarPago(Pago pago);
    
    public void cargarVoucher(Pago pago);
    
    public List<Pago> recuperarVoucher(String documento);
}
