package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.ICicloMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Ciclo;
import com.cenpro.cbppag.service.ICicloService;
import com.cenpro.cbppag.service.excepcion.MantenimientoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class CicloService extends MantenibleService<Ciclo> implements ICicloService
{
    @SuppressWarnings("unused")
    private ICicloMapper cicloMapper;

    private static final String GETS_ACTIVO = "GETS_ACTIVO";

    public CicloService(@Qualifier("ICicloMapper") IMantenibleMapper<Ciclo> mapper)
    {
        super(mapper);
        this.cicloMapper = (ICicloMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Ciclo> buscarTodos()
    {
        return this.buscar(new Ciclo(), Verbo.GETS);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Ciclo> buscarPorId(int idCiclo, String idEspecializacion)
    {
        Ciclo ciclo = Ciclo.builder().idCiclo(idCiclo).idEspecializacion(idEspecializacion).build();
        return this.buscar(ciclo, Verbo.GET);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Ciclo> buscarPorEspecializacion(String idEspecializacion) {
    	Ciclo ciclo = Ciclo.builder().idEspecializacion(idEspecializacion).build();
    	return this.buscar(ciclo, Verbo.GETS);
	}

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarCiclo(Ciclo ciclo)
    {
    	this.registrar(ciclo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void actualizarCiclo(Ciclo ciclo)
    {
        this.actualizar(ciclo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminarCiclo(Ciclo ciclo)
    {
        this.eliminar(ciclo);
    }
}