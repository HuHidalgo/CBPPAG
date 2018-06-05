package com.cenpro.cbppag.model.parametro;

import lombok.Value;

@Value
public class MensajeError
{
    private int codigo_error;
    private String motivo;
}