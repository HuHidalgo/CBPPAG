$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$modalMantenimiento : $("#modalMantenimiento"),
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		codigoCursoSeleccionado : 0,
		$unidades : $("#unidades"),
		$partidas : $("#partidas"),
		$tareas : $("#tareas"),
		$meses : $("#meses"),
		idPEgresoSeleccionado : 0,
		codigoUnidadSeleccionada : "",
		clasificadorSeleccionado : "",
		codigoTareaSeleccionado : 0,
		idMesSeleccionado : "",
		$inicial2 : $("#inicialRegistro"),
		$año : $("#egresoAño")
	};

	$formMantenimiento = $("#formMantenimiento");
	
	$funcionUtil.crearSelect2($local.$unidades, "Elija unidad");
	$funcionUtil.crearSelect2($local.$partidas, "Elija partida");
	$funcionUtil.crearSelect2($local.$tareas, "Elija tarea");
	$funcionUtil.crearSelect2($local.$meses, "Elija mes");
	
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
			"url" : $variableUtil.root + "presupuesto/egreso?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay egresos presupuestados registrados."
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2 ,3, 4, 5, 6 ],
			"className" : "all filtrable",
		}, {
			"targets" : 7,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonEliminar
		} ],
		"columns" : [{
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.codigoUnidad, row.nombreUnidad);
			},
			"title" : "Unidad"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.codigoTarea, row.nombreTarea);
			},
			"title" : "Tarea"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.clasificador, row.descripcionPartida);
			},
			"title" : "Partida"
		},{
			"data" : "año",
			"title" : "Año"
		},{
			"data" : "mes",
			"title" : "Mes"
		},{
			"data" : "importe",
			"title" : "Importe"
		},{
			"data" : "inicial",
			"title" : "Incial"
		},{
			"data" : null,
			"title" : 'Acción'
		} ]
	});

	$local.$tablaMantenimiento.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$modalMantenimiento.PopupWindow({
		title : "Mantenimiento de Egreso presupuesto",
		autoOpen : false,
		modal : false,
		height : 510,
		width : 706
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro($formMantenimiento);
		var tipo = "egreso";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "presupuesto/inicial/" +  tipo,
			success : function(iniciales) {
				$.each(iniciales, function(i, inicial) {		
					$local.$inicial2.val(this.total);
					$local.$año.val(this.año);
				});
			}
		});
		$local.$actualizarMantenimiento.addClass("hidden");
		$local.$registrarMantenimiento.removeClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$modalMantenimiento.on("open.popupwindow", function() {
		$formMantenimiento.find("input:not([disabled]):first").focus();
	});

	$local.$modalMantenimiento.on("close.popupwindow", function() {
		$local.codigoUnidadSeleccionada = "";
		$local.clasificadorSeleccionado = "";
		$local.codigoTareaSeleccionado = 0;
		$local.idMesSeleccionado = "";
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

	$local.$unidades.on("change", function(event, opcionSeleccionada) {
		var codigoUnidad = $(this).val();
		if (codigoUnidad == null || codigoUnidad == undefined) {
			$local.$tareas.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/tarea/unidad/" + codigoUnidad,
			beforeSend : function(xhr) {
				$local.$tareas.find("option:not(:eq(0))").remove();
				$local.$tareas.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Tareas</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(tareas) {
				$.each(tareas, function(i, tarea) {
					$local.$tareas.append($("<option />").val(this.codigoTarea).text(this.codigoTarea + " - " + this.nomTarea));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					$local.$tareas.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$tareas.parent().find(".cargando").remove();
			}
		});
	});
	
	$local.$tareas.on("change", function(event, opcionSeleccionada) {
		var codigoTarea = $(this).val();
		if (codigoTarea == null || codigoTarea == undefined) {
			$local.$partidas.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/partida/tarea/" + codigoTarea,
			beforeSend : function(xhr) {
				$local.$partidas.find("option:not(:eq(0))").remove();
				$local.$partidas.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Partidas</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(partidas) {
				$.each(partidas, function(i, partida) {
					$local.$partidas.append($("<option />").val(this.clasificador).text(this.clasificador + " - " + this.descripcion));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					$local.$partidas.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$partidas.parent().find(".cargando").remove();
			}
		});
	});
		

	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var egreso = $formMantenimiento.serializeJSON();
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "presupuesto/egreso",
			data : JSON.stringify(egreso),
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
			success : function(egresos) {
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var egreso = egresos[0];
				var row = $local.tablaMantenimiento.row.add(egreso).draw();
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
		var egreso = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.idPEgresoSeleccionado = egreso.idPEgreso;
		$local.codigoUnidadSeleccionada = egreso.codigoUnidad;
		$local.codigoTareaSeleccionado = egreso.codigoTarea;
		$local.clasificadorSeleccionado = egreso.clasificador;
		$local.idMesSeleccionado = egreso.mes;
		$funcionUtil.llenarFormulario(egreso, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var egreso = $formMantenimiento.serializeJSON();
		egreso.idPEgreso= $local.idPEgresoSeleccionado;
		egreso.codigoUnidad = $local.$unidades.val();
		egreso.codigoTarea = $local.$tareas.val();
		egreso.clasificador = $local.$partidas.val();
		egreso.mes = $local.idMesSeleccionado;

		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "presupuesto/egreso",
			data : JSON.stringify(egreso),
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
			success : function(egresos) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(egresos[0]).draw();
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
		var egreso = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$.confirm({
			icon : "fa fa-info-circle",
			title : "Aviso",
			content : "¿Desea eliminar el egreso presupuestado <b>'" + egreso.idPEgreso + " de la unidad " + egreso.nombreUnidad + "'<b/>?",
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
									url : $variableUtil.root + "presupuesto/egreso",
									data : JSON.stringify(egreso),
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
										var mensaje = $funcionUtil.obtenerMensajeError("El egreso presupuestado <b>'" + egreso.idPEgreso + " de la unidad " + egreso.nombreUnidad + "</b>", xhr.responseJSON, $variableUtil.accionEliminado);
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