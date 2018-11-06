package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.parametro.Parametro;
import com.cenpro.cbppag.model.registro.Perfeccionamiento;

public interface IPerfeccionamientoMapper extends IMantenibleMapper<Perfeccionamiento> {
	@Select(value = { "{call MANT_PERFECCIONAMIENTO( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idPerfeccionamiento, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.tipoDocumento, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.numeroDocumento, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.nombreAlumno, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.apellidoAlumno, jdbcType = VARCHAR, mode = IN},"
			+ "#{objeto.correoAlumno, jdbcType = VARCHAR, mode = IN},"         
            + "#{objeto.fechaPago, jdbcType = DATE, mode = IN},"
            + "#{objeto.nombreEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idConceptoPago, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idTipoPago, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.numeroCiclo, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.nroCuotaAPagar, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.voucher, jdbcType = BLOB, mode = IN},"
            + "#{objeto.idMatricula, jdbcType = INTEGER, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Perfeccionamiento> mantener(Parametro parametro);
}