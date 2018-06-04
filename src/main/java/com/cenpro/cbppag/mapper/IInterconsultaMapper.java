package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.movimiento.Interconsulta;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IInterconsultaMapper extends IMantenibleMapper<Interconsulta>
{
    @Select(value = { "{call MANT_INTERCONSULTA ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.secuencia, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.numeroRegistro, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.anio, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idEspecialidad, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.motivo, jdbcType = VARCHAR, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Interconsulta> mantener(Parametro parametro);
}