package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Curso;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface ICursoMapper extends IMantenibleMapper<Curso>
{
	@Select(value = { "{call MANT_CURSOS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoCurso, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nombreCurso, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroSemestres, jdbcType = INTEGER, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Curso> mantener(Parametro parametro);
}
