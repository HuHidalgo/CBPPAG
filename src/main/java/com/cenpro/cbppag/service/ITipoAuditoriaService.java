package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.seguridad.TipoAuditoria;

public interface ITipoAuditoriaService extends IMantenibleService<TipoAuditoria>
{
    public List<TipoAuditoria> buscarTodos();
}
