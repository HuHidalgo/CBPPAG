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
public class Pago {
	private String codigoAlumno;
	private String nombreAlumno;
    private String apellidoAlumno;
    private String correoAlumno;
    private String nombreModalidad;
    private String nombreEspecializacion;
    private String tipoPago;
    private int numeroCiclo;
    private double costoMatricula;      
    private String idEspecializacion;
    private String idModalidad;
    private String conceptoPago;
    private int nroCuotasPagadas;
    private Blob voucher;
    private String codigoMatricula;   
	private String codigoPago;  
    private String descTipoPago;    
    private int nroCuotasPendientes;
    private double costoCuota;
    private int nroCuotasAPagar;
    private double montoPagado;
    private String fechaPago2;
    private byte[] bytesLeidos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaPago;  
}
