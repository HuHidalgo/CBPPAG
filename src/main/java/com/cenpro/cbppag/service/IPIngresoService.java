package com.cenpro.cbppag.service;

import java.util.List;


import com.cenpro.cbppag.model.presupuesto.PresupuestoIngreso;

public interface IPIngresoService extends IMantenibleService<PresupuestoIngreso>
{
	public List<PresupuestoIngreso> buscarTodos();
    
    public List<PresupuestoIngreso> buscarPorId(Integer idPIngreso);
    
    public int registrarPIngreso(PresupuestoIngreso pIngreso);
    
    public void actualizarPIngreso(PresupuestoIngreso pIngreso);
    
    public void eliminarPIngreso(PresupuestoIngreso pIngreso);
}
