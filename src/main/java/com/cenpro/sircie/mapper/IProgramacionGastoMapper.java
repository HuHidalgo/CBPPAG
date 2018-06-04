package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.egresos.ProgramacionGastos;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IProgramacionGastoMapper extends IMantenibleMapper<ProgramacionGastos>
{
    @Select(value = { "{call MANT_PROGRAMACION_GASTO ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idProgramacionGasto, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.nroProgramacionGasto, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.estado, jdbcType = BIT, mode = IN},"
            + "#{objeto.fechaEnvio, jdbcType = DATE, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<ProgramacionGastos> mantener(Parametro parametro);
}