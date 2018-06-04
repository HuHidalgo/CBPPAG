package com.cenpro.sircie.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unidad {
	private String codigoUnidad;
	private String nombreUnidad;
	private String nroConcepto;
}
