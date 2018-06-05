package com.cenpro.cbppag.service;

import org.springframework.web.multipart.MultipartFile;

public interface ICargaService
{
    public void cargarAlumno(MultipartFile archivoAlumnos, String tipoAlumno);
}