package com.cenpro.cbppag.model.mantenimiento;

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
    
    private Double costoMatriculaUPG;
    private Double costoCiclo; //Solo para diplomatura
    private Integer numCiclo;
    private Integer numCiclos;
}
