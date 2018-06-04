package com.cenpro.cbppag.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cenpro.cbppag.mapper.IConsultaExamenMedicoLaboratorioMapper;
import com.cenpro.cbppag.model.consulta.administrativa.ConsultaLaboratorioAdministrativo;
import com.cenpro.cbppag.model.criterio.CriterioBusquedaAdministrativaExamenMedicoLaboratorio;
import com.cenpro.cbppag.service.IConsultaExamenMedicoLaboratorioService;

@Service
public class ConsultaExamenMedicoLaboratorioService implements IConsultaExamenMedicoLaboratorioService
{
    private @Autowired IConsultaExamenMedicoLaboratorioMapper consultaExamenMedicoLaboratorioMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ConsultaLaboratorioAdministrativo> buscarPorCriterioBusquedaAdministrativa(
            CriterioBusquedaAdministrativaExamenMedicoLaboratorio criterioBusquedaAdministrativaExamenMedicoLaboratorio)
    {
        return consultaExamenMedicoLaboratorioMapper
                .buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoLaboratorio);
    }
}