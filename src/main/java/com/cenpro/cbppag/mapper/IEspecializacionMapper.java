package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Especializacion;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IEspecializacionMapper extends IMantenibleMapper<Especializacion> 
{
	@Select(value = { "{call MANT_ESPECIALIZACIONES ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idModalidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nombreEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.numCiclos, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.numCiclo, jdbcType = INTEGER, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Especializacion> mantener(Parametro parametro);
}
