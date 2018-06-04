package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.ingresos.Ingresos;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IRegistroIngresoMapper extends IMantenibleMapper<Ingresos>{
	
	@Select(value = { "{call MANT_INGRESOS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idIngreso, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.codigoUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.clasificador, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroDocCliente, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nombresCliente, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.apellidosCliente, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tipoDocCliente, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idConcepto, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.importe, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.importeDescontado, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.descDescuento, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoCurso, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroCuota, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.tipoMoneda, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tipoCambio, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.nroVoucher, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroFactura, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.fechaVF, jdbcType = DATE, mode = IN},"
            + "#{objeto.nroReciboIngreso, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.fechaRI, jdbcType = DATE, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
	public List<Ingresos> mantener(Parametro parametro);
	
}
