package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.egresos.ProgramacionGastos;

public interface IProgramacionGastoService extends IMantenibleService<ProgramacionGastos>{
	
	public List<ProgramacionGastos> buscarTodos();
	
	public List<ProgramacionGastos> buscarPorId(int codProgGastos);
    
    public int registrarProgramacionGastos(ProgramacionGastos progGastos);
    
    public void actualizarProgramacionGastos(ProgramacionGastos progGastos);
}
