package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.movimiento.ExamenMedicoOdontologico;

public interface IExamenMedicoOdontologicoService extends IMantenibleService<ExamenMedicoOdontologico>
{
	public void registrarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico);
	
	public void actualizarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico);
    
    public void eliminarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico);
    
    public List<ExamenMedicoOdontologico> buscarResultadoPorNumeroRegistro(Integer numeroRegistro);
    
    public boolean existeExamenMedicoOdontologico(Integer numeroRegistro);
}
