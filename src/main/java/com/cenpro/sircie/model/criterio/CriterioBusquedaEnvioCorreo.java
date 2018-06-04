package com.cenpro.sircie.model.criterio;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioBusquedaEnvioCorreo
{
    private String idDestinoEnvio;
    private Integer codigoFacultad;
    private Integer idCampania;
    private Date fechaInicio;
    private Date fechaFin;
}