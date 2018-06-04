$(document).ready(function() {

	$formMantenimiento.validate({
		focusCleanup : true,
		rules : {
			idConcepto : {
				required : true,
				notOnlySpace : true
			},
			codigoCurso : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 1, 10 ]
			},
			nombreCurso : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 3, 100 ]
			},
			nroSemestres : {
				required : true,
				notOnlySpace : true,
				rangelength : [ 1, 10 ]
			}
		},
		messages : {
			idConcepto : {
				required : "Elija un Concepto.",
				notOnlySpace : "El concepto no puede contener solo espacios en blanco."
			},
			codigoCurso : {
				required : "Ingrese el Código de curso.",
				notOnlySpace : "El Código de curso no puede contener solo espacios en blanco.",
				rangelength : "El Código de curso debe contener entre 1 y 10 car&aacute;cteres."
			},
			nombreCurso : {
				required : "Ingrese el nombre del curso.",
				notOnlySpace : "El nombre del curso no puede contener solo espacios en blanco.",
				rangelength : "El nombre del curso debe contener entre 3 y 100 car&aacute;cteres."
			},
			nroSemestres : {
				required : "Ingrese el Número de Semestres.",
				notOnlySpace : "El Número de Semestres no puede contener solo espacios en blanco.",
				rangelength : "El Número de Semestres debe contener entre 1 y 10 car&aacute;cteres."
			}
		}
	});
});