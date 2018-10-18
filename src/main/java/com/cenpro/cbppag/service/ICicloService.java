package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.Ciclo;

public interface ICicloService extends IMantenibleService<Ciclo>
{
	public List<Ciclo> buscarTodos();
    
    public List<Ciclo> buscarPorEspecializacion(String idEspecializacion);
    
    public List<Ciclo> buscarPorId(int idCiclo, String idEspecializacion);
    
    public void registrarCiclo(Ciclo ciclo);
    
    public void actualizarCiclo(Ciclo ciclo);
    
    public void eliminarCiclo(Ciclo ciclo);
}
