package com.cenpro.cbppag.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ciclo 
{
	private Integer idCiclo;
    private String idModalidad;
	private String idEspecializacion;
    private String nombreModalidad;
    private String nombreEspecializacion;
    
    private Double costoMatriculaUPG;
    private Double costoCiclo;
    
    private Integer numCreditos;
    private Integer numCiclo;
}
