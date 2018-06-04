package com.cenpro.sircie.model.mantenimiento;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.cenpro.sircie.utilitario.MultiTablaUtil;
import com.cenpro.sircie.validacion.MultitabDet;
import com.cenpro.sircie.validacion.NumeroDocumento;
import com.cenpro.sircie.validacion.grupo.ILlave;
import com.cenpro.sircie.validacion.grupo.accion.IActualizacion;
import com.cenpro.sircie.validacion.grupo.accion.IRegistro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NumeroDocumento(existe = true, groups = IActualizacion.class)
@NumeroDocumento(existe = false, message = "{Existe.Persona.numeroDocumento}", groups = IRegistro.class)
public class Persona
{
    @MultitabDet(campoIdItem = "idTipoDocumento", idTabla = MultiTablaUtil.TABLA_TIPO_DOCUMENTO_IDENTIDAD, min = 1, max = 5, groups = ILlave.class)
    private String idTipoDocumento;

    @NotNull(message = "{NotNull.Persona.numeroDocumento}", groups = ILlave.class)
    @NotBlank(message = "{NotBlank.Persona.numeroDocumento}", groups = ILlave.class)
    @Length(min = 5, max = 12, message = "{Length.Persona.numeroDocumento}", groups = ILlave.class)
    private String numeroDocumento;

    @NotNull(message = "{NotNull.Persona.nombres}")
    @NotBlank(message = "{NotBlank.Persona.nombres}")
    @Length(min = 3, max = 100, message = "{Length.Persona.nombres}")
    private String nombres;

    @NotNull(message = "{NotNull.Persona.apellidoPaterno}")
    @NotBlank(message = "{NotBlank.Persona.apellidoPaterno}")
    @Length(min = 3, max = 100, message = "{Length.Persona.apellidoPaterno}")
    private String apellidoPaterno;

    @NotNull(message = "{NotNull.Persona.apellidoMaterno}")
    @NotBlank(message = "{NotBlank.Persona.apellidoMaterno}")
    @Length(min = 3, max = 100, message = "{Length.Persona.apellidoMaterno}")
    private String apellidoMaterno;

    private String descripcionTipoDocumento;
}