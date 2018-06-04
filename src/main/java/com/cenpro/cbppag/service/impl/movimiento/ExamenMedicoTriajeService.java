package com.cenpro.cbppag.service.impl.movimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IExamenMedicoTriajeMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.movimiento.ExamenMedicoTriaje;
import com.cenpro.cbppag.service.IExamenMedicoTriajeService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ExamenMedicoTriajeService extends MantenibleService<ExamenMedicoTriaje>
        implements IExamenMedicoTriajeService
{
    @SuppressWarnings("unused")
    private IExamenMedicoTriajeMapper examenMedicoTriajeMapper;

    public ExamenMedicoTriajeService(@Qualifier("IExamenMedicoTriajeMapper") IMantenibleMapper<ExamenMedicoTriaje> mapper){
        super(mapper);
        this.examenMedicoTriajeMapper = (IExamenMedicoTriajeMapper) mapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarExamenMedicoTriaje(ExamenMedicoTriaje examenMedicoTriaje){
        this.registrar(examenMedicoTriaje);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void actualizarExamenMedicoTriaje(ExamenMedicoTriaje examenMedicoTriaje){
        this.actualizar(examenMedicoTriaje);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminarExamenMedicoTriaje(ExamenMedicoTriaje examenMedicoTriaje){
        this.eliminar(examenMedicoTriaje);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ExamenMedicoTriaje> buscarResultadoRegularPorNumeroRegistro(Integer numeroRegistro){
        ExamenMedicoTriaje examenMedicoTriaje = ExamenMedicoTriaje.builder().numeroRegistro(numeroRegistro).build();
        return this.buscar(examenMedicoTriaje, Verbo.GET);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean existeExamenMedicoTriaje(Integer numeroRegistro){
        ExamenMedicoTriaje examenMedicoTriaje = ExamenMedicoTriaje.builder().numeroRegistro(numeroRegistro).build();
        return this.existe(examenMedicoTriaje);
    }
}