package com.cenpro.cbppag.service.impl.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.ITipoAuditoriaMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.seguridad.TipoAuditoria;
import com.cenpro.cbppag.service.ITipoAuditoriaService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class TipoAuditoriaService extends MantenibleService<TipoAuditoria>
        implements ITipoAuditoriaService
{
    @SuppressWarnings("unused")
    private ITipoAuditoriaMapper tipoAuditoriaMapper;

    public TipoAuditoriaService(
            @Qualifier("ITipoAuditoriaMapper") IMantenibleMapper<TipoAuditoria> mapper)
    {
        super(mapper);
        this.tipoAuditoriaMapper = (ITipoAuditoriaMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<TipoAuditoria> buscarTodos()
    {
        return this.buscar(new TipoAuditoria(), Verbo.GETS);
    }
}