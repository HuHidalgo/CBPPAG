package com.cenpro.cbppag.service;

import com.cenpro.cbppag.model.movimiento.ExamenMedicoMedicinaGeneral;

public interface IExamenMedicoMedicinaGeneralService
        extends IMantenibleService<ExamenMedicoMedicinaGeneral>
{
    public void registrarExamenMedicoMedicinaGeneral(
            ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral);

    public boolean existeExamenMedicoMedicinaGeneral(Integer numeroRegistro, String anio);
}