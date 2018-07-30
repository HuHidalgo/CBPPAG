package com.cenpro.cbppag.model.registro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import javax.servlet.http.Part;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Blob;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matricula{
	private String codigoMatricula;
    private String codigoAlumno;
    private String idModalidad;
    private String nombreModalidad;
    private String idEspecializacion;
    private String nombreEspecializacion;
    private String conceptoPago;
    private double estadoCiclo;
    private double numeroCiclo;
    private FileInputStream archivo;
    private File pdf;
    private String fileOutput;
    private double costoMatricula;
    private String nombreAlumno;
    private String apellidoAlumno;
    private String correoAlumno;
    private String tipoPago;
    private String descTipoPago;
    private String fechaMatricula3;
    private Blob voucher  = null;
    private String nombreArchivo;
    private Part docV;

    private byte[] bytesLeidos;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "EST")
    private Date fechaMatricula;
}