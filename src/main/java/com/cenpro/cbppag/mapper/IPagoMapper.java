package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.registro.Pago;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IPagoMapper extends IMantenibleMapper<Pago> 
{
	@Select(value = { "{call MANT_PAGOS( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.CodAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idModalidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nombreEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.costoMatricula, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.costoCiclo, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.numCiclos, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.diaVencimiento, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.fechaInicio, jdbcType = DATE, mode = IN},"
            + "#{objeto.fechaFin, jdbcType = DATE, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Pago> mantener(Parametro parametro);
}
