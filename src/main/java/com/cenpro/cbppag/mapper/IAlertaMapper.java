package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Alerta;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IAlertaMapper extends IMantenibleMapper<Alerta>{
	@Select(value = { "{call MANT_ALERTA( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoAlerta, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tipoAlerta, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.descAlerta, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.diasVerificacion, jdbcType = NUMERIC, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Alerta> mantener(Parametro parametro);
}
