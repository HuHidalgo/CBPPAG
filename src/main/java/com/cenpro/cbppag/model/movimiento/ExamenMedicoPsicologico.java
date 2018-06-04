package com.cenpro.cbppag.model.movimiento;

import org.hibernate.validator.constraints.Length;

import com.cenpro.cbppag.utilitario.MultiTablaUtil;
import com.cenpro.cbppag.validacion.CodigoAlumno;
import com.cenpro.cbppag.validacion.IdCampania;
import com.cenpro.cbppag.validacion.MultitabDet;
import com.cenpro.cbppag.validacion.NumRegPsicologico;
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
@CodigoAlumno(existe = true, groups = IRegistro.class)
public class ExamenMedicoPsicologico
{
    @NumRegPsicologico(existe = true, groups = IActualizacion.class)
    private Integer numeroRegistro;

    @MultitabDet(campoIdItem = "idResultadoPsicologico", idTabla = MultiTablaUtil.TABLA_RESULTADO_PSICOLOGICO, existe = true, min = 1, max = 1)
    private String idResultadoPsicologico;

    @Length(max = 250, message = "{Length.ExamenPsicologico.observacion}")
    private String observacion;

    @IdCampania(existe = true, groups = IRegistro.class)
    private Integer idCampania;
    private String codigoAlumno;
    private String tipoAlumno;
    private String idEstadoExamenMedico;

    /* Datos Adicionales */
    private String descripcionTipoAlumno;
    private String descripcionResultadoPsicologico;
    private String descripcionCampania;
    private String descripcionEstadoExamenMedico;
}