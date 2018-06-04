package com.cenpro.cbppag.service;

import java.util.concurrent.Future;

import javax.mail.MessagingException;

import com.cenpro.cbppag.model.procesoautomatico.Correo;

public interface IEnvioMailAsyncExecutor
{
    public Future<Void> enviarCorreo(Correo correo) throws MessagingException;
}