package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.parametro.Parametro;
import com.cenpro.cbppag.model.presupuesto.PresupuestoIngreso;


public interface IPIngresoMapper extends IMantenibleMapper<PresupuestoIngreso>
{
	@Select(value = { "{call MANT_PRE_INGRESOS( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idPIngreso, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.codigoUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idConcepto, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.clasificador, jdbcType = VARCHAR, mode = IN},"
            
            + "#{objeto.idMes, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.año, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.importe, jdbcType = DOUBLE, mode = IN},"
            + "#{objeto.inicial, jdbcType = DOUBLE, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<PresupuestoIngreso> mantener(Parametro parametro);
}
