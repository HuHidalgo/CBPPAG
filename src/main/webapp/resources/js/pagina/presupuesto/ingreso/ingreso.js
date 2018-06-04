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
		$conceptos : $("#conceptos"),
		$meses : $("#meses"),
		idPIngresoSeleccionado : 0,
		codigoUnidadSeleccionada : "",
		clasificadorSeleccionado : "",
		idConceptoSeleccionado : 0,
		idMesSeleccionado : "",
		$inicial2 : $("#inicialRegistro"),
		$año : $("#ingresoAño")
	};

	$formMantenimiento = $("#formMantenimiento");
	
	$funcionUtil.crearSelect2($local.$unidades, "Seleccione una unidad");
	$funcionUtil.crearSelect2($local.$partidas, "Seleccione una partida");
	$funcionUtil.crearSelect2($local.$conceptos, "Seleccione un concepto");
	$funcionUtil.crearSelect2($local.$meses, "Seleccione un mes");
	
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
			"url" : $variableUtil.root + "presupuesto/ingreso?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay ingresos presupuestados registrados."
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2 ,3 ,4 ,5, 6 ],
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
				return $funcionUtil.unirCodigoDescripcion(row.nroConcepto, row.nombreConcepto);
			},
			"title" : "Concepto"
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
			"title" : "Inicial"
		},{
			"data" : null,
			"title" : 'Acción'
		} ]
	});

	$local.$tablaMantenimiento.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$modalMantenimiento.PopupWindow({
		title : "Mantenimiento de Ingreso presupuestado",
		autoOpen : false,
		modal : false,
		height : 510,
		width : 700
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro($formMantenimiento);
		var tipo = "ingreso";
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
		$local.idPEgresoSeleccionado = 0;
		$local.codigoUnidadSeleccionada = "";
		$local.clasificadorSeleccionado = "";
		$local.idConceptoSeleccionado = 0 ;
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
			$local.$partidas.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/partidas/unidad/" + codigoUnidad,
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
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/concepto/unidad/" + codigoUnidad,
			beforeSend : function(xhr) {
				$local.$conceptos.find("option:not(:eq(0))").remove();
				$local.$conceptos.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Conceptos</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(conceptos) {
				$.each(conceptos, function(i, concepto) {
					$local.$conceptos.append($("<option />").val(this.idConcepto).text(this.nroConceptoEsp + " - " + this.nomConceptoEsp));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {					
					$local.$conceptos.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$conceptos.parent().find(".cargando").remove();
			}
		});
	});
		
	
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var ingreso = $formMantenimiento.serializeJSON();
		ingreso.inicial = $local.$inicial2.val();
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "presupuesto/ingreso",
			data : JSON.stringify(ingreso),
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
			success : function(ingresos) {
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row.add(ingresos[0]).draw();
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
		var ingreso = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.idPIngresoSeleccionado = ingreso.idPIngreso;
		$local.codigoUnidadSeleccionada = ingreso.codigoUnidad;
		$local.clasificadorSeleccionado = ingreso.clasificador;
		$local.idConceptoSeleccionado = ingreso.idConcepto;
		$local.idMesSeleccionado = ingreso.mes;
		$funcionUtil.llenarFormulario(ingreso, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var ingreso = $formMantenimiento.serializeJSON();
		ingreso.idPIngreso= $local.idPIngresoSeleccionado;
		ingreso.codigoUnidad = $local.$unidades.val();
		ingreso.clasificador = $local.$partidas.val();
		ingreso.idConcepto = $local.$conceptos.val();
		ingreso.mes = $local.idMesSeleccionado;
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "presupuesto/ingreso",
			data : JSON.stringify(ingreso),
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
			success : function(ingresos) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(ingresos[0]).draw();
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
		var ingreso = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$.confirm({
			icon : "fa fa-info-circle",
			title : "Aviso",
			content : "¿Desea eliminar el ingreso presupuestado <b>'" + ingreso.idPIngreso + " de la unidad " + ingreso.nombreUnidad + "'<b/>?",
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
									url : $variableUtil.root + "presupuesto/ingreso",
									data : JSON.stringify(ingreso),
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
										var mensaje = $funcionUtil.obtenerMensajeError("El ingreso presupuestado <b>'" + ingreso.idPIngreso + " de la unidad " + ingreso.nombreUnidad + "</b>", xhr.responseJSON, $variableUtil.accionEliminado);
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