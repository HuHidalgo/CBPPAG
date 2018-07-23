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
public class Matricula {
	private String codigoMatricula;
    private String codigoAlumno;
    private String idModalidad;
    private String nombreModalidad;
    private String idEspecializacion;
    private String nombreEspecializacion;
    private String conceptoPago;
    private String estadoCiclo;
    private String numeroCiclo;
    private Date fechaMatricula;
    private FileInputStream fileInput;
    private String fileOutput;
    private double costoMatricula;
    private String nombreAlumno;
    private String apellidoAlumno;
    private String correoAlumno;
    private String tipoPago;
    private String descTipoPago;
    private String fechaMatricula2;
}
