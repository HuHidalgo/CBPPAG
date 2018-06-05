package com.cenpro.cbppag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.cbppag.mapper.base.IMantenibleMapper;
import com.cenpro.cbppag.model.mantenimiento.Modalidad;
import com.cenpro.cbppag.model.parametro.Parametro;

public interface IModalidadMapper extends IMantenibleMapper<Modalidad>{
	
	@Select(value = { "{call MANT_MODALIDADES ( "
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.idModalidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nombreModalidad, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.descripcion, jdbcType = VARCHAR, mode = IN},"
            + "#{userAudit, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Modalidad> mantener(Parametro parametro);

}
