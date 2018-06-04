package com.cenpro.cbppag.controller.seguridad.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.criterio.CriterioBusquedaAuditoria;
import com.cenpro.cbppag.model.seguridad.Auditoria;
import com.cenpro.cbppag.service.IAuditoriaService;

public @RestController class AuditoriaController
{
    private @Autowired IAuditoriaService auditoriaService;

    @GetMapping(value = "seguridad/auditoria", params = "accion=buscar")
    public List<Auditoria> buscarPistasAuditoriaPorCriterio(
            CriterioBusquedaAuditoria criterioBusquedaAuditoria)
    {
        return auditoriaService.buscarPistasAuditoriaPorCriterio(criterioBusquedaAuditoria);
    }
}