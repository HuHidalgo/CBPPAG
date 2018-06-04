package com.cenpro.sircie.controller.carga.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cenpro.sircie.service.ICargaService;

@RequestMapping("/carga/alumno")
public @RestController class CargaRestController
{
    private @Autowired ICargaService cargaService;

    @PostMapping(value = "/uploadfile/{tipoAlumno}", params = "accion=cargar")
    public void cargarAlumnos(@RequestParam("uploadfile") MultipartFile file,
            @PathVariable String tipoAlumno)
    {
        cargaService.cargarAlumno(file, tipoAlumno);
    }
}