package com.cenpro.cbppag.validacion.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cenpro.cbppag.service.IEspecialidadService;
import com.cenpro.cbppag.utilitario.ValidatorUtil;
import com.cenpro.cbppag.validacion.IdEspecialidad;

public class IdEspecialidadValidator implements ConstraintValidator<IdEspecialidad, Integer>
{
    private int min;
    private int max;
    private boolean existe;

    private @Autowired IEspecialidadService especialidadService;

    @Override
    public void initialize(IdEspecialidad anotacion)
    {
        this.min = anotacion.min();
        this.max = anotacion.max();
        this.existe = anotacion.existe();
    }

    @Override
    public boolean isValid(Integer idEspecialidad, ConstraintValidatorContext context)
    {
        if (idEspecialidad == null)
        {
            ValidatorUtil.addCustomMessageWithTemplate("{NotNull.Especialidad.idEspecialidad}", context);
            return false;
        }
        if (idEspecialidad < min || idEspecialidad > max)
        {
            ValidatorUtil.addCustomMessageWithTemplate("{Length.Especialidad.idEspecialidad}", context);
            return false;
        }
        boolean existeEspecialidad = this.especialidadService.existeEspecialidad(idEspecialidad);
        return (existe ? existeEspecialidad : !existeEspecialidad);
    }
}