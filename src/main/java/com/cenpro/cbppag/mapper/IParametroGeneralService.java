package com.cenpro.cbppag.mapper;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.ParametroGeneral;
import com.cenpro.cbppag.service.IMantenibleService;

public interface IParametroGeneralService extends IMantenibleService<ParametroGeneral>
{
    public List<ParametroGeneral> buscarTodos();
    
    public void actualizarParametroGeneral(ParametroGeneral parametroGeneral);
    
    public Integer buscarAnioInicio();
    
    public String buscarCorreoSUM();
    
    public String buscarCorreoClinica();
}