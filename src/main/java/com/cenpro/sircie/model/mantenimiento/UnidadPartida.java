package com.cenpro.sircie.model.mantenimiento;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadPartida {
	private String codigoUnidad;
	private String clasificador;
	private String descripcion;
}
