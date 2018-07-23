package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.registro.Matricula;

public interface IMatriculaService extends IMantenibleService<Matricula>{

	public List<Matricula> buscarTodos();
    
    public List<Matricula> buscarAlumno(String documento);
    
    public List<Matricula> buscarPorId(String codigo);
    
    public String registrarMatricula(Matricula matricula);
    
    public void actualizarMatricula(Matricula matricula);
    
    public void eliminarMatricula(Matricula matricula);
    
}
