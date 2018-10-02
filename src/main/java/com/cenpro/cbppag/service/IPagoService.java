package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.registro.Pago;

public interface IPagoService extends IMantenibleService<Pago>{
	
	public List<Pago> buscarTodos();
    
    public List<Pago> buscarAlumno(String documento);
    
    public List<Pago> buscarPorId(String codigoPago);
	
    public String registrarPago(Pago pago);
    
    public void actualizarPago(Pago pago);
    
    public void actualizarVoucher(Pago pago);
    
    public void cargarVoucher(Pago pago);
    
    public List<Pago> recuperarVoucher(String codigoPago);
}
