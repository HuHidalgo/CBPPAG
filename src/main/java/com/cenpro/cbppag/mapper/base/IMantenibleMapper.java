package com.cenpro.cbppag.mapper.base;

import java.util.List;

import com.cenpro.cbppag.model.parametro.Parametro;

public interface IMantenibleMapper<T>
{
    public List<T> mantener(Parametro parametro);
}
