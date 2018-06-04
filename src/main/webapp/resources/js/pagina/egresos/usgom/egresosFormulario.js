$(document).ready(function() {

	$formMantenimiento1.validate({
		focusCleanup : true,
		rules : {
			codigoUnidad : {
				required : true,
				notOnlySpace : true
			},
			tipoAdquisicion : {
				required : true,
				notOnlySpace : true
			},
			documento : {
				required : true,
				notOnlySpace : true
			},
			nombreProveedor : {
				required : true,
				notOnlySpace : true
			},
			tipoDocProveedor : {
				required : true,
				notOnlySpace : true
			},
			nroDocProveedor : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 5, 15 ]
			}
		},
		messages : {
			codigoUnidad : {
				required : "Seleccione una Unidad.",
				notOnlySpace : "La unidad no puede estar en blanco."
			},
			tipoAdquisicion : {
				required : "Seleccione un tipo de adquisición.",
				notOnlySpace : "El tipo de adquisición no puede estar en blanco."
			},
			documento : {
				required : "Seleccione un documento.",
				notOnlySpace : "El documento no puede estar en blanco."
			},
			nombreProveedor : {
				required : "Ingrese el nombre del Proveedor.",
				notOnlySpace : "El nombre del proveedor no puede contener solo espacios en blanco."
			},
			tipoDocProveedor : {
				required : "Seleccione el Tipo de Documento.",
				notOnlySpace : "El Tipo de Documento no puede contener solo espacios en blanco."
			},
			nroDocProveedor : {
				required : "Ingrese el N&uacute;mero de Documento.",
				notOnlySpace : "El N&uacute;umero de Documento no puede contener solo espacios en blanco.",
				rangelength : "El N&uacute;umero de Documento debe contener entre 3 y 15 car&aacute;cteres."
			}
		}
	});
});