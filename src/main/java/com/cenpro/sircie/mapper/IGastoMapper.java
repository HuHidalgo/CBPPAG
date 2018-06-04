package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.egresos.Gastos;
import com.cenpro.sircie.model.parametro.Parametro;

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