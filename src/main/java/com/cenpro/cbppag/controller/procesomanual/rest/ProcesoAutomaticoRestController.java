package com.cenpro.cbppag.controller.procesomanual.rest;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.procesoautomatico.ProcesoAutomatico;
import com.cenpro.cbppag.service.IProcesoAutomaticoService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.grupo.accion.IActualizacion;
import com.cenpro.cbppag.validacion.grupo.accion.IRegistro;

@RequestMapping("/mantenimiento/procesoAutomatico")
public @RestController class ProcesoAutomaticoRestController
{
    private @Autowired IProcesoAutomaticoService procesoAutomaticoService;

    @GetMapping(params = "accion=buscarTodos")
    public List<ProcesoAutomatico> buscarTodos()
    {
        return procesoAutomaticoService.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<?> registrarProcesoAutomatico(@Validated({ IRegistro.class,
            Default.class }) @RequestBody ProcesoAutomatico procesoAutomatico, Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        procesoAutomaticoService.registrarProcesoAutomatico(procesoAutomatico);
        return ResponseEntity.ok(procesoAutomaticoService
                .buscarPorIdProcesoAutomatico(procesoAutomatico.getIdProcesoAutomatico()));
    }

    @PutMapping
    public ResponseEntity<?> actualizarProcesoAutomatico(@Validated({ IActualizacion.class,
            Default.class }) @RequestBody ProcesoAutomatico procesoAutomatico, Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        procesoAutomaticoService.actualizarProcesoAutomatico(procesoAutomatico);
        return ResponseEntity.ok(procesoAutomaticoService
                .buscarPorIdProcesoAutomatico(procesoAutomatico.getIdProcesoAutomatico()));
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarProcesoAutomatico(
            @Validated(IActualizacion.class) @RequestBody ProcesoAutomatico procesoAutomatico,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        procesoAutomaticoService.eliminarProcesoAutomatico(procesoAutomatico);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}