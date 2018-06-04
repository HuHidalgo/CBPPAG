package com.cenpro.sircie.service.impl.egresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IRegistroEgresosMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.egresos.Egresos;
import com.cenpro.sircie.service.IRegistroEgresosService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.MultiTablaUtil;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class RegistroEgresosService extends MantenibleService<Egresos> implements IRegistroEgresosService{
	
	@SuppressWarnings("unused")
	private IRegistroEgresosMapper egresosMapper;
	
	public RegistroEgresosService(@Qualifier("IRegistroEgresosMapper") IMantenibleMapper<Egresos> mapper) {
		
		super(mapper);
		this.egresosMapper = (IRegistroEgresosMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Egresos> buscarTodos() {
		
		return this.buscar(new Egresos(), Verbo.GETS);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Egresos> buscarPorId(int codEgreso)
    {
		Egresos egreso = Egresos.builder().idEgreso(codEgreso).build();
        return this.buscar(egreso, Verbo.GET);
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarEgresos(Egresos egresos) {
		
		List<Egresos> egreso = this.registrarAutoIncrementable(egresos);
        if (!egreso.isEmpty() && egreso.get(0).getIdEgreso() != null)
        {
            return egreso.get(0).getIdEgreso();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarEgresos(Egresos egresos) {
		
		this.actualizar(egresos);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Egresos> buscarPorIdProgramacionGasto(int IdProgramacionGasto) {
		Egresos egreso = Egresos.builder().idProgramacionGasto(IdProgramacionGasto).idTabla(MultiTablaUtil.TABLA_DOCUMENTO).build();
        return this.buscar(egreso, Verbo.GETS_T);
	}

}
