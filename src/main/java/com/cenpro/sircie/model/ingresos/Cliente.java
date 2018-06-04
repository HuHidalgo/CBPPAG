package com.cenpro.sircie.model.ingresos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	private String nroDocumento; 
	private String nomCliente; 
	private String apeCliente;
	private String tipoDoc;
}
