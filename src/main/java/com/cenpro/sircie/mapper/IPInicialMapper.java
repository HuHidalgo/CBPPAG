package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.parametro.Parametro;
import com.cenpro.sircie.model.presupuesto.Inicial;

public interface IPInicialMapper extends IMantenibleMapper<Inicial>
{
	@Select(value = { "{call MANT_PRE_INICIAL( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idInicial, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.inicial, jdbcType = DOUBLE, mode = IN},"
            + "#{objeto.año, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tipo, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Inicial> mantener(Parametro parametro);
}
