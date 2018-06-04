package com.cenpro.sircie.mapper.base;

import java.util.List;

import com.cenpro.sircie.model.parametro.Parametro;

public interface IMantenibleMapper<T>
{
    public List<T> mantener(Parametro parametro);
}
