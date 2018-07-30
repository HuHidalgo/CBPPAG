package com.cenpro.cbppag.controller.ingresos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.cbppag.model.registro.Pago;
import com.cenpro.cbppag.service.IPagoService;
import com.cenpro.cbppag.utilitario.ConstantesGenerales;

@RequestMapping("/ingresos/pago")
public @RestController class PagoRestController {

	private @Autowired IPagoService pagoService;
	
	@GetMapping(params = "accion=buscarTodos")
    public List<Pago> buscarTodos()
    {
        return pagoService.buscarTodos();
    }
	
	@GetMapping("/{codAlumno}")
    public List<Pago> verificarAlumno(@PathVariable String codAlumno)
    {
        return pagoService.buscarAlumno(codAlumno);
    }
	
	@PostMapping
    public ResponseEntity<?> registrarPago(@RequestBody Pago pago)
    {
		System.out.println("Pago : "+pago.getCodigoAlumno());
		pagoService.registrarPago(pago);
		return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
    }
}
