package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Escuela;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IEscuelaMapper extends IMantenibleMapper<Escuela>
{
    @Select(value = { "{call MANT_ESCUELAS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoEscuela, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.codigoFacultad, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.descripcion, jdbcType = VARCHAR, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Escuela> mantener(Parametro parametro);
}