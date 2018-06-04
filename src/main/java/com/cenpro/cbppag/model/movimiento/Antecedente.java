package com.cenpro.cbppag.model.movimiento;

import org.hibernate.validator.constraints.Length;

import com.cenpro.cbppag.utilitario.MultiTablaUtil;
import com.cenpro.cbppag.validacion.IdCie10;
import com.cenpro.cbppag.validacion.MultitabDet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Antecedente
{
    private int secuencia;

    @IdCie10(existe = true)
    private String idCie10;

    @MultitabDet(campoIdItem = "idTipoAntecedente", idTabla = MultiTablaUtil.TABLA_TIPO_ANTECEDENTE, existe = true, min = 1, max = 1)
    private String idTipoAntecedente;

    @Length(max = 400, message = "{Length.Antecedente.descripcion}")
    private String descripcion;

    /* Dato de Medicina General */
    private Integer numeroRegistro;
    private String anio;
    
    private String idTipoDocumento;
    private String numeroDocumento;
}