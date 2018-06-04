package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IFacultadMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Facultad;
import com.cenpro.cbppag.service.IFacultadService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class FacultadService extends MantenibleService<Facultad> implements IFacultadService
{
    @SuppressWarnings("unused")
    private IFacultadMapper facultadMapper;

    public FacultadService(@Qualifier("IFacultadMapper") IMantenibleMapper<Facultad> mapper)
    {
        super(mapper);
        this.facultadMapper = (IFacultadMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Facultad> buscarTodos()
    {
        return this.buscar(new Facultad(), Verbo.GETS);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Facultad> buscarTodosT()
    {
        return this.buscar(new Facultad(), Verbo.GETS_T);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarFacultad(Facultad facultad)
    {
        this.registrar(facultad);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void actualizarFacultad(Facultad facultad)
    {
        this.actualizar(facultad);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminarFacultad(Facultad facultad)
    {
        this.eliminar(facultad);
    }
}