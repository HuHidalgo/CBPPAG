package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.registro.Matricula;

public interface IMatriculaService extends IMantenibleService<Matricula>{

	public List<Matricula> buscarTodos();
    
    public List<Matricula> buscarAlumno(String tipoDocumento, String nroDocumento);
    
    public List<Matricula> buscarDiplomatura(String tipoDocumento, String nroDocumento);
    
    public List<Matricula> buscarPorId(int idMatricula);
    
    public int registrarMatricula(Matricula matricula);
    
    public List<Matricula> recuperarVoucher(int idMatricula);
    
    public void cargarVoucher(Matricula matricula);
    
    public void actualizarMatricula(Matricula matricula);
    
    public void actualizarVoucher(Matricula matricula);
    
    public void eliminarMatricula(Matricula matricula);
    
}