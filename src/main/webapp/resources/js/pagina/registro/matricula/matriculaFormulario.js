$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			tipoDocumento : {
				required : true,
				notOnlySpace : true,
				selectlength : [ 1, 4 ]
			},
			numeroDocumento : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 8, 12 ]
			},
			nombreAlumno : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 100 ]
			},
			apellidoAlumno : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 100 ]
			},
			correoAlumno : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 100 ]
			},
			fechaMatricula : {
				required : true
			},
			idConceptoPago : {
				required : true,
				notOnlySpace : true
			},
			idModalidad : {
				required : true,
				notOnlySpace : true
			},
			idEspecializacion : {
				required : true,
				notOnlySpace : true
			},
			idTipoPago : {
				required : true,
				notOnlySpace : true
			},
			uploadfile : {
				required : true
			}
		},
		messages : {
			tipoDocumento : {
				required : "Seleccione el Tipo de Documento.",
				notOnlySpace : "El Tipo de Documento no debe contener solo espacios en blanco.",
				selectlength : "El Tipo de Documento debe contener entre 1 y 5 car&aacute;cteres"
			},
			numeroDocumento : {
				required : "Ingrese el N&uacute;mero de Documento.",
				notOnlySpace : "El N&uacute;umero de Documento no debe contener solo espacios en blanco.",
				rangelength : "El N&uacute;umero de Documento debe contener entre 8 y 12 car&aacute;cteres."
			},
			nombreAlumno : {
				required : "Ingrese Nombres del alumno.",
				notOnlySpace : "Los Nombres no pueden contener solo espacios en blanco.",
				rangelength : "Los Nombres deben contener entre 3 y 100 car&aacute;cteres."
			},
			apellidoAlumno : {
				required : "Ingrese Apellidos del alumno.",
				notOnlySpace : "Los Apellidos no pueden contener solo espacios en blanco.",
				rangelength : "Los Apellidos deben contener entre 3 y 100 car&aacute;cteres."
			},
			correoAlumno : {
				required : "Ingrese correo del alumno.",
				notOnlySpace : "El correo no puede contener solo espacios en blanco.",
				rangelength : "El correo debe contener entre 3 y 100 car&aacute;cteres."
			},
			fechaMatricula : {
				required : "Ingrese la fecha de matricula."
			},
			idConceptoPago : {
				required : "Ingrese un concepto de pago.",
				notOnlySpace : "El concepto de pago no puede contener solo espacios en blanco."
			},
			idModalidad : {
				required : "Ingrese una modalidad.",
				notOnlySpace : "La modalidad no puede contener solo espacios en blanco."
			},
			idEspecializacion : {
				required : "Ingrese una especializaci&oacute;n.",
				notOnlySpace : "La especializaci&oacute;n no puede contener solo espacios en blanco."
			},
			idTipoPago : {
				required : "Ingrese el tipo de pago.",
				notOnlySpace : "El tipo de pago no puede contener solo espacios en blanco."
			},
			uploadfile : {
				required : "Ingrese voucher de pago."
			}
		}
	});

});