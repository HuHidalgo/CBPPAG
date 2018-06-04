package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;
import com.cenpro.sircie.model.criterio.CriterioBusquedaAuditoria;
import com.cenpro.sircie.model.seguridad.Auditoria;

public interface IAuditoriaService extends IMantenibleService<Auditoria>
{
    public List<Auditoria> buscarPistasAuditoriaPorCriterio(CriterioBusquedaAuditoria criterio);

    public void registrarAuditoria(Auditoria auditoria);

    public void registrarAuditoria(Tipo tipo, Comentario comentario, Accion accion, boolean exito,
            String nombreUsuario, String direccionIp);
}