$(document).ready(function() {

	var $local = {
		$tablaMantenimiento : $("#tablaMantenimiento"),
		tablaMantenimiento : "",
		$modalMantenimiento : $("#modalMantenimiento"),
		$aniadirMantenimento : $("#aniadirMantenimiento"),
		$registrarMantenimiento : $("#registrarMantenimiento"),
		$filaSeleccionada : "",
		$actualizarMantenimiento : $("#actualizarMantenimiento"),
		idMatricula :"",
		idTipoDocumento : "",
		numeroDocumento : "",
		$verificarAlumno : $("#verificarDatos"),
		$numeroDocumento : $("#numeroDocumento"),
		$nombres : $("#nombreAlumno"),
		$apellidos : $("#apellidoAlumno"),
		$correo : $("#correoAlumno"),
		$modalidades : $("#modalidades"),
		$costoMatricula : $("#costoMatricula"),
		$tiposDocumento : $("#tiposDocumento"),
		$tiposPago : $("#tiposPago"),
		$conceptosPago : $("#conceptosPago"),
		$especializaciones : $("#especializaciones"),
		$numeroCiclos : $("#numeroCiclo"),
		$fechaMatricula : $("#fechaMatricula"),
		$voucher : $("#uploadfile"),
		$documento : "",
		$costoMatriculaCiclo : 0.0,
		matriculas : [],
		especializaciones : [],
		filtrosSeleccionables : {}
	};

	$formMantenimiento = $("#formMantenimiento");

	$funcionUtil.crearSelect2($local.$modalidades, "Seleccione una Modalidad");
	$funcionUtil.crearSelect2($local.$especializaciones, "Seleccione una Especialización");
	$funcionUtil.crearSelect2($local.$tiposPago, "Seleccione un tipo de pago");
	$funcionUtil.crearSelect2($local.$conceptosPago, "Seleccione un concepto de pago");
	$funcionUtil.crearSelect2($local.$tiposDocumento, "Seleccione un Tipo de Documento");
	$funcionUtil.crearDatePickerSimple3($local.$fechaMatricula, "DD/MM/YYYY");

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
			"url" : $variableUtil.root + "registro/matricula?accion=buscarTodos",
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
			"targets" : [ 0, 1, 2, 3, 4, 5, 6 ],
			"className" : "all filtrable",
		}, {
			"targets" : 7,
			"className" : "all dt-center",
			"defaultContent" : $variableUtil.botonActualizar + " " + $variableUtil.botonDescargar
		} ],
		"columns" : [ {
			"data" : 'fechaMatricula',
			"title" : "Fecha Matricula"
		}, {
			"data" : function(row) {
				return row.tipoDocumento + " - " + row.numeroDocumento;
			},
			"title" : "Documento"
		}, {
			"data" : function(row) {
				return row.apellidoAlumno + ", " + row.nombreAlumno;
			},
			"title" : "Alumno"
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
		height : 700,
		width : 1010,
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
	
	$local.$verificarAlumno.click(function(){		
		var codAlumno = $local.$numeroDocumento.val();		
		var tipoDoc = $local.$tiposDocumento.val();
		if (codAlumno == null || codAlumno == undefined || codAlumno == "") {
			$funcionUtil.notificarException($variableUtil.codigoAlumnoVacio, "fa-exclamation-circle", "Información", "danger");
			return;
		}else{
			$.ajax({
				type : "GET",
				url : $variableUtil.root + "registro/matricula/" + tipoDoc + "/" + codAlumno,
				success : function(matriculas) {
					if(matriculas.length == 0){
						$funcionUtil.notificarException($variableUtil.alumnoNoEncontrado, "fa-exclamation-circle", "Información", "danger");
						return;
					}
					else{
						var banderaDoctorado = false;
						var banderaMaestria = false;
						$local.matriculas = matriculas;
						var matricula = matriculas[matriculas.length-1];
						
						$local.$apellidos.val(matricula.apellidoAlumno);
						$local.$nombres.val(matricula.nombreAlumno);
						$local.$correo.val(matricula.correoAlumno);
						
						/*if(matricula.idModalidad == 'M100'){
							console.log("Entro en bandera M100" );
							banderaDoctorado = true;
							if(matricula.estadoCiclo == 0){
								$local.$numeroCiclos.val(matricula.numeroCiclo + 1);
								devolverCosto(matricula.idEspecializacion, matricula.numeroCiclo+1);
								$local.matriculas.push(matricula);
								$local.$modalidades.val(matricula.idModalidad).trigger("change.select2"); 
								$local.$modalidades.trigger("change", [ matricula.idEspecializacion ]);
								
							}
						}
						
						if(matricula.idModalidad == 'M101'){
							console.log("Entro en bandera M101");
							banderaMaestria = true;
							if(matricula.estadoCiclo == 0){
								$local.$numeroCiclos.val(matricula.numeroCiclo + 1);
								$local.$costoMatricula.val(matricula.costoMatricula);
								devolverCosto(matricula.idEspecializacion, matricula.numeroCiclo+1);
								$local.$modalidades.val(matricula.idModalidad).trigger("change.select2"); 
								$local.$modalidades.trigger("change", [ matricula.idEspecializacion ]);
							}
						}*/
						
						console.log("Cantidad de matriculas : "+matriculas.length);
						$.each(matriculas, function(i, matricula) {		
							if(matricula.idModalidad == 'M100'){
								if(matricula.estadoCiclo == 0){
									banderaDoctorado = true;
								}
								
							}
							
							if(matricula.idModalidad == 'M101'){
								console.log("Entro en bandera M101");
								if(matricula.estadoCiclo == 0){
									banderaMaestria = true;
								}
							}
						});
						
						if(banderaDoctorado){
							$local.$modalidades.find('option[value="'+"M101"+'"]').remove();
						}
						if(banderaMaestria){
							$local.$modalidades.find('option[value="'+"M100"+'"]').remove();
						}
					}
				}
			});
		}
	});

	function eliminateDuplicates(arr) {
		 var i,
		     len=arr.length,
		     out=[],
		     obj={};

		 for (i=0;i<len;i++) {
		    obj[arr[i]]=0;
		 }
		 for (i in obj) {
		    out.push(i);
		 }
		 return out;
	}
	
	$local.$modalidades.on("change", function(event, opcionSeleccionada) {
		var idModalidad = $(this).val();
		var nroCiclo = $local.$numeroCiclos.val();	
		if (nroCiclo == "")
			nroCiclo = 0;
		
		if (idModalidad == null || idModalidad == undefined) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/modalidad/" + idModalidad + "/" + nroCiclo,
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
				$local.especializaciones = especializaciones;
				console.log("Entro en modalidad "+idModalidad+ " " +nroCiclo);
				$.each(especializaciones, function(i, especializacion) {
					console.log("Entro en modalidad2");
					$local.$especializaciones.append($("<option />").val(this.idEspecializacion).text(this.nombreEspecializacion));
				});
				if (opcionSeleccionada != null && opcionSeleccionada != undefined) {
					$local.$especializaciones.val(opcionSeleccionada).trigger("change.select2");
				}
			},
			complete : function() {
				$local.$especializaciones.parent().find(".cargando").remove();
			}
		});

	});
	
	$local.$especializaciones.on("change", function(event)  {
		var idEspecializacion = $(this).val();

		if (idEspecializacion == null || idEspecializacion == undefined || idEspecializacion == -1) {
			$local.$especializaciones.find("option:not(:eq(0))").remove();
			$local.$costoMatricula.val("");
			return;
		}

		if($local.matriculas.length == 0){
			$local.$numeroCiclos.val("1");
			devolverCosto(idEspecializacion, 1);
		}
		else{
			var array = [];
			$.each($local.matriculas, function(i, matricula) {
				console.log(matricula);
				if(idEspecializacion == matricula.idEspecializacion){
					array.push(matricula)
				}
			});
			var bandera = 1;
			
			var matriculaAuxiliar = $local.matriculas.pop();
			var esp = $local.especializaciones.find(function(especializacion) {
				  return especializacion.idEspecializacion == matriculaAuxiliar.idEspecializacion;
			});
			
			if(matriculaAuxiliar.estadoCiclo == 1){
				if(idEspecializacion == matriculaAuxiliar.idEspecializacion){
					if(matriculaAuxiliar.numeroCiclo+1<=esp.numCiclos){
						console.log("Matricula : "+matriculaAuxiliar.idEspecializacion + " " + matriculaAuxiliar.numeroCiclo);
						$local.$numeroCiclos.val(matriculaAuxiliar.numeroCiclo + 1);
						devolverCosto(idEspecializacion, $local.$numeroCiclos.val());
						bandera = 2;
					}
					else{
						$local.$numeroCiclos.val("");
						$funcionUtil.notificarException($variableUtil.ciclosCompletos, "fa-exclamation-circle", "Información", "info");
						bandera = 3;
					}
				}
			}
			else{
				bandera = 4;
				$funcionUtil.notificarException($variableUtil.cicloEnProceso, "fa-exclamation-circle", "Información", "danger");
			}
			
			if(bandera==1){
				$local.$numeroCiclos.val("1");
				devolverCosto(idEspecializacion, $local.$numeroCiclos.val());
			}
			
		}
	});
	
	$local.$registrarMantenimiento.on("click", function() {
		if (!$formMantenimiento.valid()) {
			return;
		}
		var matricula = $formMantenimiento.serializeJSON();
		matricula.fechaMatricula = $local.$fechaMatricula.data("daterangepicker").startDate.format("YYYY-MM-DD");
		console.log(matricula);
		//matricula.nombreArchivo = $local.$voucher.val();
		//matricula.idModalidad = $local.$modalidades.val();
		//matricula.nombreModalidad = $("#modalidades option:selected").text().substring(7);
		//matricula.idEspecializacion = $local.$especializaciones.val();
		//matricula.numeroCiclo = $local.$numeroCiclos.val();
		//matricula.nombreEspecializacion = $("#especializaciones option:selected").text().substring(7);
		//matricula.tipoPago = $local.$tiposPago.val();
		//matricula.conceptoPago = $local.$conceptosPago.val();		
		
		
		matricula.nombreModalidad = $("#modalidades option:selected").text();
		matricula.nombreEspecializacion = $("#especializaciones option:selected").text();
		matricula.conceptoPago = $("#conceptosPago option:selected").text();	
		matricula.descTipoPago = $("#tiposPago option:selected").text().substring(5);
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "registro/matricula",
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
					url : $variableUtil.root + "registro/matricula/uploadfile/"+"?accion=cargar",
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
		$local.$conceptosPago.val(matricula.conceptoPago).trigger("change.select2");	
		console.log("select "+$local.$conceptosPago);
		$local.$tiposPago.val(matricula.idTipoPago).trigger("change.select2"); 		
		$local.idMatricula = matricula.idMatricula;		
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
		console.log($local.idMatricula);
		matricula.idMatricula = $local.idMatricula;
		matricula.nombreArchivo = $local.$voucher.val();
		matricula.idModalidad = $local.$modalidades.val();
		matricula.numeroCiclo = $local.$numeroCiclos.val();
		matricula.idEspecializacion = $local.$especializaciones.val();
		matricula.tipoPago = $local.$tiposPago.val();
		matricula.conceptoPago = $local.$conceptosPago.val();
		matricula.fechaMatricula = $local.$fechaMatricula.data("daterangepicker").startDate.format("YYYY-MM-DD");	
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "registro/matricula",
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
				
				var form = $("#formMantenimiento")[0];
				var data = new FormData(form);
				
				var row = $local.tablaMantenimiento.row($local.$filaSeleccionada).data(matriculas[0]).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				
				$.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : $variableUtil.root + "registro/matricula/uploadfile/"+ $local.idMatricula +"?accion=actualizar",
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
				$local.$actualizarMantenimiento.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaMantenimiento.children("tbody").on("click", ".descargar", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		
		var matricula = $local.tablaMantenimiento.row($local.$filaSeleccionada).data();
		console.log(matricula);
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "registro/matricula/voucher/" + matricula.idMatricula,
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
	
	function blobToFile(blob, filename){
		var file = new File([blob], filename, {type: "application/pdf", lastModified: Date.now()});
		return file;
	}
	
	function download(text, filename){
		  var blob = new Blob([text], {type: "application/pdf"});
		  var url = window.URL.createObjectURL(blob);
		  var a = document.createElement("a");
		  a.href = url;
		  a.download = filename;
		  a.click();
	};
	
	/**
	 * Esta función se encargará de traer el costo de la matrícula 
	 * dependiendo de la especialización y del ciclo
	 */
	function devolverCosto(idEspecializacion, nroCiclo){
		console.log(idEspecializacion + " " +nroCiclo);
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/costo/" + idEspecializacion + "/" + nroCiclo,
			beforeSend : function(xhr) {
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
				var esp = especializaciones[0];
				$local.$costoMatricula.val(esp.costoMatriculaUPG);
			},
			complete : function() {
			}
		});
	}
	
	function devolverNumeroCiclo(idEspecializacion){
		var numCiclo = 0;
		return $.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/especializacion/" + idEspecializacion,
			beforeSend : function(xhr) {
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
				var esp = especializaciones[0];
			},
			complete : function() {
			}
		});
	}
	
});