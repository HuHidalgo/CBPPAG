package com.cenpro.cbppag.service;

import com.cenpro.cbppag.model.mantenimiento.ParametroCorreo;

public interface IParametroCorreoService extends IMantenibleService<ParametroCorreo>
{
    public ParametroCorreo buscarParametroCorreo();
}