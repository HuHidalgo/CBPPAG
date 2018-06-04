package com.cenpro.sircie.model.seguridad;

import lombok.Data;

@Data
public class TipoAuditoria
{
    private Integer idTipoAuditoria;
    private String descripcion;
    private String codigoAuditoria;
}