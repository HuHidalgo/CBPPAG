package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.egresos.Gastos;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IGastoMapper extends IMantenibleMapper<Gastos>
{
    @Select(value = { "{call MANT_GASTOS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idGasto, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.idEgreso, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.nroMeta, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.codigoTarea, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.clasificador, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tipoAdquisicion, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.detalle, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.cantidad, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.precioUnitario, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.montoTotal, jdbcType = NUMERIC, mode = IN})}"})
    @Options(statementType = StatementType.CALLABLE)
    public List<Gastos> mantener(Parametro parametro);
}