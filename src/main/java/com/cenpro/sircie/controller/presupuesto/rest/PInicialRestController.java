package com.cenpro.sircie.controller.presupuesto.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.model.mantenimiento.Unidad;
import com.cenpro.sircie.model.presupuesto.Inicial;
import com.cenpro.sircie.service.IPInicialService;
import com.cenpro.sircie.utilitario.ConstantesGenerales;

@RequestMapping("/presupuesto/inicial")
public @RestController class PInicialRestController {
	private @Autowired IPInicialService pInicialService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Inicial> buscarTodosT()
    {
        return pInicialService.buscarTodos();
    }
	
	@GetMapping("/{tipo}")
    public List<Inicial> buscarInicial(@PathVariable String tipo)
    {
		System.out.println("Tipo : " + tipo);
        return pInicialService.ultimoAño(tipo);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarInicial(@RequestBody Inicial inicial)
    {
        int id = pInicialService.registrarPInicial(inicial);
        System.out.println("Identificador : " + id);
        return ResponseEntity.ok(pInicialService.buscarPorId(id));
    }
	
	@PutMapping
    public ResponseEntity<?> actualizarInicial(@RequestBody Inicial inicial)
    {
		 System.out.println("Identificador : " + inicial.getIdInicial());
		pInicialService.actualizarPInicial(inicial);
        return ResponseEntity.ok(pInicialService.buscarPorId(inicial.getIdInicial()));
    }
    
    @DeleteMapping
    public ResponseEntity<?> eliminarInicial(@RequestBody Inicial inicial)
    {
    	pInicialService.eliminarPInicial(inicial);
        return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
    }
}
