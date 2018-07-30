package com.cenpro.cbppag.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
	private String codigoAlerta;
	private String tipoAlerta;
	private String descAlerta;
	private int diasVerificacion;
}
