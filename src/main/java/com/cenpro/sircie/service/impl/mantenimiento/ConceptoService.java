package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IConceptoMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Concepto;
import com.cenpro.sircie.service.IConceptoService;
import com.cenpro.sircie.service.excepcion.MantenimientoException;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class ConceptoService extends MantenibleService<Concepto> implements IConceptoService {

	@SuppressWarnings("unused")
	private IConceptoMapper conceptoMapper;
	
	private static final String GET_CON = "GET_CON";
	
	public ConceptoService(@Qualifier("IConceptoMapper") IMantenibleMapper<Concepto> mapper) {
		super(mapper);
		this.conceptoMapper = (IConceptoMapper) mapper;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Concepto> buscarPorCodigoUnidad(String codigoUnidad) {
		Concepto concepto = Concepto.builder().codigoUnidad(codigoUnidad).build();
        return this.buscar(concepto, GET_CON);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Concepto> buscarPorId(Integer idConcepto) {
		Concepto concepto = Concepto.builder().idConcepto(idConcepto).build();
        return this.buscar(concepto, Verbo.GET_IMP);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Concepto> buscarTodos() {
		return this.buscar(new Concepto(), Verbo.GETS);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Concepto> buscarPorCodigoUnidadIdConcepto(String codigoUnidad, Integer idConcepto) {
		Concepto concepto = Concepto.builder().codigoUnidad(codigoUnidad).idConcepto(idConcepto).build();
		return this.buscar(concepto, Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int registrarConcepto(Concepto concepto) {
		List<Concepto> conceptos = this.registrarAutoIncrementable(concepto);
        if (!conceptos.isEmpty() && conceptos.get(0).getIdConcepto() != null){
            return conceptos.get(0).getIdConcepto();
        } else {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarConcepto(Concepto concepto) {
		this.actualizar(concepto);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarConcepto(Concepto concepto) {
		this.eliminar(concepto);
	}

}
