package com.cenpro.cbppag.model.registro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
	private String codigoPago;
    private String codigoAlumno;
    private Date fechaPago;
    private String concepto;
    private String descModalidad;
    private String idEspecializacion;
    private String descEspecializacion;
    private String descTipoPago;
    private int ciclo;
    private int nroCuotasPendientes;
    private double costoCuota;
    private int nroCuotasAPagar;
    private double montoPagado;
    private String nombresAlumno;
    private String apellidosAlumno;
    private String correoAlumno;
    private String fechaPago2;
    private FileInputStream fileInput;
    private String fileOutput;
}