package com.cenpro.cbppag.model.movimiento;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.cenpro.cbppag.utilitario.MultiTablaUtil;
import com.cenpro.cbppag.validacion.CodigoAlumno;
import com.cenpro.cbppag.validacion.IdCampania;
import com.cenpro.cbppag.validacion.MultitabDet;
import com.cenpro.cbppag.validacion.NumRegRadiologico;
import com.cenpro.cbppag.validacion.grupo.IGeneracionNumReg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CodigoAlumno(existe = true, groups = IGeneracionNumReg.class)
public @NumRegRadiologico class ExamenMedicoRadiologico
{
    private Integer numeroRegistro;
    private String anio;

    @MultitabDet(campoIdItem = "idResultadoRadiologico", idTabla = MultiTablaUtil.TABLA_RESULTADO_RADIOLOGICO, existe = true, min = 1, max = 1)
    private String idResultadoRadiologico;

    @Length(max = 250, message = "{Length.ExamenMedicoRadiologico.observacion}")
    private String observacion;
    
    @IdCampania(existe = true, groups = IGeneracionNumReg.class)
    private Integer idCampania;
    private String codigoAlumno;
    private String tipoAlumno;
    private String idEstadoExamenMedico;

    // DATOS ADICIONALES
    private String descripcionTipoAlumno;
    private String descripcionResultadoRadiologico;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombres;
    private int edad;
    private String idSexo;
    private String descripcionSexo;
    private String descripcionEscuela;
    private String descripcionCampania;
    private String descripcionEstadoExamenMedico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
    private Date fechaGeneracionNumeroRegistro;
}