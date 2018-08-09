package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IAlertaMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Alerta;
import com.cenpro.cbppag.service.IAlertaService;
import com.cenpro.cbppag.service.excepcion.MantenimientoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class AlertaService extends MantenibleService<Alerta> implements IAlertaService{

	@SuppressWarnings("unused")
    private IAlertaMapper alertaMapper;
	
	public AlertaService(@Qualifier("IAlertaMapper") IMantenibleMapper<Alerta> mapper)
    {
        super(mapper);
        this.alertaMapper = (IAlertaMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Alerta> buscarTodos() {
		return this.buscar(new Alerta(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Alerta> buscarPorId(String codigoAlerta) {
		Alerta alerta = Alerta.builder().codigoAlerta(codigoAlerta).build();
		return this.buscar(alerta, Verbo.GET);
	}

	@Override
	public List<Alerta> buscarMensaje(String tipoAlerta) {
		Alerta alerta = Alerta.builder().tipoAlerta(tipoAlerta).build();
		return this.buscar(alerta, Verbo.GET_MENSAJE);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String registrarAlerta(Alerta alerta) {
		List<Alerta>alertas = this.registrarAutoIncrementable(alerta);
		if (!alertas.isEmpty() && alertas.get(alertas.size()-1).getCodigoAlerta()!= null){
            return alertas.get(alertas.size()-1).getCodigoAlerta();
        } else {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarAlerta(Alerta alerta) {
		this.actualizar(alerta);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarAlerta(Alerta alerta) {
		this.eliminar(alerta);
	}

}
