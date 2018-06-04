package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.seguridad.TipoAuditoria;

public interface ITipoAuditoriaService extends IMantenibleService<TipoAuditoria>
{
    public List<TipoAuditoria> buscarTodos();
}
