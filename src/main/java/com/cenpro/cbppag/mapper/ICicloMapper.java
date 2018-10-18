package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Ciclo;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface ICicloMapper extends IMantenibleMapper<Ciclo> 
{
	@Select(value = { "{call MANT_CICLOS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idCiclo, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.idEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.costoMatriculaUPG, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.costoCiclo, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.numCreditos, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.numCiclo, jdbcType = INTEGER, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Ciclo> mantener(Parametro parametro);
}
