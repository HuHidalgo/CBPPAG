package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.seguridad.Perfil;

public interface IPerfilService extends IMantenibleService<Perfil>
{
    public List<Perfil> buscarTodos();
}