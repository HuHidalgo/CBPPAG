package com.cenpro.sircie.model.ingresos;

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
public class ReporteIngresos {
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "EST")
    private Date fechaVF;
    
    private String codigoUnidad;
    private String nombreUnidad;
    private String nroConceptoUnidad;
    
    private String nroConceptoEsp;
    private String nomConceptoEsp;
    private Integer idConcepto;
    
    private Integer codigoCurso;
    private String nombreCurso;
    private Integer nroSemestres;
    
    private String nroDocCliente;
    private String nombresCliente;
    private String apellidosCliente;
    
    private Double importe;
    private Double ingresoTotal;
	private String nroReciboIngreso;
    
	private Integer idReporteIngreso;
    private Double importeEnero;
    private Double importeFebrero;
    private Double importeMarzo;
    private Double importeAbril;
    private Double importeMayo;
    private Double importeJunio;
    private Double importeJulio;
    private Double importeAgosto;
    private Double importeSetiembre;
    private Double importeOctubre;
    private Double importeNoviembre;
    private Double importeDiciembre;

}
