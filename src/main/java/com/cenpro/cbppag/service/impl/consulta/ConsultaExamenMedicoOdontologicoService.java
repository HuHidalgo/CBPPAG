package com.cenpro.cbppag.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IConsultaExamenMedicoOdontologicoMapper;
import com.cenpro.cbppag.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;
import com.cenpro.cbppag.service.IConsultaExamenMedicoOdontologicoService;

@Service
public class ConsultaExamenMedicoOdontologicoService implements IConsultaExamenMedicoOdontologicoService
{
    private @Autowired IConsultaExamenMedicoOdontologicoMapper consultaExamenMedicoOdontologicoMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico)
    {
        return consultaExamenMedicoOdontologicoMapper.buscarPorCriterioBusquedaAdministrativa(
                criterioBusquedaAdministrativaExamenMedicoOdontologico);
    }
}