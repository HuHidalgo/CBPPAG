package com.cenpro.sircie.service.impl.seguridad;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.aspecto.enumeracion.Tipo;
import com.cenpro.sircie.mapper.IAuditoriaMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.criterio.CriterioBusquedaAuditoria;
import com.cenpro.sircie.model.parametro.Parametro;
import com.cenpro.sircie.model.seguridad.Auditoria;
import com.cenpro.sircie.service.IAuditoriaService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.DatesUtils;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class AuditoriaService extends MantenibleService<Auditoria> implements IAuditoriaService
{
    private IAuditoriaMapper auditoriaMapper;

    public AuditoriaService(@Qualifier("IAuditoriaMapper") IMantenibleMapper<Auditoria> mapper)
    {
        super(mapper);
        this.auditoriaMapper = (IAuditoriaMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Auditoria> buscarPistasAuditoriaPorCriterio(
            CriterioBusquedaAuditoria criterioBusqueda)
    {
        return auditoriaMapper.buscarPistasAuditoriaPorCriterio(criterioBusqueda);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarAuditoria(Auditoria auditoria)
    {
        auditoria.setFecha(new Date());
        auditoria.setHora(DatesUtils.obtenerFechaEnFormato(new Date(), DatesUtils.FORMATO_HHMMSS));
        this.registrar(auditoria);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarAuditoria(Tipo tipo, Comentario comentario, Accion accion, boolean exito,
            String nombreUsuario, String direccionIp)
    {
        Auditoria auditoria = Auditoria.builder().codigoAuditoria(tipo.name().toUpperCase())
                .idAccion(accion.toString()).comentario(comentario.toString()).exito(exito)
                .direccionIp(direccionIp).nombreUsuario(nombreUsuario).fecha(new Date())
                .hora(DatesUtils.obtenerFechaEnFormato(new Date(), DatesUtils.FORMATO_HHMMSS))
                .build();
        auditoriaMapper.mantener(new Parametro(Verbo.INSERT, auditoria));
    }
}