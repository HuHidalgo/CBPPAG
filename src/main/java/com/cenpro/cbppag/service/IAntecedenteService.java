package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.movimiento.Antecedente;

public interface IAntecedenteService extends IMantenibleService<Antecedente>
{
    public void registrarAntecedente(List<Antecedente> antecedentes);

    public void registrarAntecedente(List<Antecedente> antecedentes, Integer numeroRegistro,
            String anio, String numeroDocumento, String idTipoDocumento);

    public void registrarAntecedente(Antecedente antecedente);
}