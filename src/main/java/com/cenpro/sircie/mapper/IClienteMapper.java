package com.cenpro.sircie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.cenpro.sircie.mapper.base.IMantenibleMapper;
import com.cenpro.sircie.model.ingresos.Cliente;
import com.cenpro.sircie.model.parametro.Parametro;

public interface IClienteMapper  extends IMantenibleMapper<Cliente>{
	@Select(value = { "{call MANT_CLIENTES ( " 
            + "#{verbo, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nroDocumento, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.nomCliente, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.apeCliente, jdbcType = VARCHAR, mode = IN},"
            + "#{objeto.tipoDoc, jdbcType = VARCHAR, mode = IN})}" })
    @Options(statementType = StatementType.CALLABLE)
    public List<Cliente> mantener(Parametro parametro);
}
