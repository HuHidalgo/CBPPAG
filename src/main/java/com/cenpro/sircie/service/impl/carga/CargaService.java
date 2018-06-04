package com.cenpro.sircie.service.impl.carga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cenpro.sircie.model.mantenimiento.Alumno;
import com.cenpro.sircie.model.mantenimiento.Alumno.AlumnoBuilder;
import com.cenpro.sircie.service.IAlumnoService;
import com.cenpro.sircie.service.ICargaService;
import com.cenpro.sircie.service.excepcion.CargaArchivoException;
import com.cenpro.sircie.utilitario.ConstantesExcepciones;
import com.cenpro.sircie.utilitario.ConstantesFormatosExcel;

@Service
public class CargaService implements ICargaService
{
    private @Autowired IAlumnoService alumnoService;

    public void cargarAlumno(MultipartFile archivoAlumnos, String tipoAlumno)
    {
        List<Alumno> alumnos = new ArrayList<>();

        boolean finExcel = false;
        XSSFWorkbook workbook = null;
        XSSFSheet worksheet = null;
        int fila = 1;
        try
        {
            workbook = new XSSFWorkbook(archivoAlumnos.getInputStream());
        } catch (IOException e)
        {
            throw new CargaArchivoException(ConstantesExcepciones.ERROR_LECTURA_ARCHIVO);
        }
        worksheet = workbook.getSheetAt(0);
        worksheet.getRow(3);
        fila = ConstantesFormatosExcel.CANTIDAD_FILA_INICIO;
        int ultimaFila = worksheet.getLastRowNum() - ConstantesFormatosExcel.CANTIDAD_FILA_FIN;

        while (fila <= worksheet.getLastRowNum() && !finExcel)
        {
            XSSFRow row = worksheet.getRow(fila);

            AlumnoBuilder alumno = Alumno.builder();

            // CODIGO ALUMNO
            Cell codigoAlumno = row.getCell(ConstantesFormatosExcel.COLUMNA_CODIGO_ALUMNO);
            if (codigoAlumno == null)
            {
                finExcel = true;
                continue;
            }
            codigoAlumno.setCellType(Cell.CELL_TYPE_STRING);
            String codigo = codigoAlumno.getStringCellValue();
            alumno.codigoAlumno(codigoAlumno.getStringCellValue().trim());

            // TIPO ALUMNO
            alumno.tipoAlumno(tipoAlumno);

            // APELLIDO PATERNO
            Cell apellidoPaterno = row.getCell(ConstantesFormatosExcel.COLUMNA_APELLIDO_PATERNO);
            apellidoPaterno.setCellType(Cell.CELL_TYPE_STRING);
            alumno.apellidoPaterno(
                    Optional.ofNullable(apellidoPaterno.getStringCellValue()).orElse("").trim());

            // APELLIDO MATERNO
            Cell apellidoMaterno = row.getCell(ConstantesFormatosExcel.COLUMNA_APELLIDO_MATERNO);
            apellidoMaterno.setCellType(Cell.CELL_TYPE_STRING);
            alumno.apellidoMaterno(apellidoMaterno.getStringCellValue().trim());

            // NOMBRES
            Cell nombres = row.getCell(ConstantesFormatosExcel.COLUMNA_NOMBRES);
            nombres.setCellType(Cell.CELL_TYPE_STRING);
            alumno.nombres(nombres.getStringCellValue().trim());

            // SEXO
            Cell sexo = row.getCell(ConstantesFormatosExcel.COLUMNA_SEXO);
            sexo.setCellType(Cell.CELL_TYPE_STRING);
            String idSexo = sexo.getStringCellValue().trim();
            alumno.idSexo(idSexo.length() == 1 ? idSexo : "N");

            // FECHA NACIMIENTO
            Cell fechaNacimiento = row.getCell(ConstantesFormatosExcel.COLUMNA_FECHA_NACIMIENTO);
            try
            {
                alumno.fechaNacimiento(fechaNacimiento.getDateCellValue());
            } catch (Exception e)
            {
                alumno.fechaNacimiento(null);
            }

            // TIPO DOCUMENTO
            Cell tipoDocumento = row.getCell(ConstantesFormatosExcel.COLUMNA_TIPO_DOCUMENTO);
            tipoDocumento.setCellType(Cell.CELL_TYPE_STRING);
            alumno.idTipoDocumento(tipoDocumento.getStringCellValue().trim());

            // NUMERO DOCUMENTO
            Cell numeroDocumento = row.getCell(ConstantesFormatosExcel.COLUMNA_NUMERO_DOCUMENTO);
            numeroDocumento.setCellType(Cell.CELL_TYPE_STRING);
            alumno.numeroDocumento(numeroDocumento.getStringCellValue().trim());

            // CORREO INSTITUCIONAL
            Cell correoInstitucional = row
                    .getCell(ConstantesFormatosExcel.COLUMNA_CORREO_INSTITUCIONAL);
            correoInstitucional.setCellType(Cell.CELL_TYPE_STRING);
            alumno.correoInstitucional(correoInstitucional.getStringCellValue().trim());

            // CORREO PERSONAL
            Cell correoPersonal = row.getCell(ConstantesFormatosExcel.COLUMNA_CORREO_PERSONAL);
            correoPersonal.setCellType(Cell.CELL_TYPE_STRING);
            alumno.correoPersonal(correoPersonal.getStringCellValue().trim());

            // DIRECCION
            Cell direccion = row.getCell(ConstantesFormatosExcel.COLUMNA_DIRECCION);
            direccion.setCellType(Cell.CELL_TYPE_STRING);
            alumno.direccion(direccion.getStringCellValue().trim());

            // TELEFONO FIJO
            Cell telefonoFijo = row.getCell(ConstantesFormatosExcel.COLUMNA_TELEFONO_FIJO);
            telefonoFijo.setCellType(Cell.CELL_TYPE_STRING);
            alumno.telefonoFijo(telefonoFijo.getStringCellValue().trim());

            // TELEFONO MOVIL
            Cell telefonoMovil = row.getCell(ConstantesFormatosExcel.COLUMNA_TELEFONO_MOVIL);
            telefonoMovil.setCellType(Cell.CELL_TYPE_STRING);
            alumno.telefonoMovil(telefonoMovil.getStringCellValue().trim());

            // CODIGO ESCUELA
            Cell codigoEscuela = row.getCell(ConstantesFormatosExcel.COLUMNA_CODIGO_ESCUELA);
            codigoEscuela.setCellType(Cell.CELL_TYPE_STRING);
            try
            {
                alumno.codigoEscuela(Integer.parseInt(codigoEscuela.getStringCellValue().trim()));
            } catch (Exception e)
            {
                e.printStackTrace();
                alumno.codigoEscuela(0);
            }

            // CODIGO FACULTAD
            Cell codigoFacultad = row.getCell(ConstantesFormatosExcel.COLUMNA_CODIGO_FACULTAD);
            codigoFacultad.setCellType(Cell.CELL_TYPE_STRING);
            try
            {
                alumno.codigoFacultad(Integer.parseInt(codigoFacultad.getStringCellValue().trim()));
            } catch (Exception e)
            {
                e.printStackTrace();
                alumno.codigoFacultad(0);
            }

            // DISCAPACIDAD
            Cell discapacidad = row.getCell(ConstantesFormatosExcel.COLUMNA_DISCAPACIDAD);
            discapacidad.setCellType(Cell.CELL_TYPE_STRING);
            alumno.idDiscapacidad(discapacidad.getStringCellValue().trim());

            alumnos.add(alumno.build());
            fila++;
        }
        try
        {
            workbook.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("FIN LECTURA EXCEL");
        System.out.println("ALUMNO: " + alumnos.size());
        alumnoService.registrarAlumnos(alumnos);
    }
}