package com.cenpro.cbppag.controller.mantenimiento.rest;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.aspecto.anotacion.Audit;
import com.cenpro.cbppag.aspecto.enumeracion.Accion;
import com.cenpro.cbppag.aspecto.enumeracion.Comentario;
import com.cenpro.cbppag.model.mantenimiento.Ciclo;
import com.cenpro.cbppag.service.ICicloService;
import com.cenpro.cbppag.service.excepcion.BadRequestException;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.grupo.accion.IRegistro;

@RequestMapping("/mantenimiento/ciclo")
public @RestController class CicloRestController 
{
	private @Autowired ICicloService cicloService;

	@Audit(accion = Accion.Consulta, comentario = Comentario.Consulta)
	@GetMapping("/{idEspecializacion}")
    public List<Ciclo> buscarCosto(@PathVariable String idEspecializacion)
    {
        return cicloService.buscarPorEspecializacion(idEspecializacion);
    }
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Ciclo> buscarTodos()
    {
        return cicloService.buscarTodos();
    }

	@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
    @PostMapping
    public ResponseEntity<?> registrarCiclo(@Validated({ Default.class, IRegistro.class }) @RequestBody Ciclo ciclo, Errors error)
    {	
		if (error.hasErrors())
        {
            throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
        }
		
        cicloService.registrarCiclo(ciclo);
        return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }

    @PutMapping
    public ResponseEntity<?> actualizarCiclo(@RequestBody Ciclo ciclo)
    {
        cicloService.actualizarCiclo(ciclo);
        return ResponseEntity.ok(cicloService.buscarPorId(ciclo.getIdCiclo(), ciclo.getIdEspecializacion()));
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarCiclo(@RequestBody Ciclo ciclo)
    {
    	cicloService.eliminarCiclo(ciclo);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
