$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$tablaVerDetalleGastos : $("#tablaVerDetalleGastos"),
		tablaVerDetalleGastos : "",
		
		$modalActualizarGasto : $("#modalActualizarGasto"),
		$modalVerDetalleGastos : $("#modalVerDetalleGastos"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimientoGasto"),
		$unidades : $("#unidades"),
		$partidas : $("#partidas"),
		$tareas : $("#tareas"),
		$metas : $("#metas"),
		$filaDetalleSeleccionada : "",
		codigoEgresoSeleccionado : 0,
		id_ProgramacionGasto : 0,
		codigoGastoSeleccionado : 0,
		arregloSiNo : [ "1", "0" ],
		filtrosSeleccionables : {}
	};

	$formMantenimientoGasto = $("#formMantenimientoGasto");
	
	$funcionUtil.crearSelect2($local.$unidades, "Seleccione una Unidad");
	$funcionUtil.crearSelect2($local.$partidas, "Seleccione una Partida");
	$funcionUtil.crearSelect2($local.$tareas, "Seleccione una Tarea");
	$funcionUtil.crearSelect2($local.$metas, "Seleccione una Meta");
	
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
			"url" : $variableUtil.root + "egresos/planificacion/programacionGastos?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay programaciones de gastos registrados."
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$local.filtrosSeleccionables["3"] = $local.arregloSiNo;
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento, $local.filtrosSeleccionables);
		},
		"ordering" : false,
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2 ],
			"className" : "all filtrable",
		}, {
			"targets" : 3,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonVerGastos
		}, ],
		"columns" : [ {
			"data" : function(row) {
				return $funcionUtil.convertirDeFormatoAFormato(row.fechaEnvio, "YYYY-MM-DD", "DD/MM/YYYY");
			},
			"title" : "Fecha"
		}, {
			"data" : "nroProgramacionGasto",
			"title" : "Programación de Gasto"
		}, {
			"className" : "all seleccionable data-no-definida dt-center",
			"data" :	function(row) {
				return $funcionUtil.insertarEtiquetaSiNo(row.estado);
			},
			"title" : "Atendido",
			"width" : "10%"
		}, {
			"data" : null,
			"title" : "Detalle de Gastos",
			"width" : "10%"
		}]
	});

	$local.$tablaMantenimiento.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});
	
	$local.$tablaMantenimiento.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	//**************************************************************************************
	//						MODAL VER DETALLE DE GASTOS  
	//***************************************************************************************
	
	$local.$tablaVerDetalleGastos.on('xhr.dt', function(e, settings, json, xhr) {
		switch (xhr.status) {
		case 500:
			$local.tablaVerDetalleGastos.clear().draw();
			break;
		}
	});
	
	$local.tablaVerDetalleGastos = $local.$tablaVerDetalleGastos.DataTable({
		"language" : {
			"emptyTable" : "No hay gastos registrados."
		},
		"initComplete" : function() {
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaVerDetalleGastos);
		},
		"columnDefs" : [ {
			"targets" : 0,
			"className" : "all filtrable"
		}, {
			"targets" : 1,
			"className" : "all  filtrable"
		}, {
			"targets" : 2,
			"className" : "all  filtrable"
		},  {
			"targets" : 3,
			"className" : "all  filtrable"
		},  {
			"targets" : 4,
			"className" : "all  filtrable"
		},	{
			"targets" : 5,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar
		}  ],
		"columns" : [ {
			"data" : function(row) {
				return row.documento + " " + row.docReferencia;
			},
			"title" : "Documento Referencia", 
			"width": "18%"
		}, {
			"data" : 'detalle',
			"title" : "Detalle",
			"width"	: "48%"
		}, {
			"data" : 'nombreUnidad',
			"title" : "Solicitante",
			"width" : "20%"
		}, {
			"data" : 'clasificador',
			"title" : "Partida",
			"width": "7%"
		}, {
			"data" : 'montoTotal',
			"title" : "Monto",
			"width": "7%"
		}, {
			"data" : null,
			"title" : 'Acción',
			"width": "5%"
		} ]
	});
	
	$local.$tablaVerDetalleGastos.wrap("<div class='table-responsive'></div>");

	$local.$tablaVerDetalleGastos.find("thead").on('keyup', 'input', function() {
		$local.tablaVerDetalleGastos.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaVerDetalleGastos.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaVerDetalleGastos.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	$local.$partidas.on("change", function(event, opcionSeleccionada) {
		var codigoPartida = $(this).val();
		if (codigoPartida == null || codigoPartida == undefined) {
			$local.$tareas.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/tarea/partida/" + codigoPartida,
			beforeSend : function(xhr) {
				$local.$tareas.find("option:not(:eq(0))").remove();
				$local.$tareas.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Conceptos</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimientoGasto);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimientoGasto);
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
			$local.$metas.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/tarea/meta/" + codigoTarea,
			beforeSend : function(xhr) {
				$local.$metas.find("option:not(:eq(0))").remove();
				$local.$metas.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Conceptos</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimientoGasto);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimientoGasto);
				}
			},
			success : function(metas) {
				$.each(metas, function(i, meta) {
					$local.$metas.append($("<option />").val(this.nroMeta).text(this.nroMeta + " - " + this.nomMeta));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					$local.$metas.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$metas.parent().find(".cargando").remove();
			}
		});

	});
	
	$local.$modalVerDetalleGastos.PopupWindow({
		title : "Detalle de Gastos",
		autoOpen : false,
		modal : false,
		height : 600,
		width : 1100,
	});
	
	$local.$tablaMantenimiento.children("tbody").on("click", ".ver-gastos", function() {
		$local.$filaDetalleSeleccionada = $(this).parents("tr");
		var progGasto = $local.tablaMantenimiento.row($local.$filaDetalleSeleccionada).data();
		$local.id_ProgramacionGasto = progGasto.idProgramacionGasto;
		
		$.ajax({
	    	type : "GET",
	    	url: $variableUtil.root + "egresos/egresos/detalle/" +$local.id_ProgramacionGasto,
	    	beforeSend: function(){
	    		$local.tablaVerDetalleGastos.clear().draw();
	    	},
	    	success : function(egresos){
	    		if(egresos.length == 0){
	    			return;
	    		}
	    		$local.tablaVerDetalleGastos.rows.add(egresos).draw();
	    	}
	    });		
		
		$local.$modalVerDetalleGastos.PopupWindow("open");
	});
	
	$local.$tablaVerDetalleGastos.children("tbody").on("click", ".actualizar", function() {
		$funcionUtil.prepararFormularioActualizacion($formMantenimientoGasto);
		$local.$filaSeleccionada = $(this).parents("tr");
		var gastos = $local.tablaVerDetalleGastos.row($local.$filaSeleccionada).data();
		$local.codigoGastoSeleccionado = gastos.idGasto;
		$funcionUtil.llenarFormulario(gastos, $formMantenimientoGasto);
		$local.$partidas.trigger("change", [ gastos.clasificador ]);
		$local.$modalVerDetalleGastos.PopupWindow("hidden");
		$local.$modalActualizarGasto.PopupWindow("open");
	});
	
	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimientoGasto.valid()) {
			return;
		}
		var gastos = $formMantenimientoGasto.serializeJSON();
		gastos.idGasto = $local.codigoGastoSeleccionado;
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "egresos/gastos",
			data : JSON.stringify(gastos),
			beforeSend : function(xhr) {
				$local.$actualizarMantenimiento.attr("disabled", true).find("i").removeClass("fa-pencil-square").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimientoGasto);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimientoGasto);
				}
			},
			success : function(gastos) {
				console.log(gastos[0]);
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaVerDetalleGastos.row($local.$filaSeleccionada).data(gastos[0]).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalActualizarGasto.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$actualizarMantenimiento.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});
	
	$local.$modalActualizarGasto.PopupWindow({
		title : "Detalle de Gastos",
		autoOpen : false,
		modal : false,
		height : 500,
		width : 700,
	});
		
	$local.$modalActualizarGasto.on("open.popupwindow", function() {
		$formMantenimientoGasto.find("input:first").focus();
	});
	
});