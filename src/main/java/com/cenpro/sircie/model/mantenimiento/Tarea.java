package com.cenpro.sircie.model.mantenimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarea 
{
	private Integer codigoTarea;
	private String nomTarea;
	
    private String codigoUnidad;
    private String nombreUnidad;
    
    private Integer nroMeta;
    private String nomMeta;
    
    private String clasificador;
    private String descripcion;
    
}
