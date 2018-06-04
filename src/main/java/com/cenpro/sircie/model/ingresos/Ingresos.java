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
public class Ingresos {
	
	private Integer idIngreso;
	
	private String codigoUnidad;
	private String nombreUnidad;
	private String nroConceptoUnidad;
	
	private String nombresCliente;
	private String apellidosCliente;
	private String tipoDocCliente;
	private String nroDocCliente;
	
	private String clasificador;
	private String descripcionPartida;
	
	private Integer idConcepto;
	private String nroConceptoEsp;
	private String nomConceptoEsp;
	private Double importe;
	
	private Double importeTotal;
	private Double descuento;
	private Double importeDescontado;
	private String descDescuento;
	
	private String codigoCurso;
	private String nombreCurso;
	private int nroCuota;
	
	private String tipoMoneda;
	private Double tipoCambio;
	private String nroVoucher;
	private String nroFactura;
	private String nroReciboIngreso;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaVF;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaRI;

}
