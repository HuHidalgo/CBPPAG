package com.cenpro.cbppag.validacion.grupo.secuencia;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.cenpro.cbppag.validacion.grupo.ILlave;
import com.cenpro.cbppag.validacion.grupo.accion.IRegistro;

@GroupSequence({ Default.class, ILlave.class, IRegistro.class })
public interface ISecuenciaValidacionRegistro
{

}
