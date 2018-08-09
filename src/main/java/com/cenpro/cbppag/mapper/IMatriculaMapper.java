package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.parametro.Parametro;
import com.cenpro.cbppag.model.registro.Matricula;

public interface IMatriculaMapper extends IMantenibleMapper<Matricula> {
	@Select(value = { "{call MANT_MATRICULA( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoMatricula, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.conceptoPago, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.estadoCiclo, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.numeroCiclo, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.fechaMatricula, jdbcType = DATE, mode = IN},"
            + "#{objeto.nombreAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.apellidoAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.correoAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.voucher, jdbcType = BLOB, mode = IN},"
            + "#{objeto.tipoPago, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Matricula> mantener(Parametro parametro);
}