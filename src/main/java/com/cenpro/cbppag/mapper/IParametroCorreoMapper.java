package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.ParametroCorreo;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IParametroCorreoMapper extends IMantenibleMapper<ParametroCorreo>
{
    @Select(value = { "{call MANT_PARAMETRO_CORREO ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tokenUrl, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.oAuthClienteId, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.oAuthSecretId, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.refreshToken, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.accessToken, jdbcType = VARCHAR, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<ParametroCorreo> mantener(Parametro parametro);
}