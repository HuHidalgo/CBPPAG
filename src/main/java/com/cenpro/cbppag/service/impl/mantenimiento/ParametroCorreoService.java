package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IParametroCorreoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.ParametroCorreo;
import com.cenpro.cbppag.service.IParametroCorreoService;
import com.cenpro.cbppag.service.excepcion.ValorNoEncontradoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ParametroCorreoService extends MantenibleService<ParametroCorreo>
        implements IParametroCorreoService
{
    @SuppressWarnings("unused")
    private IParametroCorreoMapper parametroCorreoMapper;

    public ParametroCorreoService(
            @Qualifier("IParametroCorreoMapper") IMantenibleMapper<ParametroCorreo> mapper)
    {
        super(mapper);
        this.parametroCorreoMapper = (IParametroCorreoMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ParametroCorreo buscarParametroCorreo()
    {
        List<ParametroCorreo> parametrosCorreo = this.buscar(new ParametroCorreo(), Verbo.GET);
        if (parametrosCorreo.isEmpty())
        {
            throw new ValorNoEncontradoException(
                    ConstantesExcepciones.PARAMETRO_CORREO_NO_ENCONTRADO);
        }
        return parametrosCorreo.get(0);
    }
}