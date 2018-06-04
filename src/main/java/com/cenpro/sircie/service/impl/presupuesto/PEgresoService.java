package com.cenpro.sircie.service.impl.presupuesto;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IPEgresoMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.presupuesto.PresupuestoEgreso;
import com.cenpro.sircie.service.IPEgresoService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class PEgresoService extends MantenibleService<PresupuestoEgreso> implements IPEgresoService
{

	@SuppressWarnings("unused")
    private IPEgresoMapper pEgresoMapper;
	
	public PEgresoService(@Qualifier("IPEgresoMapper") IMantenibleMapper<PresupuestoEgreso> mapper)
    {
        super(mapper);
        this.pEgresoMapper = (IPEgresoMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<PresupuestoEgreso> buscarTodos() {
		return this.buscar(new PresupuestoEgreso(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<PresupuestoEgreso> buscarPorId(Integer idPEgreso) {
		PresupuestoEgreso pEgreso = PresupuestoEgreso.builder().idPEgreso(idPEgreso).build();
        return this.buscar(pEgreso, Verbo.GET);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarPEgreso(PresupuestoEgreso pEgreso) {
		
		List<PresupuestoEgreso> egreso = this.registrarAutoIncrementable(pEgreso);
		
		System.out.println(egreso);
		
        if (!egreso.isEmpty() && egreso.get(0).getIdPEgreso()!= null)
        {
            return egreso.get(0).getIdPEgreso();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarPEgreso(PresupuestoEgreso pEgreso) {
		this.actualizar(pEgreso);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarPEgreso(PresupuestoEgreso pEgreso) {
		this.eliminar(pEgreso);
	}

}
