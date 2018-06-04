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

import com.cenpro.cbppag.model.movimiento.ExamenMedicoTriaje;
import com.cenpro.cbppag.service.IExamenMedicoTriajeService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.grupo.accion.IActualizacion;
import com.cenpro.cbppag.validacion.grupo.accion.IRegistro;

@RequestMapping("/examenmedico/triaje")
public @RestController class ExamenMedicoTriajeRestController
{
    private @Autowired IExamenMedicoTriajeService examenMedicoTriajeService;

    @PostMapping
    public String registrarExamenMedicoTriaje(@Validated({ IRegistro.class, Default.class }) 
    		@RequestBody ExamenMedicoTriaje examenMedicoTriaje, Errors error)
    {
        if (error.hasErrors()) {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoTriajeService.registrarExamenMedicoTriaje(examenMedicoTriaje);
        return ConstantesGenerales.REGISTRO_EXITOSO;
    }
    
    @PutMapping
    public String actualizarExamenMedicoTriaje(@Validated({ IActualizacion.class, Default.class }) 
    		@RequestBody ExamenMedicoTriaje examenMedicoTriaje, Errors error)
    {
        if (error.hasErrors()) {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoTriajeService.actualizarExamenMedicoTriaje(examenMedicoTriaje);
        return ConstantesGenerales.ACTUALIZACION_EXITOSA;
    }

    @DeleteMapping
    public String eliminarExamenMedicoTriaje(@Validated(IActualizacion.class) 
    		@RequestBody ExamenMedicoTriaje examenMedicoTriaje, Errors error)
    {
        if (error.hasErrors()){
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoTriajeService.eliminarExamenMedicoTriaje(examenMedicoTriaje);
        return ConstantesGenerales.ELIMINACION_EXITOSA;
    }
}