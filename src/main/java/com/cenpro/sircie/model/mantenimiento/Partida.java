package com.cenpro.sircie.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partida 
{
	private String clasificador;
	private String descripcion;
	private String descDetallada;
	
	private Integer codigoTarea;
	private String nomTarea;
}
