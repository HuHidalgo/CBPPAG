package com.cenpro.cbppag.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Modalidad {
	private String idModalidad;
	private String nombreModalidad;
	private String descripcion;
}
