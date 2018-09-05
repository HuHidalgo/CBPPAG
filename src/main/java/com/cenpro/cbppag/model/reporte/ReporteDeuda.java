package com.cenpro.cbppag.model.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDeuda {
	private String idModalidad;
	private String nombreModalidad;
	private String idEspecializacion;
	private String nombreEspecializacion;
	private String codigoAlumno;
	private String nombreAlumno;
	private String apellidoAlumno;
	private int numeroCiclo;
	private String ciclo;
	private int numeroCuota;
	private double montoDeuda;
}
