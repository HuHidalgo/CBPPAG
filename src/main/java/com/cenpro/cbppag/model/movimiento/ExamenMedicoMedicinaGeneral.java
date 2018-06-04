package com.cenpro.cbppag.model.movimiento;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

import com.cenpro.cbppag.validacion.CodigoAlumno;
import com.cenpro.cbppag.validacion.IdCampania;
import com.cenpro.cbppag.validacion.NumRegMedicinaGeneral;
import com.cenpro.cbppag.validacion.grupo.accion.IActualizacion;
import com.cenpro.cbppag.validacion.grupo.accion.IRegistro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NumRegMedicinaGeneral(groups = IActualizacion.class)
@CodigoAlumno(existe = true, groups = IRegistro.class)
public class ExamenMedicoMedicinaGeneral
{
    private Integer numeroRegistro;
    private String anio;
    private boolean embarazo;

    @Length(max = 1000, message = "{Length.ExamenMedicoMedicinaGeneral.comentario}")
    private String comentario;
    
    @Valid
    private List<Antecedente> antecedentes;
    
    @Valid
    private List<Interconsulta> interconsultas;

    @IdCampania(existe = true, groups = IRegistro.class)
    private Integer idCampania;
    
    private String codigoAlumno;
    private String tipoAlumno;
    private String idEstadoExamenMedico;
}