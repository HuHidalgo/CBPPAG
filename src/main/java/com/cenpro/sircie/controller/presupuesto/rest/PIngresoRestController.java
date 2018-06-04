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

import com.cenpro.sircie.model.presupuesto.PresupuestoIngreso;
import com.cenpro.sircie.service.IPIngresoService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/presupuesto/ingreso")
public @RestController class PIngresoRestController {

	private @Autowired IPIngresoService pIngresoService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<PresupuestoIngreso> buscarTodos()
    {
        return pIngresoService.buscarTodos();
    }
	
	@PostMapping
    public ResponseEntity<?> registrarPIngreso(@RequestBody PresupuestoIngreso pIngreso)
    {
		int idPIngreso=pIngresoService.registrarPIngreso(pIngreso);
        return ResponseEntity.ok(pIngresoService.buscarPorId(idPIngreso));
    }

    @PutMapping
    public ResponseEntity<?> actualizarPIngreso(@RequestBody PresupuestoIngreso pIngreso)
    {
    	pIngresoService.actualizarPIngreso(pIngreso);
        return ResponseEntity.ok(pIngresoService.buscarPorId(pIngreso.getIdPIngreso()));
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarPIngreso(@RequestBody PresupuestoIngreso pIngreso)
    {
    	pIngresoService.eliminarPIngreso(pIngreso);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
    
}
