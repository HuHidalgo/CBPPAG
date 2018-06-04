package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.ParametroGeneral;

public interface IParametroGeneralService extends IMantenibleService<ParametroGeneral>
{
    public List<ParametroGeneral> buscarTodos();
    
    public void actualizarParametroGeneral(ParametroGeneral parametroGeneral);
    
    public Integer buscarAnioInicio();
    
    public String buscarCorreoSUM();
    
    public String buscarCorreoClinica();
}