package com.cenpro.sircie.controller.mantenimiento.rest;

import java.util.List;

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

import com.cenpro.sircie.aspecto.anotacion.Audit;
import com.cenpro.sircie.aspecto.enumeracion.Accion;
import com.cenpro.sircie.aspecto.enumeracion.Comentario;
import com.cenpro.sircie.model.mantenimiento.Alumno;
import com.cenpro.sircie.service.IAlumnoService;
import com.cenpro.sircie.service.excepcion.BadRequestException;
import com.cenpro.sircie.utilitario.ConstantesGenerales;
import com.cenpro.sircie.utilitario.ValidatorUtil;
import com.cenpro.sircie.validacion.grupo.secuencia.ISecuenciaValidacionActualizacion;
import com.cenpro.sircie.validacion.grupo.secuencia.ISecuenciaValidacionEliminacion;
import com.cenpro.sircie.validacion.grupo.secuencia.ISecuenciaValidacionRegistro;

@RequestMapping("/mantenimiento/alumno")
public @RestController class AlumnoRestController
{
    private @Autowired IAlumnoService alumnoService;

    @Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
    @GetMapping(params = "accion=buscarTodos")
    public List<Alumno> buscarTodos()
    {
        return alumnoService.buscarTodos();
    }

    @Audit(accion = Accion.Registro, comentario = Comentario.Registro)
    @PostMapping
    public ResponseEntity<?> registrarAlumno(
            @Validated(ISecuenciaValidacionRegistro.class) @RequestBody Alumno alumno, Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        alumnoService.registrarAlumno(alumno);
        return ResponseEntity.ok(alumnoService
                .buscarPorCodigoAlumnoTipoAlumno(alumno.getCodigoAlumno(), alumno.getTipoAlumno()));
    }

    @Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
    @PutMapping
    public ResponseEntity<?> actualizarAlumno(
            @Validated(ISecuenciaValidacionActualizacion.class) @RequestBody Alumno alumno,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        alumnoService.actualizarAlumno(alumno);
        return ResponseEntity.ok(alumnoService
                .buscarPorCodigoAlumnoTipoAlumno(alumno.getCodigoAlumno(), alumno.getTipoAlumno()));
    }

    @Audit(accion = Accion.Eliminacion, comentario = Comentario.Eliminacion)
    @DeleteMapping
    public ResponseEntity<?> eliminarAlumno(
            @Validated(ISecuenciaValidacionEliminacion.class) @RequestBody Alumno alumno,
            Errors error)
    {
        if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
        alumnoService.eliminarAlumno(alumno);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}