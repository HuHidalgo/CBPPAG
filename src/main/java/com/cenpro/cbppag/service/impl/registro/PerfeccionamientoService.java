package com.cenpro.cbppag.service.impl.registro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IPerfeccionamientoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.registro.Perfeccionamiento;
import com.cenpro.cbppag.service.IPerfeccionamientoService;
import com.cenpro.cbppag.service.excepcion.MantenimientoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class PerfeccionamientoService extends MantenibleService<Perfeccionamiento> implements IPerfeccionamientoService{
	@SuppressWarnings("unused")
	private IPerfeccionamientoMapper pagoMapper;
	
	public PerfeccionamientoService(@Qualifier("IPerfeccionamientoMapper") IMantenibleMapper<Perfeccionamiento> mapper)
    {
        super(mapper);
        this.pagoMapper = (IPerfeccionamientoMapper) mapper;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Perfeccionamiento> buscarTodos() {
		return this.buscar(new Perfeccionamiento(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Perfeccionamiento> buscarPorId(int idPerfeccionamiento) {
		Perfeccionamiento pago = Perfeccionamiento.builder().idPerfeccionamiento(idPerfeccionamiento).build();
		return this.buscar(pago, Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Perfeccionamiento> buscarAlumno(String tipoDocumento, String numDocumento) {
		Perfeccionamiento pago = Perfeccionamiento.builder().tipoDocumento(tipoDocumento).numeroDocumento(numDocumento).build();
		return this.buscar(pago, Verbo.VERIFICAR_AM);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarPerfeccionamiento(Perfeccionamiento pago) {
		
		List<Perfeccionamiento> pagos = this.registrarAutoIncrementable(pago);
		if (!pagos.isEmpty() && pagos.get(0).getIdPerfeccionamiento()!= null){
            return pagos.get(0).getIdPerfeccionamiento();
        } else {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void cargarVoucher(Perfeccionamiento pago) {
		this.registrar(pago);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Perfeccionamiento> recuperarVoucher(int idPerfeccionamiento) {
		Perfeccionamiento pago = Perfeccionamiento.builder().idPerfeccionamiento(idPerfeccionamiento).build();
		return this.buscar(pago, Verbo.GET_VOUCHER_PAGO);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarPerfeccionamiento(Perfeccionamiento pago) {
		this.actualizar(pago);
	}

	@Override
	public void actualizarVoucher(Perfeccionamiento pago) {
		this.actualizar(pago);
		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Perfeccionamiento> buscarPerfeccionamiento(String tipoDocumento, String numDocumento) {
		Perfeccionamiento pago = Perfeccionamiento.builder().tipoDocumento(tipoDocumento).numeroDocumento(numDocumento).build();
		List<Perfeccionamiento> listaPago = this.buscar(pago, Verbo.GETS_PERFEC);
		List<Perfeccionamiento> listaPagoAuxiliar = new ArrayList<>();
		if(!listaPago.isEmpty()) {
			Perfeccionamiento pagoAuxiliar = listaPago.get(0);
			for(Perfeccionamiento auxiliar : listaPago) {
				if(!auxiliar.getIdEspecializacion().equals(pagoAuxiliar.getIdEspecializacion())) {
					listaPagoAuxiliar.add(pagoAuxiliar);
				}
				pagoAuxiliar = auxiliar;
			}
			listaPagoAuxiliar.add(pagoAuxiliar);
		}
		return listaPagoAuxiliar;
	}

}
