package com.cenpro.sircie.service;

import java.util.List;
import com.cenpro.sircie.model.ingresos.Cliente;

public interface IClienteService extends IMantenibleService<Cliente>{
	public List<Cliente> buscarPorNroDocumento(String nroDocumento);
}
