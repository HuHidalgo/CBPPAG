package com.cenpro.cbppag.service.impl.registro;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IAlumnoMapper;
import com.cenpro.cbppag.mapper.IMatriculaMapper;
import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Alumno;
import com.cenpro.cbppag.model.registro.Matricula;
import com.cenpro.cbppag.service.IMatriculaService;
import com.cenpro.cbppag.service.excepcion.MantenimientoException;
import com.cenpro.cbppag.service.impl.MantenibleService;
import com.cenpro.cbppag.utilitario.ConstantesExcepciones;
import com.cenpro.cbppag.utilitario.Verbo;

@Service
public class MatriculaService extends MantenibleService<Matricula> implements IMatriculaService{
	@SuppressWarnings("unused")
	private IMatriculaMapper matriculaMapper;
	
	public MatriculaService(@Qualifier("IMatriculaMapper") IMantenibleMapper<Matricula> mapper)
    {
        super(mapper);
        this.matriculaMapper = (IMatriculaMapper) mapper;
    }

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Matricula> buscarTodos() {
		return this.buscar(new Matricula(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Matricula> buscarPorId(String codigo) {
		Matricula matricula = Matricula.builder().codigoAlumno(codigo).build();
        return this.buscar(matricula, Verbo.GET);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Matricula> buscarAlumno(String documento) {
		return this.buscar(new Matricula(), Verbo.VERIFICAR_AM);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String registrarMatricula(Matricula matricula) {
		List<Matricula> matriculas = this.registrarAutoIncrementable(matricula);
		
        if (!matriculas.isEmpty() && matriculas.get(0).getCodigoAlumno() != null)
        {
            return matriculas.get(0).getCodigoAlumno();
        } else
        {
            throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
        }	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarMatricula(Matricula matricula) {
		// TODO Auto-generated method stub
		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarMatricula(Matricula matricula) {
		// TODO Auto-generated method stub
		
	}

}
