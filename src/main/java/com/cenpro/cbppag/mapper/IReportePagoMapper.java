package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.parametro.Parametro;
import com.cenpro.cbppag.model.reporte.ReportePago;

public interface IReportePagoMapper extends IMantenibleMapper<ReportePago> {
	@Select(value = { "{call MANT_REPORTE_PAGO( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idModalidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.numeroCiclo, jdbcType = NUMERIC, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)	
	public List<ReportePago> mantener(Parametro parametro);
}
