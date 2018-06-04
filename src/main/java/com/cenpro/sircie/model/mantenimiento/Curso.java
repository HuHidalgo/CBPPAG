package com.cenpro.sircie.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curso 
{
	private String codigoCurso;
	private String nombreCurso;
	private Integer nroSemestres;
	
	private String codigoUnidad;
	private String nroConceptoUnidad;
	private String nombreUnidad;
}
