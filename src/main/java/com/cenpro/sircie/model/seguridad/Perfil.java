package com.cenpro.sircie.model.seguridad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Perfil
{
    private String idPerfil;
    private String descripcion;
    private String nombrePerfil;
}