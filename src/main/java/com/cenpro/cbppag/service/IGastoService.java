package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.egresos.Gastos;

public interface IGastoService extends IMantenibleService<Gastos>{
	
	public List<Gastos> buscarTodos();
	
	public List<Gastos> buscarPorId(int codGastos);
    
    public int registrarGastos(Gastos gastos);
    
    public void actualizarGastos(Gastos gastos);
}
