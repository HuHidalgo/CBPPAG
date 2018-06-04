package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Unidad;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IUnidadMapper extends IMantenibleMapper<Unidad>{
	
	@Select(value = { "{call MANT_UNIDADES ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.codigoUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nombreUnidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroConcepto, jdbcType = VARCHAR, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Unidad> mantener(Parametro parametro);

}
