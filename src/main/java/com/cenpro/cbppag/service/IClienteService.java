package com.cenpro.cbppag.service;

import java.util.List;
import com.cenpro.cbppag.model.ingresos.Cliente;

public interface IClienteService extends IMantenibleService<Cliente>{
	public List<Cliente> buscarPorNroDocumento(String nroDocumento);
}
