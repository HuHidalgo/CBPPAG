package com.cenpro.sircie.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.sircie.mapper.IAlumnoMapper;
import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Alumno;
import com.cenpro.sircie.service.IAlumnoService;
import com.cenpro.sircie.service.impl.MantenibleService;
import com.cenpro.sircie.utilitario.Verbo;

@Service
public class AlumnoService extends MantenibleService<Alumno> implements IAlumnoService
{
    @SuppressWarnings("unused")
    private IAlumnoMapper alumnoMapper;

    private static final String CARGAR = "CARGAR";

    public AlumnoService(@Qualifier("IAlumnoMapper") IMantenibleMapper<Alumno> mapper)
    {
        super(mapper);
        this.alumnoMapper = (IAlumnoMapper) mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Alumno> buscarTodos()
    {
        return this.buscar(new Alumno(), Verbo.GETS);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Alumno> buscarPorCodigoAlumnoTipoAlumno(String codigoAlumno, String tipoAlumno)
    {
        Alumno alumno = Alumno.builder().codigoAlumno(codigoAlumno).tipoAlumno(tipoAlumno).build();
        return this.buscar(alumno, Verbo.GET);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean existeAlumno(String codigoAlumno, String tipoAlumno)
    {
        Alumno alumno = Alumno.builder().codigoAlumno(codigoAlumno).tipoAlumno(tipoAlumno).build();
        return this.existe(alumno);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void registrarAlumno(Alumno alumno)
    {
        this.registrar(alumno);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarAlumnos(List<Alumno> alumnos)
    {
        alumnos.stream().forEach(alumno -> {
            System.out.println(alumno);
            this.registrar(alumno, CARGAR);
            ;
        });
        System.out.println("FIN CARGA");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void actualizarAlumno(Alumno alumno)
    {
        this.actualizar(alumno);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void eliminarAlumno(Alumno alumno)
    {
        this.eliminar(alumno);
    }
}