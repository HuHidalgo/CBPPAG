package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.UnidadPartida;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IUnidadPartidaMapper extends IMantenibleMapper<UnidadPartida>
{
	@Select(value = { "{call MANT_UNIDAD_PARTIDAS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoUnidad, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<UnidadPartida> mantener(Parametro parametro);

}
