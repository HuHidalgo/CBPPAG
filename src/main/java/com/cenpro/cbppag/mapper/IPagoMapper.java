package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.parametro.Parametro;
import com.cenpro.cbppag.model.registro.Pago;

public interface IPagoMapper extends IMantenibleMapper<Pago> {
	@Select(value = { "{call MANT_PAGOS( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoAlumno, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.fechaPago, jdbcType = DATE, mode = IN},"
            + "#{objeto.idEspecializacion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.conceptoPago, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.numeroCiclo, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.nroCuotasPagadas, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.voucher, jdbcType = BLOB, mode = IN},"
            + "#{objeto.codigoMatricula, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Pago> mantener(Parametro parametro);
}