package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.aspecto.enumeracion.Accion;
import com.cenpro.cbppag.aspecto.enumeracion.Comentario;
import com.cenpro.cbppag.aspecto.enumeracion.Tipo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAuditoria;
import com.cenpro.cbppag.model.seguridad.Auditoria;

public interface IAuditoriaService extends IMantenibleService<Auditoria>
{
    public List<Auditoria> buscarPistasAuditoriaPorCriterio(CriterioBusquedaAuditoria criterio);

    public void registrarAuditoria(Auditoria auditoria);

    public void registrarAuditoria(Tipo tipo, Comentario comentario, Accion accion, boolean exito,
            String nombreUsuario, String direccionIp);
}