package com.cenpro.cbppag.model.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportePago {
	private String idModalidad;
	private String nombreModalidad;
	private String idEspecializacion;
	private String nombreEspecializacion;
	private String codigoAlumno;
	private String nombreAlumno;
	private String apellidoAlumno;
	private String conceptoPago;
	private int numeroCiclo;
	private int cuotasPagadas;
	private double montoPagado;
}
