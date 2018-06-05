package com.cenpro.cbppag.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.ICursoMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Concepto;
import com.cenpro.cbppag.model.mantenimiento.Curso;
import com.cenpro.cbppag.service.ICursoService;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class CursoService extends MantenibleService<Curso> implements ICursoService
{
	@SuppressWarnings("unused")
    private ICursoMapper cursoMapper;
	
	private static final String GET_CUR = "GET_CUR";
		
	public CursoService(@Qualifier("ICursoMapper") IMantenibleMapper<Curso> mapper)
    {
        super(mapper);
        this.cursoMapper = (ICursoMapper) mapper;
    }
		
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Curso> buscarPorCodigoUnidadCodigoCurso(String codCurso, String codUnidad) {
		Curso curso = Curso.builder().codigoCurso(codCurso).codigoUnidad(codUnidad).build();
		return this.buscar(curso, Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Curso> buscarPorCodigoUnidad(String codigoUnidad) {
		
		Curso curso = Curso.builder().codigoUnidad(codigoUnidad).build();
        return this.buscar(curso, GET_CUR);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Curso> buscarTodos() {

		return this.buscar(new Curso(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarCurso(Curso curso) {
		this.registrar(curso);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarCurso(Curso curso) {
		this.actualizar(curso);	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarCurso(Curso curso) {
		this.eliminar(curso);
	}
}
