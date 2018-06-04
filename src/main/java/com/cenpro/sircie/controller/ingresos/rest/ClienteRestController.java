package com.cenpro.sircie.controller.ingresos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenpro.sircie.model.ingresos.Cliente;
import com.cenpro.sircie.service.IClienteService;

@RequestMapping("/ingresos/cliente")
public @RestController class ClienteRestController {
	private @Autowired IClienteService clienteService;
	
	@GetMapping("/{nroDocumento}")
	public List<Cliente> buscarPorNroDocumento(@PathVariable String nroDocumento)
    {
		System.out.println(nroDocumento);
        List<Cliente> cli = clienteService.buscarPorNroDocumento(nroDocumento);
        System.out.println(cli);
		return cli;
    }
}
