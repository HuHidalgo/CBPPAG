package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.registro.Perfeccionamiento;

public interface IPerfeccionamientoService extends IMantenibleService<Perfeccionamiento>{
	
	public List<Perfeccionamiento> buscarTodos();
    
    public List<Perfeccionamiento> buscarAlumno(String tipoDocumento, String numDocumento);
    
    public List<Perfeccionamiento> buscarPerfeccionamiento(String tipoDocumento, String numDocumento);
    
    public List<Perfeccionamiento> buscarPorId(int idPerfeccionamiento);
	
    public int registrarPerfeccionamiento(Perfeccionamiento pago);
    
    public void actualizarPerfeccionamiento(Perfeccionamiento pago);
    
    public void actualizarVoucher(Perfeccionamiento pago);
    
    public void cargarVoucher(Perfeccionamiento pago);
    
    public List<Perfeccionamiento> recuperarVoucher(int idPerfeccionamiento);
}
