package com.cenpro.cbppag.model.registro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
	private String codigo;
    private String nombres;
    private String apellidos;
    private String correo;
}
