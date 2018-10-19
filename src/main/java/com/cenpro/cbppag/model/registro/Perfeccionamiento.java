package com.cenpro.cbppag.model.registro;

import java.sql.Blob;
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
public class Perfeccionamiento {
	
	private String numeroDocumento;
	private String tipoDocumento;
	private String codigoAlumno;
	private String nombreAlumno;
    private String apellidoAlumno;
    private String correoAlumno;
    private String nombreModalidad;
    private String nombreEspecializacion;      
    private String idEspecializacion;
    private String idModalidad;
    private String idConceptoPago;
    private String conceptoPago;
    private String idTipoPago;  
    private String descTipoPago; 
    private String fechaPago2;
    
    private int numeroCiclo;
    private double costoMatricula;
    private int nroCuotasPagadas;
    private Integer idMatricula;   
	private Integer idPerfeccionamiento;   
    private int nroCuotasPendientes;
    private double montoAPagar;
    private int nroCuotasAPagar;
    private double montoPagado;
    
    private byte[] bytesLeidos;
    private Blob voucher;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaPago;  
}
