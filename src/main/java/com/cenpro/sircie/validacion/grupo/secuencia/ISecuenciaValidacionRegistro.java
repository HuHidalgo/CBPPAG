package com.cenpro.sircie.validacion.grupo.secuencia;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.cenpro.sircie.validacion.grupo.ILlave;
import com.cenpro.sircie.validacion.grupo.accion.IRegistro;

@GroupSequence({ Default.class, ILlave.class, IRegistro.class })
public interface ISecuenciaValidacionRegistro
{

}
