$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$modalMantenimiento : $("#modalMantenimiento"),
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		idTipoDocumento : "",
		numeroDocumento : "",
		codigoMatricula : "",
		$verificarAlumno : $("#verificarDatos"),
		$codigoAlumno : $("#codigoAlumno"),
		$nombres : $("#nombreAlumno"),
		$apellidos : $("#apellidoAlumno"),
		$correo : $("#correoAlumno"),
		$modalidad : $("#modalidad"),
		$cuotaPendiente : $("#nroCuotasPendientes"),
		$tipoPago : $("#tipoPago"),
		$nroCiclo : $("#ciclo"),
		$costoCuota : $("#costoCuota"),
		$especializacion : $("#especializacion"),
		$fechaPago : $("#fechaPago"),
		$voucher : $("#uploadfile"),
		$documento : "",
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");

	$funcionUtil.crearDatePickerSimple($local.$fechaPago, "DD/MM/YYYY");

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
			"url" : $variableUtil.root + "ingresos/pago?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay Pagos registrados"
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5 ],
			"className" : "all filtrable",
		}, {
			"targets" : 6,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonEliminar
		} ],
		"columns" : [ {
			"data" : 'codigoAlumno',
			"title" : "Código"
		}, {
			"data" : function(row) {
				return row.apellidoAlumno+ ", " + row.nombreAlumno;
			},
			"title" : "Datos del Alumno"
		}, {
			"data" : function(row) {
				return row.nombreModalidad + " - " + row.nombreEspecializacion;
			},
			"title" : "Modalidad - Especialización"
		}, {
			"data" : 'tipoPago',
			"title" : "Tipo Pago"
		},{
			"data" : 'numeroCiclo',
			"title" : "Ciclo"
		}, {
			"data" : 'costoMatricula',
			"title" : "Monto(Soles)"
		},{
			"data" : null,
			"title" : 'Acción'
		} ]
	});


	$local.$tablaMantenimiento.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaMantenimiento.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaMantenimiento.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});

	$local.$modalMantenimiento.PopupWindow({
		title : "Registro de Pago",
		autoOpen : false,
		modal : false,
		height : 640,
		width : 636,
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
		$local.idTipoDocumento = "";
		$local.numeroDocumento = "";
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
	
	$local.$verificarAlumno.click(function(){		
		var codAlumno = $local.$codigoAlumno .val();
		if (codAlumno == null || codAlumno == undefined) {
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/pago/" + codAlumno,
			success : function(pagos) {
				$.each(pagos, function(i, pago) {				
					$local.$apellidos.val(this.apellidoAlumno);
					$local.$nombres.val(this.nombreAlumno);
					$local.$correo.val(this.correoAlumno);
					$local.$modalidad.val(this.nombreModalidad);
					$local.$tipoPago.val(this.tipoPago);
					$local.$nroCiclo.val(this.numeroCiclo);
					$local.$costoCuota.val(this.costoCuota);
					$local.$especializacion.val(this.nombreEspecializacion);
					$local.$cuotaPendiente.val(this.nroCuotasPendientes);
					$local.codigoMatricula = this.codigoMatricula;
					console.log($local.codigoMatricula);
				});
			}
		});
	});
	
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var pago = $formMantenimiento.serializeJSON();
		pago.codigoMatricula = $local.codigoMatricula;
		pago.fechaPago = $local.$fechaPago.data("daterangepicker").startDate.format("YYYY-MM-DD");	
		  
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "ingresos/pago",
			data : JSON.stringify(pago),
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
			success : function(pagos) {
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var pago = pagos[0];
				var row = $local.tablaMantenimiento.row.add(pago).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				
				var form = $("#formMantenimiento")[0];
				var data = new FormData(form);
				
				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : $variableUtil.root + "ingresos/pago/uploadfile/"+"?accion=cargar",
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					beforeSend : function(xhr) {
						xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
					},
					success : function(response) {
					},
					complete : function(response) {
					}
				});
				
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
		var persona = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		$local.idTipoDocumento = persona.idTipoDocumento;
		$local.numeroDocumento = persona.numeroDocumento;
		$funcionUtil.llenarFormulario(persona, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var persona = $formMantenimiento.serializeJSON();
		persona.idTipoDocumento = $local.idTipoDocumento;
		persona.numeroDocumento = $local.numeroDocumento;
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "mantenimiento/persona",
			data : JSON.stringify(persona),
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
			success : function(personas) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(personas[0]).draw();
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
		var pago = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		
		var codAlumno = pago.codigoAlumno;
		if (codAlumno == null || codAlumno == undefined) {
			return;
		}
		var file = $local.$voucher[0].files[0];
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/pago/voucher/" + codAlumno,
			success : function(pagos) {
				$.each(pagos, function(i, pago) {	
					console.log(pago.codigoAlumno);
				});
			}
		});
		descargarArchivo(file,"voucher");
	});
	
	function descargarArchivo(contenidoEnBlob, nombreArchivo) {
		  //creamos un FileReader para leer el Blob
		  var reader = new FileReader();
		  //Definimos la función que manejará el archivo
		  //una vez haya terminado de leerlo
		  reader.onload = function (event) {
		    //Usaremos un link para iniciar la descarga 
		    var save = document.createElement('a');
		    save.href = event.target.result;
		    save.target = '_blank';
		    //Truco: así le damos el nombre al archivo 
		    save.download = nombreArchivo || 'archivo.dat';
		    var clicEvent = new MouseEvent('click', {
		      'view': window,
		      'bubbles': true,
		      'cancelable': true
		    });
		    //Simulamos un clic del usuario
		    //no es necesario agregar el link al DOM.
		    save.dispatchEvent(clicEvent);
		    //Y liberamos recursos...
		    (window.URL || window.webkitURL).revokeObjectURL(save.href);
		  };
		  //Leemos el blob y esperamos a que dispare el evento "load"
		  reader.readAsDataURL(contenidoEnBlob);
		};
});