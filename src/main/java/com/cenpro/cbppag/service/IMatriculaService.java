package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.model.registro.Pago;

public interface IMatriculaService extends IMantenibleService<Matricula>{

	public List<Matricula> buscarTodos();
    
    public List<Matricula> buscarAlumno(String documento);
    
    public List<Matricula> buscarPorId(String codigoMatricula);
    
    public String registrarMatricula(Matricula matricula);
    
    public List<Matricula> recuperarVoucher(String codigoMatricula);
    
    public void cargarVoucher(Matricula matricula);
    
    public void actualizarMatricula(Matricula matricula);
    
    public void actualizarVoucher(Matricula matricula);
    
    public void eliminarMatricula(Matricula matricula);
    
}