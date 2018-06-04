package com.cenpro.cbppag.validacion.grupo.secuencia;

import javax.validation.GroupSequence;

import com.cenpro.cbppag.validacion.grupo.ILlave;
import com.cenpro.cbppag.validacion.grupo.accion.IActualizacion;

@GroupSequence({ ILlave.class, IActualizacion.class })
public interface ISecuenciaValidacionEliminacion
{

}