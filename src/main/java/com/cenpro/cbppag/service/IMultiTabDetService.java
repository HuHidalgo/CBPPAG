package com.cenpro.cbppag.service;

import java.util.List;

import com.cenpro.cbppag.model.mantenimiento.MultiTabDet;

public interface IMultiTabDetService extends IMantenibleService<MultiTabDet>
{
    public List<MultiTabDet> buscarTodos();

    public List<MultiTabDet> buscarPorIdTablaIdItem(int idTabla, String idItem);

    public boolean existeMultiTabDet(int idTabla, String idItem);

    public void registrarMultiTabDet(MultiTabDet multiTabDet);

    public void actualizarMultiTabDet(MultiTabDet multiTabDet);

    public void eliminarMultiTabDet(MultiTabDet multiTabDet);

    public List<MultiTabDet> buscarPorIdTabla(int idTabla);
}