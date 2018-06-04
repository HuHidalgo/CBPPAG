package com.cenpro.cbppag.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IConsultaTriajeMapper;
import com.cenpro.cbppag.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaTriaje;
import com.cenpro.cbppag.service.IConsultaTriajeService;

@Service
public class ConsultaTriajeService implements IConsultaTriajeService
{
    private @Autowired IConsultaTriajeMapper consultaTriajeMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaTriaje criterioBusquedaAdministrativaTriaje)
    {
        return consultaTriajeMapper.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaTriaje);
    }
}