package com.cenpro.sircie.model.presupuesto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inicial {
	private Integer idInicial;
	private Double inicial;
	private String año;
	private Double total;
	private String tipo;
}
