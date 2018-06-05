package com.cenpro.cbppag.service;

import java.util.List;
import com.cenpro.cbppag.model.mantenimiento.Meta;


public interface IMetaService extends IMantenibleService<Meta>
{
	public List<Meta> buscarTodos();
    
    public List<Meta> buscarPorId();
    
    public void registrarMeta(Meta meta);
    
    public void actualizarMeta(Meta meta);
    
    public void eliminarMeta(Meta meta);
}
