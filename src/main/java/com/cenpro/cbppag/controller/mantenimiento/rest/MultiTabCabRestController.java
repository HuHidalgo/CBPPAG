package com.cenpro.cbppag.controller.mantenimiento.rest;

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

import com.cenpro.cbppag.aspecto.anotacion.Audit;
import com.cenpro.cbppag.aspecto.enumeracion.Accion;
import com.cenpro.cbppag.aspecto.enumeracion.Comentario;
import com.cenpro.cbppag.aspecto.enumeracion.Dato;
import com.cenpro.cbppag.aspecto.enumeracion.Tipo;
import com.cenpro.cbppag.model.mantenimiento.MultiTabCab;
import com.cenpro.cbppag.service.IMultiTabCabService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.grupo.accion.IActualizacion;

@Audit(tipo = Tipo.MulTabCab, datos = Dato.MultiTabCab)
@RequestMapping("/mantenimiento/multiTabCab")
public @RestController class MultiTabCabRestController
{
    private @Autowired IMultiTabCabService multiTabCabService;

    @Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
    @GetMapping(params = "accion=buscarTodos")
    public List<MultiTabCab> buscarTodos()
    {
        return multiTabCabService.buscarTodos();
    }

    @Audit(accion = Accion.Registro, comentario = Comentario.Registro)
    @PostMapping
    public ResponseEntity<?> registrarMultiTabCab(
            @Validated(Default.class) @RequestBody MultiTabCab multiTabCab, Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        int idTabla = multiTabCabService.registrarMultiTabCab(multiTabCab);
        return ResponseEntity.ok(multiTabCabService.buscarPorIdTabla(idTabla));
    }

    @Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
    @PutMapping
    public ResponseEntity<?> actualizarMultiTabCab(@Validated({ Default.class,
            IActualizacion.class }) @RequestBody MultiTabCab multiTabCab, Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        multiTabCabService.actualizarMultiTabCab(multiTabCab);
        return ResponseEntity.ok(multiTabCabService.buscarPorIdTabla(multiTabCab.getIdTabla()));
    }

    @Audit(accion = Accion.Eliminacion, comentario = Comentario.Eliminacion)
    @DeleteMapping
    public ResponseEntity<?> eliminarMultiTabCab(
            @Validated(IActualizacion.class) @RequestBody MultiTabCab multiTabCab, Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        multiTabCabService.eliminarMultiTabCab(multiTabCab);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}