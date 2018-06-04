package com.cenpro.sircie.controller.presupuesto.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.model.presupuesto.PresupuestoEgreso;
import com.cenpro.sircie.service.IPEgresoService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/presupuesto/egreso")
public @RestController class PEgresoRestController 
{

	private @Autowired IPEgresoService pEgresoService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<PresupuestoEgreso> buscarTodos()
    {
        return pEgresoService.buscarTodos();
    }
	
	@PostMapping
    public ResponseEntity<?> registrarPEgreso(@RequestBody PresupuestoEgreso pEgreso)
    {
		int idPEgreso = pEgresoService.registrarPEgreso(pEgreso);
        return ResponseEntity.ok(pEgresoService.buscarPorId(idPEgreso));
    }

    @PutMapping
    public ResponseEntity<?> actualizarPEgreso(@RequestBody PresupuestoEgreso pEgreso)
    {
    	pEgresoService.actualizarPEgreso(pEgreso);
        return ResponseEntity.ok(pEgresoService.buscarPorId(pEgreso.getIdPEgreso()));
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarPEgreso(@RequestBody PresupuestoEgreso pEgreso)
    {
    	pEgresoService.eliminarPEgreso(pEgreso);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
