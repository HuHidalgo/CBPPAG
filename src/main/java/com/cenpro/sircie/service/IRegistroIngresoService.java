package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.ingresos.Ingresos;

public interface IRegistroIngresoService extends IMantenibleService<Ingresos>{
	
	public List<Ingresos> buscarTodos();
	
	public List<Ingresos> buscarPorId(int codIngreso);
    
    public int registrarIngresos(Ingresos ingresos);
    
    public void actualizarIngresos(Ingresos ingresos);
}
