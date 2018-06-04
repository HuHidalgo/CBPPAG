package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.mantenimiento.Meta;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IMetaMapper extends IMantenibleMapper<Meta>
{
	@Select(value = { "{call MANT_METAS ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroMeta, jdbcType = NUMERIC, mode = IN},"
            + "#{objeto.nomMeta, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.desMeta, jdbcType = VARCHAR, mode = IN},"
			+ "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Meta> mantener(Parametro parametro);
}
