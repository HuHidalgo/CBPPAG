package com.cenpro.cbppag.service.impl.presupuesto;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IPIngresoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.presupuesto.PresupuestoEgreso;
import com.cenpro.cbppag.model.presupuesto.PresupuestoIngreso;
import com.cenpro.cbppag.service.IPIngresoService;
import com.cenpro.cbppag.service.excepcion.MantenimientoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class PIngresoService extends MantenibleService<PresupuestoIngreso> implements IPIngresoService
{
	@SuppressWarnings("unused")
    private IPIngresoMapper pIngresoMapper;
	
	public PIngresoService(@Qualifier("IPIngresoMapper") IMantenibleMapper<PresupuestoIngreso> mapper)
    {
        super(mapper);
        this.pIngresoMapper = (IPIngresoMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<PresupuestoIngreso> buscarTodos() {
		return this.buscar(new PresupuestoIngreso(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<PresupuestoIngreso> buscarPorId(Integer idPIngreso) {
		PresupuestoIngreso pIngreso = PresupuestoIngreso.builder().idPIngreso(idPIngreso).build();
        return this.buscar(pIngreso, Verbo.GET);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarPIngreso(PresupuestoIngreso pIngreso) {

		List<PresupuestoIngreso> ingreso = this.registrarAutoIncrementable(pIngreso);
		
        if (!ingreso.isEmpty() && ingreso.get(0).getIdPIngreso()!= null)
        {
            return ingreso.get(0).getIdPIngreso();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarPIngreso(PresupuestoIngreso pIngreso) {
		this.actualizar(pIngreso);	
	}

	@Override
	public void eliminarPIngreso(PresupuestoIngreso pIngreso) {
		this.eliminar(pIngreso);
	}

}
