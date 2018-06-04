package com.cenpro.sircie.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.configuracion.security.SecurityContextFacade;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.parametro.Parametro;
import com.cenpro.sircie.service.IMantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class MantenibleService<T> implements IMantenibleService<T>
{
    protected IMantenibleMapper<T> mantenimientoMapper;

    public MantenibleService(IMantenibleMapper<T> mapper)
    {
        this.mantenimientoMapper = mapper;
    }

    public MantenibleService()
    {
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void registrar(T dto)
    {
        mantenimientoMapper.mantener(
                new Parametro(Verbo.INSERT, dto, SecurityContextFacade.obtenerNombreUsuario()));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> registrarAutoIncrementable(T dto)
    {
        return mantenimientoMapper.mantener(
                new Parametro(Verbo.INSERT, dto, SecurityContextFacade.obtenerNombreUsuario()));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void registrar(T dto, String verbo)
    {
        mantenimientoMapper
                .mantener(new Parametro(verbo, dto, SecurityContextFacade.obtenerNombreUsuario()));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void actualizar(T dto)
    {
        mantenimientoMapper.mantener(
                new Parametro(Verbo.UPDATE, dto, SecurityContextFacade.obtenerNombreUsuario()));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void actualizar(T dto, String verbo)
    {
        mantenimientoMapper
                .mantener(new Parametro(verbo, dto, SecurityContextFacade.obtenerNombreUsuario()));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void eliminar(T dto)
    {
        mantenimientoMapper.mantener(new Parametro(Verbo.REMOVE, dto));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void eliminar(T dto, String verbo)
    {
        mantenimientoMapper.mantener(new Parametro(verbo, dto));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> buscar(T dto, String verbo)
    {
    	//System.out.println("3>>>>> "+mantenimientoMapper.mantener(new Parametro(verbo, dto)));
        return mantenimientoMapper.mantener(new Parametro(verbo, dto));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public boolean existe(T dto)
    {
        return !mantenimientoMapper.mantener(new Parametro(Verbo.EXIST, dto)).isEmpty();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mantener(T dto, String verbo)
    {
        mantenimientoMapper
                .mantener(new Parametro(verbo, dto, SecurityContextFacade.obtenerNombreUsuario()));
    }
}