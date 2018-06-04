package com.cenpro.cbppag.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IConsultaExamenMedicoRadiologicoMapper;
import com.cenpro.cbppag.model.consulta.administrativa.ConsultaRadiologicoAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoRadiologico;
import com.cenpro.cbppag.service.IConsultaExamenMedicoRadiologicoService;

@Service
public class ConsultaExamenMedicoRadiologicoService
        implements IConsultaExamenMedicoRadiologicoService
{
    private @Autowired IConsultaExamenMedicoRadiologicoMapper consultaExamenMedicoRadiologicoMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ConsultaRadiologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoRadiologico criterioBusquedaAdministrativaExamenMedicoRadiologico)
    {
    	System.out.println("2");
        return consultaExamenMedicoRadiologicoMapper.buscarPorCriterioBusquedaAdministrativa(
                criterioBusquedaAdministrativaExamenMedicoRadiologico);
    }
}