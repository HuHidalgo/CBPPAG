package com.cenpro.sircie.service.impl.ingresos;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IClienteMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.ingresos.Cliente;
import com.cenpro.sircie.service.IClienteService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

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
