package com.cenpro.cbppag.validacion.grupo.secuencia;

import javax.validation.GroupSequence;

import com.cenpro.cbppag.validacion.grupo.IMetodo;
import com.cenpro.cbppag.validacion.grupo.IParametro;

@GroupSequence({ IParametro.class, IMetodo.class })
public interface ISecuenciaValidacionController
{

}
