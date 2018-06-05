package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.presupuesto.PresupuestoEgreso;


public interface IPEgresoService extends IMantenibleService<PresupuestoEgreso>
{
	public List<PresupuestoEgreso> buscarTodos();
    
    public List<PresupuestoEgreso> buscarPorId(Integer idPEgreso);
    
    public int registrarPEgreso(PresupuestoEgreso pEgreso);
    
    public void actualizarPEgreso(PresupuestoEgreso pEgreso);
    
    public void eliminarPEgreso(PresupuestoEgreso pEgreso);
}
