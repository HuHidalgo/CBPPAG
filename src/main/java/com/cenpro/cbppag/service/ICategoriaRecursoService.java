package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.seguridad.CategoriaRecurso;

public interface ICategoriaRecursoService extends IMantenibleService<CategoriaRecurso>
{
    public List<CategoriaRecurso> buscarTodosCategoriaRecurso();

    public List<CategoriaRecurso> buscarTodos();

    public void registrarCategoriaRecurso(CategoriaRecurso categoriaRecurso);

    public List<CategoriaRecurso> buscarPorCodigoCategoriaRecurso(String categoria);

    public void actualizarCategoriaRecurso(CategoriaRecurso categoriaRecurso);

    public void eliminarCategoriaRecurso(CategoriaRecurso categoriaRecurso);
}
