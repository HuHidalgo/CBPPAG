package com.cenpro.sircie.validacion.grupo.secuencia;

import javax.validation.GroupSequence;

import com.cenpro.sircie.validacion.grupo.IMetodo;
import com.cenpro.sircie.validacion.grupo.IParametro;

@GroupSequence({ IParametro.class, IMetodo.class })
public interface ISecuenciaValidacionController
{

}
