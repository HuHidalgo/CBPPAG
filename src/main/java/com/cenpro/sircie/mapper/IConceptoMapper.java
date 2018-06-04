package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Concepto;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IConceptoMapper extends IMantenibleMapper<Concepto>{
	@Select(value = { "{call MANT_CONCEPTOS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idConcepto, jdbcType = INTEGER, mode = IN},"
            + "#{objeto.codigoUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroConceptoEsp, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nomConceptoEsp, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.descConceptoEsp, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.importe, jdbcType = NUMERIC, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Concepto> mantener(Parametro parametro);

}
