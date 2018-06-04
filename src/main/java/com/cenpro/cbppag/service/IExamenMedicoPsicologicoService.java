package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.movimiento.ExamenMedicoPsicologico;

public interface IExamenMedicoPsicologicoService extends IMantenibleService<ExamenMedicoPsicologico>
{
    public void registrarExamenMedicoPsicologico(ExamenMedicoPsicologico examenMedicoPsicologico);
    
    public void actualizarExamenMedicoPsicologico(ExamenMedicoPsicologico examenMedicoPsicologico);
    
    public void eliminarExamenMedicoPsicologico(ExamenMedicoPsicologico examenMedicoPsicologico);
    
    public List<ExamenMedicoPsicologico> buscarResultadoRegularPorNumeroRegistro(Integer numeroRegistro);
    
    public boolean existeExamenMedicoPsicologico(Integer numeroRegistro);
}