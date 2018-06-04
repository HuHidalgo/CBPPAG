package com.cenpro.sircie.service.impl.egresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IProgramacionGastoMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.egresos.ProgramacionGastos;
import com.cenpro.sircie.service.IProgramacionGastoService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class ProgramacionGastoService extends MantenibleService<ProgramacionGastos> implements IProgramacionGastoService{
	
	@SuppressWarnings("unused")
	private IProgramacionGastoMapper egresosMapper;
	
	public ProgramacionGastoService(@Qualifier("IProgramacionGastoMapper") IMantenibleMapper<ProgramacionGastos> mapper) {
		
		super(mapper);
		this.egresosMapper = (IProgramacionGastoMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ProgramacionGastos> buscarTodos() {
		
		return this.buscar(new ProgramacionGastos(), Verbo.GETS);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ProgramacionGastos> buscarPorId(int codProgramacionGasto)
    {
		ProgramacionGastos egreso = ProgramacionGastos.builder().idProgramacionGasto(codProgramacionGasto).build();
        return this.buscar(egreso, Verbo.GET);
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarProgramacionGastos(ProgramacionGastos progGastos) {
		
		List<ProgramacionGastos> egreso = this.registrarAutoIncrementable(progGastos);
        if (!egreso.isEmpty() && egreso.get(0).getIdProgramacionGasto() != null)
        {
            return egreso.get(0).getIdProgramacionGasto();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarProgramacionGastos(ProgramacionGastos progGastos) {
		
		this.actualizar(progGastos);
	}
}
