$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$modalMantenimiento : $("#modalMantenimiento"),
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		codigoEspecializacionSeleccionada : 0,
		$modalidades : $("#modalidades"),
		$fechaInicio : $("#fechaInicio"),
		$fechaFin : $("#fechaFin"),
	};

	$formMantenimiento = $("#formMantenimiento");
	
	$funcionUtil.crearSelect2($local.$modalidades, "Seleccione una Modalidad");
	$funcionUtil.crearDatePickerSimple($local.$fechaInicio, "DD/MM/YYYY");
	$funcionUtil.crearDatePickerSimple2($local.$fechaFin, "DD/MM/YYYY");
	
	$.fn.dataTable.ext.errMode = 'none';

	$local.$tablaMantenimiento.on('xhr.dt', function(e, settings, json, xhr) {
		switch (xhr.status) {
		case 500:
			$local.tablaMantenimiento.clear().draw();
			break;
		}
	});

	$local.tablaMantenimiento = $local.$tablaMantenimiento.DataTable({
		"ajax" : {
			"url" : $variableUtil.root + "mantenimiento/especializacion?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay Especializaciones registradas."
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6 ],
			"className" : "all filtrable",
		}, {
			"targets" : 7,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonEliminar
		} ],
		"columns" : [ {
			"data" : "idEspecializacion",
			"title" : "Código de Especialización"
		}, {
			"data" : "nombreEspecializacion",
			"title" : "Especialización"
		}, {
			"data" : "nombreModalidad",
			"title" : "Modalidad"
		}, {
			"data" : "costoMatricula",
			"title" : "Costo de Matrícula"
		}, {
			"data" : "costoCiclo",
			"title" : "Costo por Ciclo"
		},{
			"data" : function(row){
				return $funcionUtil.convertirDeFormatoAFormato(row.fechaInicio, "YYYY-MM-DD", "DD/MM/YYYY");
			},
			"title" : "Fecha de Inicio"
		},{
			"data" : function(row){
				return $funcionUtil.convertirDeFormatoAFormato(row.fechaFin, "YYYY-MM-DD", "DD/MM/YYYY");
			},
			"title" : "Fecha de Fin"
		},{
			"data" : null,
			"title" : 'Acción'
		} ]
	});

	$local.$tablaMantenimiento.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$modalMantenimiento.PopupWindow({
		title : "Mantenimiento de Especializacion",
		autoOpen : false,
		modal : false,
		height : 500,
		width : 800
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro($formMantenimiento);
		$local.$actualizarMantenimiento.addClass("hidden");
		$local.$registrarMantenimiento.removeClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$modalMantenimiento.on("open.popupwindow", function() {
		$formMantenimiento.find("input:not([disabled]):first").focus();
	});

	$local.$modalMantenimiento.on("close.popupwindow", function() {
		$local.codigoEspecializacionSeleccionada = 0;
	});

	$formMantenimiento.find("input").keypress(function(event) {
		if (event.which == 13) {
			if (!$local.$registrarMantenimiento.hasClass("hidden")) {
				$local.$registrarMantenimiento.trigger("click");
				return false;
			} else {
				if (!$local.$actualizarMantenimiento.hasClass("hidden")) {
					$local.$actualizarMantenimiento.trigger("click");
				}
				return false;
			}
		}
	});

	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var especializacion = $formMantenimiento.serializeJSON();
		especializacion.fechaInicio = $local.$fechaInicio.data("daterangepicker").startDate.format('YYYY-MM-DD');
		especializacion.fechaFin = $local.$fechaFin.data("daterangepicker").startDate.format('YYYY-MM-DD');
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "mantenimiento/especializacion",
			data : JSON.stringify(especializacion),
			beforeSend : function(xhr) {
				$local.$registrarMantenimiento.attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var especializacion = especializaciones[0];
				var row = $local.tablaMantenimiento.row.add(especializacion).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalMantenimiento.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$registrarMantenimiento.attr("disabled", false).find("i").addClass("fa-floppy-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaMantenimiento.children("tbody").on("click", ".actualizar", function() {
		$funcionUtil.prepararFormularioActualizacion($formMantenimiento);
		$local.$filaSeleccionada = $(this).parents("tr");
		var especializacion = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.codigoEspecializacionSeleccionada = especializacion.idEspecializacion;
		$funcionUtil.llenarFormulario(especializacion, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var especializacion = $formMantenimiento.serializeJSON();
		especializacion.idEspecializacion = $local.codigoEspecializacionSeleccionada;
		campania.fechaInicio = $local.$fechaInicio.data("daterangepicker").startDate.format('YYYY-MM-DD');
		campania.fechaFin = $local.$fechaFin.data("daterangepicker").startDate.format('YYYY-MM-DD');
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "mantenimiento/especializacion",
			data : JSON.stringify(especializacion),
			beforeSend : function(xhr) {
				$local.$actualizarMantenimiento.attr("disabled", true).find("i").removeClass("fa-pencil-square").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var especializacion = especializaciones[0];
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(especializacion).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalMantenimiento.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$actualizarMantenimiento.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaMantenimiento.children("tbody").on("click", ".eliminar", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		var especializacion = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$.confirm({
			icon : "fa fa-info-circle",
			title : "Aviso",
			content : "¿Desea eliminar la especializacion <b>'" + especializacion.codigoEspecializacion + " - " + especializacion.nomEspecializacion + "'<b/>?",
			theme : "bootstrap",
			buttons : {
				Aceptar : {
					action : function() {
						var confirmar = $.confirm({
							icon : 'fa fa-spinner fa-pulse fa-fw',
							title : "Eliminando...",
							content : function() {
								var self = this;
								self.buttons.close.hide();
								$.ajax({
									type : "DELETE",
									url : $variableUtil.root + "mantenimiento/especializacion",
									data : JSON.stringify(especializacion),
									autoclose : true,
									beforeSend : function(xhr) {
										xhr.setRequestHeader('Content-Type', 'application/json');
										xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
									}
								}).done(function(response) {
									$funcionUtil.notificarException(response, "fa-check", "Aviso", "success");
									$local.tablaMantenimiento.row($local.$filaSeleccionada).remove().draw(false);
									confirmar.close();
								}).fail(function(xhr) {
									confirmar.close();
									switch (xhr.status) {
									case 400:
										$funcionUtil.notificarException($funcionUtil.obtenerMensajeErrorEnCadena(xhr.responseJSON), "fa-warning", "Aviso", "warning");
										break;
									case 409:
										var mensaje = $funcionUtil.obtenerMensajeError("La especializacion <b>" + especializacion.codigoEspecializacion + " - " + especializacion.nomEspecializacion + "</b>", xhr.responseJSON, $variableUtil.accionEliminado);
										$funcionUtil.notificarException(mensaje, "fa-warning", "Aviso", "warning");
										break;
									}
								});
							},
							buttons : {
								close : {
									text : 'Aceptar'
								}
							}
						});
					},
					keys : [ 'enter' ],
					btnClass : "btn-primary"
				},
				Cancelar : {
					keys : [ 'esc' ]
				},
			}
		});
	});
});