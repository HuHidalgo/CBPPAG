$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			codigoTarea : {
				required : true,
				notOnlySpace : true
			},
			clasificador : {
				required : true,
				notOnlySpace : true
			},
			descripcion : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 450 ]
			},
			descDetallada : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 850 ]
			}
		},
		messages : {
			codigoTarea : {
				required : "Elija el C&oacute;digo de Tarea.",
				notOnlySpace : "El C&oacute;digo de Unidad no debe contener solo espacios en blanco."
			},
			clasificador : {
				required : "Ingrese el clasificador de la partida.",
				notOnlySpace : "El clasificador no puede contener solo espacios en blanco.",
				digits : "El C&oacute;digo de Facultad debe contener solo d&iacute;gitos."
			},
			descripcion : {
				required : "Ingrese la descripción de la partida.",
				notOnlySpace : "La descripción no puede contener solo espacios en blanco.",
				rangelength : "La descripción debe contener entre 3 y 250 car&aacute;cteres."
			},
			descDetallada : {
				required : "Ingrese la descripción detallada de la partida.",
				notOnlySpace : "La descripción detallada no puede contener solo espacios en blanco.",
				rangelength : "La descripción detalada debe contener entre 3 y 850 car&aacute;cteres."
			}
		}
	});
});