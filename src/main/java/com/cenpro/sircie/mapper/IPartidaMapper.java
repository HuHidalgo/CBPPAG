package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Partida;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IPartidaMapper extends IMantenibleMapper<Partida>
{
	@Select(value = { "{call MANT_PARTIDAS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.clasificador, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.descripcion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.descDetallada, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoTarea, jdbcType = INTEGER, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Partida> mantener(Parametro parametro);
}