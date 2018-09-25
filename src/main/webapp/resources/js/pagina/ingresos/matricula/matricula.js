$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$modalMantenimiento : $("#modalMantenimiento"),
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		codigoMatricula :"",
		idTipoDocumento : "",
		numeroDocumento : "",
		$verificarAlumno : $("#verificarDatos"),
		$codigoAlumno : $("#codigoAlumno"),
		$nombres : $("#nombreAlumno"),
		$apellidos : $("#apellidoAlumno"),
		$correo : $("#correoAlumno"),
		$modalidades : $("#modalidades"),
		$costoMatricula : $("#costoMatricula"),
		$tiposPago : $("#tiposPago"),
		$especializaciones : $("#especializaciones"),
		$numeroCiclos : $("#numeroCiclo"),
		$fechaMatricula : $("#fechaMatricula"),
		$voucher : $("#uploadfile"),
		$documento : "",
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");

	$funcionUtil.crearSelect2($local.$modalidades, "--Selecciona Modalidad--");
	$funcionUtil.crearSelect2($local.$especializaciones, "--Selecciona Especialización--");
	$funcionUtil.crearSelect2($local.$numeroCiclos, "--Seleccione Ciclo--");
	$funcionUtil.crearSelect2($local.$tiposPago, "--Seleccione tipo de pago--");
	$funcionUtil.crearDatePickerSimple($local.$fechaMatricula, "DD/MM/YYYY");

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
			"url" : $variableUtil.root + "ingresos/matricula?accion=buscarTodos",
			"dataSrc" : ""
		},
		"language" : {
			"emptyTable" : "No hay Alumnos matriculados"
		},
		"initComplete" : function() {
			$local.$tablaMantenimiento.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaMantenimiento);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7 ],
			"className" : "all filtrable",
		}, {
			"targets" : 8,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonDescargar
		} ],
		"columns" : [ {
			"data" : 'codigoMatricula',
			"title" : "Matricula"
		}, {
			"data" : 'codigoAlumno',
			"title" : "Código"
		},{
			"data" : function(row) {
				return row.apellidoAlumno+ ", " + row.nombreAlumno;
			},
			"title" : "Datos del Alumno"
		}, {
			"data" : 'nombreModalidad',
			"title" : "Modalidad"
		}, {
			"data" : 'nombreEspecializacion',
			"title" : "Especialización"
		}, {
			"data" : 'numeroCiclo',
			"title" : "Ciclo"
		}, {
			"data" : 'costoMatricula',
			"title" : "Costo(Soles)"
		},{
			"data" : 'fechaMatricula',
			"title" : "Fecha Matricula"
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
		title : "Registro de Matricula",
		autoOpen : false,
		modal : false,
		height : 600,
		width : 900,
	});

	$local.$aniadirMantenimento.on("click", function() {
		$funcionUtil.prepararFormularioRegistro2($formMantenimiento);
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

	$local.$modalidades.on("change", function(event, opcionSeleccionada) {
		var idModalidad = $(this).val();
		if (idModalidad == null || idModalidad == undefined) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad,
			beforeSend : function(xhr) {
				$local.$especializaciones.find("option:not(:eq(0))").remove();
				$local.$especializaciones.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Especializaciones</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(especializaciones) {
				$.each(especializaciones, function(i, especializacion) {
					$local.$especializaciones.append($("<option />").val(this.idEspecializacion).text(this.idEspecializacion + " - " + this.nombreEspecializacion));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					console.log(" 123 "+opcionSeleccionada);
					$local.$especializaciones.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$especializaciones.parent().find(".cargando").remove();
			}
		});

	});
	
	$local.$especializaciones.on("change", function(event, opcionSeleccionada) {
		var idEspecializacion = $(this).val();
		if (idEspecializacion == null || idEspecializacion == undefined) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/costo/" + idEspecializacion,
			beforeSend : function(xhr) {
				$local.$numeroCiclos.find("option:not(:eq(0))").remove();
				$local.$numeroCiclos.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Ciclos</span>")
			},
			success : function(especializaciones) {
				var j = 1;
				$.each(especializaciones, function(i, especializacion) {
					console.log(especializacion);
					while(j<=especializacion.numCiclos){
						$local.$numeroCiclos.append($("<option />").val(j).text(j));
						console.log(j);
						j++;
					}
					$local.$costoMatricula.val(this.costoMatricula);
				});
			},
			complete : function() {
				$local.$numeroCiclos.parent().find(".cargando").remove();
			}
		});

	});
	
	$local.$verificarAlumno.click(function(){		
		var codAlumno = $local.$codigoAlumno .val();
		if (codAlumno == null || codAlumno == undefined || codAlumno == "") {
			$funcionUtil.notificarException($variableUtil.codigoAlumnoVacio, "fa-exclamation-circle", "Información", "danger");
			return;
		}else{
			$.ajax({
				type : "GET",
				url : $variableUtil.root + "ingresos/matricula/" + codAlumno,
				success : function(matriculas) {
					if(matriculas.length == 0){
						$funcionUtil.notificarException($variableUtil.alumnoNoEncontrado, "fa-exclamation-circle", "Información", "danger");
						return;
					}
					else{
						$.each(matriculas, function(i, matricula) {				
							$local.$apellidos.val(this.apellidoAlumno);
							$local.$nombres.val(this.nombreAlumno);
							$local.$correo.val(this.correoAlumno);
						});
					}
				}
			});
		}
	});
	
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var matricula = $formMantenimiento.serializeJSON();
		matricula.nombreArchivo = $local.$voucher.val();
		matricula.idModalidad = $local.$modalidades.val();
		matricula.nombreModalidad = $("#modalidades option:selected").text().substring(7);
		matricula.idEspecializacion = $local.$especializaciones.val();
		matricula.numeroCiclo = $local.$numeroCiclos.val();
		matricula.nombreEspecializacion = $("#especializaciones option:selected").text().substring(7);
		matricula.tipoPago = $local.$tiposPago.val();
		matricula.fechaMatricula = $local.$fechaMatricula.data("daterangepicker").startDate.format("YYYY-MM-DD");		
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "ingresos/matricula",
			data : JSON.stringify(matricula),
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
			success : function(matriculas) {
				var form = $("#formMantenimiento")[0];
				var data = new FormData(form);
				
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var matricula = matriculas[0];
				var row = $local.tablaMantenimiento.row.add(matricula).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				
				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : $variableUtil.root + "ingresos/matricula/uploadfile/"+"?accion=cargar",
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
		var matricula = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();

		$local.$modalidades.val(matricula.idModalidad).trigger("change.select2"); 

		$local.$modalidades.trigger("change", [ matricula.idEspecializacion ]);
		
		$local.$tiposPago.val(matricula.tipoPago).trigger("change.select2"); 
		
		$local.codigoMatricula = matricula.codigoMatricula;
		
		var contentType = "application/pdf";
		var file = b64toBlob (matricula.bytesLeidos,contentType);
		
		$funcionUtil.llenarFormulario(matricula, $formMantenimiento);
		$local.$actualizarMantenimiento.removeClass("hidden");
		$local.$registrarMantenimiento.addClass("hidden");
		$local.$modalMantenimiento.PopupWindow("open");
	});

	$local.$actualizarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var matricula = $formMantenimiento.serializeJSON();
		console.log($local.codigoMatricula);
		matricula.codigoMatricula = $local.codigoMatricula;
		matricula.nombreArchivo = $local.$voucher.val();
		matricula.idModalidad = $local.$modalidades.val();
		matricula.idEspecializacion = $local.$especializaciones.val();
		matricula.tipoPago = $local.$tiposPago.val();
		matricula.fechaMatricula = $local.$fechaMatricula.data("daterangepicker").startDate.format("YYYY-MM-DD");	
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "ingresos/matricula",
			data : JSON.stringify(matricula),
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
			success : function(matriculas) {
				$funcionUtil.notificarException($variableUtil.actualizacionExitosa, "fa-check", "Aviso", "success");
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(matriculas[0]).draw();
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

	$local.$tablaMantenimiento.children("tbody").on("click", ".descargar", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		
		var matricula = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "ingresos/matricula/voucher/" + matricula.codigoMatricula,
			beforeSend : function(xhr) {
				$local.$registrarMantenimiento.attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			success : function(matricula) {
				var contentType = "application/pdf";
				var file = b64toBlob (matricula.bytesLeidos,contentType);
				download(file, "voucher");
			}
		});
		
	});
	
	function b64toBlob(b64Data, contentType, sliceSize) {
		  contentType = contentType || '';
		  sliceSize = sliceSize || 512;

		  var byteCharacters = atob(b64Data);
		  var byteArrays = [];

		  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
		    var slice = byteCharacters.slice(offset, offset + sliceSize);

		    var byteNumbers = new Array(slice.length);
		    for (var i = 0; i < slice.length; i++) {
		      byteNumbers[i] = slice.charCodeAt(i);
		    }

		    var byteArray = new Uint8Array(byteNumbers);

		    byteArrays.push(byteArray);
		  }

		  var blob = new Blob(byteArrays, {type: contentType});
		  return blob;
	};
	
	function download(text, filename){
		  var blob = new Blob([text], {type: "application/pdf"});
		  var url = window.URL.createObjectURL(blob);
		  var a = document.createElement("a");
		  a.href = url;
		  a.download = filename;
		  a.click();
	};
});