package com.cenpro.cbppag.model.mantenimiento;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Especializacion 
{
	private String idEspecializacion;
    private String idModalidad;
    private String nombreModalidad;
    private String nombreEspecializacion;
    
    private Double costoMatricula;
    private Double costoCiclo;
    private Double costoCuota;
    private Integer numCiclos;
    private Integer diaVencimiento;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaFin;
        
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
}
