package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.Curso;


public interface ICursoService extends IMantenibleService<Curso>
{
	public List<Curso> buscarTodos();
	
	public List<Curso> buscarPorCodigoUnidad(String codUnidad);
	
    public List<Curso> buscarPorCodigoUnidadCodigoCurso(String codCurso, String codUnidad);
    
    public void registrarCurso(Curso curso);
    
    public void actualizarCurso(Curso curso);
    
    public void eliminarCurso(Curso curso);
}
