package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.presupuesto.Inicial;
import com.cenpro.sircie.model.seguridad.Usuario;

public interface IPInicialService extends IMantenibleService<Inicial>
{
	public List<Inicial> buscarTodos();
	
	public List<Inicial> buscarPorId(Integer id);
	
	public List<Inicial> ultimoAño(String tipo);
	
    public int registrarPInicial(Inicial inicial);
    
    public void actualizarPInicial(Inicial inicial);

    public void eliminarPInicial(Inicial inicial);
}

