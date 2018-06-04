$(document).ready(function() {

	$formDetalleMantenimiento.validate({
		focusCleanup : true,
		rules : {
			clasificador : {
				required : true,
				notOnlySpace : true
			},
			detalle : {
				required : true,
				notOnlySpace : true
			},
			cantidad : {
				required : true,
				notOnlySpace : true
			},
			precioUnitario : {
				required : true,
				notOnlySpace : true
			}
		},
		messages : {
			clasificador : {
				required : "Seleccione una Partida.",
				notOnlySpace : "La partida no puede estar en blanco."
			},
			detalle : {
				required : "Ingrese el detalle.",
				notOnlySpace : "El detalle no puede contener solo espacios en blanco."
			},
			cantidad : {
				required : "Ingrese la cantidad.",
				notOnlySpace : "La cantidad no puede contener solo espacios en blanco."
			},
			precioUnitario : {
				required : "Ingrese el precio unitario.",
				notOnlySpace : "El precio unitario no puede contener solo espacios en blanco."
			}
		}
	});
});