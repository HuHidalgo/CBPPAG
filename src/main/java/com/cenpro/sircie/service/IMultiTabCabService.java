package com.cenpro.sircie.service;

import java.util.List;

import com.cenpro.sircie.model.mantenimiento.MultiTabCab;

public interface IMultiTabCabService extends IMantenibleService<MultiTabCab>
{
    public List<MultiTabCab> buscarTodos();

    public List<MultiTabCab> buscarPorIdTabla(int idTabla);

    public boolean existeIdTabla(Integer idTabla);

    public int registrarMultiTabCab(MultiTabCab multiTabCab);

    public void actualizarMultiTabCab(MultiTabCab multiTabCab);

    public void eliminarMultiTabCab(MultiTabCab multiTabCab);
}