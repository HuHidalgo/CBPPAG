package com.cenpro.cbppag.service.impl.ingresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IClienteMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.ingresos.Cliente;
import com.cenpro.cbppag.service.IClienteService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class ClienteService extends MantenibleService<Cliente> implements IClienteService{

	@SuppressWarnings("unused")
    private IClienteMapper clienteMapper;


    public ClienteService(@Qualifier("IClienteMapper") IMantenibleMapper<Cliente> mapper)
    {
        super(mapper);
        this.clienteMapper = (IClienteMapper) mapper;
    }
    
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Cliente> buscarPorNroDocumento(String nroDocumento) {
        Cliente cliente = Cliente.builder().nroDocumento(nroDocumento).build();
        System.out.println("Cliente : "+cliente.getNroDocumento());
        return this.buscar(cliente, Verbo.GET);
	}

}
