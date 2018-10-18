package com.cenpro.cbppag.model.registro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matricula{
	
	private String numeroDocumento;
	private String tipoDocumento;
    private String codigoAlumno;
    private String idModalidad;
    private String nombreModalidad;
    private String idEspecializacion;
    private String nombreEspecializacion;
    private String idConceptoPago;
    private String conceptoPago;    
    private String nombreAlumno;
    private String apellidoAlumno;
    private String correoAlumno;
    private String idTipoPago;
    private String descTipoPago;
    private String fechaMatricula3;
    
    private int estadoCiclo;
    private int numeroCiclo;
    private double costoMatricula;
	private Integer idMatricula;
    private Blob voucher;
    private String nombreArchivo;
    
    private byte[] bytesLeidos;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaMatricula;
}