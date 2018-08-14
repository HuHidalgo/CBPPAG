package com.cenpro.cbppag.model.alerta;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertaPago {
	private String nombreAlumno;
	private String apellidoAlumno;
	private String correoAlumno;
	private String conceptoPago;
	private String modalidad;
	private String especializacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaVencimiento;
	
	private String fechaActual;
}
