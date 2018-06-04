$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			nombresCliente : {
				required : true,
				notOnlySpace : true
			},
			apellidosCliente : {
				required : true,
				notOnlySpace : true
			},
			tipoDocCliente : {
				required : true,
				notOnlySpace : true,
				selectlength : [ 1, 4 ]
			},
			nroDocCliente : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 5, 15 ]
			},
			codigoUnidad : {
				required : true,
				notOnlySpace : true
			},
			idConcepto : {
				required : true,
				notOnlySpace : true
			},
			clasificador : {
				required : true,
				notOnlySpace : true
			},
			importe : {
				required : true,
				notOnlySpace : true
			},
			tipoMoneda : {
				required : true,
				notOnlySpace : true
			},
			nroVoucher : {
				required : true,
				notOnlySpace : true
			},
			nroFactura : {
				required : true,
				notOnlySpace : true
			}
		},
		messages : {
			nombresCliente : {
				required : "Ingrese nombre de aportante.",
				notOnlySpace : "El nombre de aportante no puede contener solo espacios en blanco."
			},
			apellidosCliente : {
				required : "Ingrese apellidos de aportante",
				notOnlySpace : "El apellido de aportante no puede contener solo espacios en blanco."
			},
			tipoDocCliente : {
				required : "Seleccione el Tipo de Documento.",
				notOnlySpace : "El Tipo de Documento no puede contener solo espacios en blanco.",
				selectlength : "El Tipo de Documento debe contener entre 1 y 4 car&aacute;cteres"
			},
			nroDocCliente : {
				required : "Ingrese el N&uacute;mero de Documento.",
				notOnlySpace : "El N&uacute;umero de Documento no puede contener solo espacios en blanco.",
				rangelength : "El N&uacute;umero de Documento debe contener entre 3 y 15 car&aacute;cteres."
			},
			codigoUnidad : {
				required : "Seleccione una Unidad.",
				notOnlySpace : "La unidad no puede estar en blanco."
			},
			idConcepto : {
				required : "Seleccione un Concepto de pago.",
				notOnlySpace : "El concepto no puede estar en blanco."
			},
			clasificador : {
				required : "Seleccione una Partida.",
				notOnlySpace : "La partida no puede estar en blanco."
			},
			importe : {
				required : "Ingrese el importe.",
				notOnlySpace : "El importe no debe contener solo espacios en blanco."
			},
			tipoMoneda : {
				required : "Seleccione un tipo de moneda.",
				notOnlySpace : "El tipo de moneda no debe contener solo espacios en blanco."
			},
			nroVoucher : {
				required : "Ingrese el número de voucher.",
				notOnlySpace : "El número de voucher no debe contener solo espacios en blanco."
			},
			nroFactura : {
				required : "Ingrese el número de factura.",
				notOnlySpace : "El número de factura no debe contener solo espacios en blanco."
			}
		}
	});
});