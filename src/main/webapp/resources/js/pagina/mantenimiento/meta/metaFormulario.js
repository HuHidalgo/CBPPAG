$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			nroMeta : {
				required : true,
				notOnlySpace : true,
				range : [ 1, 99 ]
			},
			nomMeta : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 100 ]
			}
		},
		messages : {
			nroMeta : {
				required : "Ingrese el N&uacute;mero de Meta.",
				notOnlySpace : "El N&uacute;mero de Meta no debe contener solo espacios en blanco.",
				range : "El N&uacute;mero de Meta debe estar entre 1 y 99."
			},
			nomMeta : {
				required : "Ingrese el Nombre de Meta.",
				notOnlySpace : "La descripci&oacute;n no puede contener solo espacios en blanco.",
				rangelength : "La descripci&oacute;n debe contener entre 3 y 100 car&aacute;cteres."
			}
		}
	});
});