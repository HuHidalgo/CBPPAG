package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.movimiento.Interconsulta;

public interface IInterconsultaService extends IMantenibleService<Interconsulta>
{
    public void registrarInterconsulta(List<Interconsulta> interconsultas, Integer numeroRegistro,
            String anio);

    public void registrarInterconsulta(List<Interconsulta> interconsultas);

    public void registrarInterconsulta(Interconsulta interconsulta);
}