package com.cenpro.cbppag.model.criterio;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioBusquedaAdministrativaExamenMedicoOdontologico
{
    private Integer numeroRegistro;
    private String anio;
    private String codigoAlumno;
    private String tipoAlumno;
    private String idEstadoExamenMedico;
    private Integer idCampania;
    private Integer codigoFacultad;
    private Integer codigoEscuela;

    private Integer indiceMasticacion;
    private String idMaloclusion;
    private String idHigiene;
    private String idProtesis;
    private Integer piezasFaltantes;
    private Integer piezasObturadas;
    private Integer piezasPorObturar;
    private Integer piezasPorExtraer;
    private String observacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;
}