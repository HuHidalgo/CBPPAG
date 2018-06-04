package com.cenpro.cbppag.service.impl.movimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IExamenMedicoOdontologicoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.movimiento.ExamenMedicoOdontologico;
import com.cenpro.cbppag.service.IExamenMedicoOdontologicoService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ExamenMedicoOdontologicoService extends MantenibleService<ExamenMedicoOdontologico> implements IExamenMedicoOdontologicoService
{
    @SuppressWarnings("unused")
    private IExamenMedicoOdontologicoMapper examenMedicoOdontologicoMapper;

    public ExamenMedicoOdontologicoService(
            @Qualifier("IExamenMedicoOdontologicoMapper") IMantenibleMapper<ExamenMedicoOdontologico> mapper)
    {
        super(mapper);
        this.examenMedicoOdontologicoMapper = (IExamenMedicoOdontologicoMapper) mapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico)
    {
        this.registrar(examenMedicoOdontologico);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void actualizarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico)
    {
        this.actualizar(examenMedicoOdontologico);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico)
    {
        this.eliminar(examenMedicoOdontologico);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ExamenMedicoOdontologico> buscarResultadoPorNumeroRegistro(Integer numeroRegistro)
    {
        ExamenMedicoOdontologico examenMedicoOdontologico = ExamenMedicoOdontologico.builder().numeroRegistro(numeroRegistro).build();
        return this.buscar(examenMedicoOdontologico, Verbo.GET);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean existeExamenMedicoOdontologico(Integer numeroRegistro)
    {
        ExamenMedicoOdontologico examenMedicoOdontologico = ExamenMedicoOdontologico.builder().numeroRegistro(numeroRegistro).build();
        return this.existe(examenMedicoOdontologico);
    }
}