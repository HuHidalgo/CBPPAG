package com.cenpro.sircie.model.criterio;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioBusquedaIngresos {
	    
	private String verbo;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;
    
    private String codigoUnidad;
    private String idConcepto;
    private String codigoCurso;
    private String nroDocCliente;
    
    private String descripcionRangoFecha;
    
}
