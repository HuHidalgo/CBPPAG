package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.egresos.Egresos;

public interface IRegistroEgresosService extends IMantenibleService<Egresos>{
	
	public List<Egresos> buscarTodos();
	
	public List<Egresos> buscarPorId(int codEgreso);
	
	public List<Egresos> buscarPorIdProgramacionGasto(int IdProgramacionGasto);
    
    public int registrarEgresos(Egresos egresos);
    
    public void actualizarEgresos(Egresos egresos);
}
