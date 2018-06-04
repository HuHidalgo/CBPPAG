package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.model.criterio.CriterioBusquedaIngresos;
import com.cenpro.sircie.model.ingresos.ReporteIngresos;

public interface IReporteIngresosMapper {

    @Select(value = { "{call REPORTE_INGRESOS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{fechaInicio, jdbcType = DATE, mode = IN},"
            + "#{fechaFin, jdbcType = DATE, mode = IN},"
            + "#{codigoUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{idConcepto, jdbcType = INTEGER, mode = IN},"
            + "#{codigoCurso, jdbcType = VARCHAR, mode = IN},"
            + "#{nroDocCliente, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<ReporteIngresos> buscarIngresosDiarios(CriterioBusquedaIngresos criterioBusquedaIngresos);

}
