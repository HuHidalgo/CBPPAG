package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Especialidad;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IEspecialidadMapper extends IMantenibleMapper<Especialidad>
{
    @Select(value = { "{call MANT_ESPECIALIDAD ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idEspecialidad, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.descripcionCorta, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.descripcion, jdbcType = VARCHAR, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Especialidad> mantener(Parametro parametro);
}