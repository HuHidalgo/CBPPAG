package com.cenpro.cbppag.controller.movimiento.rest;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.movimiento.ExamenMedicoOdontologico;
import com.cenpro.cbppag.service.IExamenMedicoOdontologicoService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.grupo.accion.IActualizacion;
import com.cenpro.cbppag.validacion.grupo.accion.IRegistro;

@RequestMapping("/examenmedico/odontologia")
public @RestController class ExamenMedicoOdontologicoRestController
{
    private @Autowired IExamenMedicoOdontologicoService examenMedicoOdontologicoService;

    @PostMapping
    public String registrarExamenMedicoOdontologico(@Validated({ Default.class,
            IRegistro.class }) @RequestBody ExamenMedicoOdontologico examenMedicoOdontologico,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoOdontologicoService
                .registrarExamenMedicoOdontologico(examenMedicoOdontologico);
        return ConstantesGenerales.REGISTRO_EXITOSO;
    }
    
    @PutMapping
    public String actualizarExamenMedicoOdontologico(
            @Validated({ IActualizacion.class,
                    Default.class }) @RequestBody ExamenMedicoOdontologico examenMedicoOdontologico,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoOdontologicoService
                .actualizarExamenMedicoOdontologico(examenMedicoOdontologico);
        return ConstantesGenerales.ACTUALIZACION_EXITOSA;
    }

    @DeleteMapping
    public String eliminarExamenMedicoOdontologico(
            @Validated(IActualizacion.class) @RequestBody ExamenMedicoOdontologico examenMedicoOdontologico,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoOdontologicoService
                .eliminarExamenMedicoOdontologico(examenMedicoOdontologico);
        return ConstantesGenerales.ELIMINACION_EXITOSA;
    }
}