package com.cenpro.cbppag.controller.movimiento.rest;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.movimiento.ExamenMedicoRadiologico;
import com.cenpro.cbppag.service.IExamenMedicoRadiologicoService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.grupo.IGeneracionNumReg;

@RequestMapping("/examenmedico/radiologico")
public @RestController class ExamenMedicoRadiologicoRestController
{
    private @Autowired IExamenMedicoRadiologicoService examenMedicoRadiologicoService;

    @PostMapping(params = "accion=generar")
    public int generarNumeroRegistro(
            @Validated(IGeneracionNumReg.class) @RequestBody ExamenMedicoRadiologico examenMedicoRadiologico,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        return this.examenMedicoRadiologicoService.generarNumeroRegistro(examenMedicoRadiologico);
    }

    @GetMapping(params = "accion=buscarNumeroRegistroGenerado")
    public List<ExamenMedicoRadiologico> buscarPorEstadoExamenMedicoNumeroRegistroGenerado()
    {
        return this.examenMedicoRadiologicoService
                .buscarPorEstadoExamenMedico(ConstantesGenerales.ESTADO_NUMERO_REGISTRO_GENERADO);
    }

    @GetMapping(params = "accion=buscarTomaRadiologica")
    public List<ExamenMedicoRadiologico> buscarPorEstadoExamenMedicoTomaRadiologica()
    {
        return this.examenMedicoRadiologicoService
                .buscarPorEstadoExamenMedico(ConstantesGenerales.ESTADO_TOMA_RADIOLOGICA);
    }

    @PutMapping(params = "accion=actualizarTomaRadiologica")
    public void actualizarTomaRadiologica(
            @RequestBody ExamenMedicoRadiologico examenMedicoRadiologico)
    {
        this.examenMedicoRadiologicoService.actualizarTomaRadiologica(examenMedicoRadiologico);
    }

    @PostMapping(params = "accion=registrarExamenMedicoRegularRadiologico")
    public void registrarExamenMedicoRegularRadiologico(
            @Validated(Default.class) @RequestBody ExamenMedicoRadiologico examenMedicoRadiologico,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        this.examenMedicoRadiologicoService
                .registrarExamenMedicoRegularRadiologico(examenMedicoRadiologico);
    }
}