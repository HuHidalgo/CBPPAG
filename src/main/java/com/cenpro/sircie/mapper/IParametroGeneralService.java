package com.cenpro.sircie.mapper;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.ParametroGeneral;
import com.cenpro.sircie.service.IMantenibleService;

public interface IParametroGeneralService extends IMantenibleService<ParametroGeneral>
{
    public List<ParametroGeneral> buscarTodos();
    
    public void actualizarParametroGeneral(ParametroGeneral parametroGeneral);
    
    public Integer buscarAnioInicio();
    
    public String buscarCorreoSUM();
    
    public String buscarCorreoClinica();
}