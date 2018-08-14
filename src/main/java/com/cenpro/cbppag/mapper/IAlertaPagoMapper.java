package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.alerta.AlertaPago;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IAlertaPagoMapper extends IMantenibleMapper<AlertaPago>{
	@Select(value = { "{call MANT_ALERTA_PAGO( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.fechaActual, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<AlertaPago> mantener(Parametro parametro);
}
