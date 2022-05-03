
var iniciarSesion = function () {


	var email = $("#correo").val();
	var password = $("#contrasenia").val();


	$.post("/usuario/login", { 'correo': email, 'contrasenia': password }, /*callback*/ function (fragmento) {
		$("#contenedor").replaceWith(fragmento);
	});
};

var registrarUsuario = function () {

	var nombre = $("#nombre").val();
	var apellidopat = $("#apellidoPat").val();
	var apellidomat = $("#apellidoMat").val();
	var matricula = $("#matricula").val();
	var correo = $("#correo").val();
	var password = $("#contrasenia").val();
	// falta el curso 
	$.get("/usuario/registro", {
		'nombre': nombre, 'apellidoPat': apellidopat, 'aplliedoMat': apellidomat, 'matricula': matricula,
		'correo': correo, 'contrasenia': password
	},
		function (fragmento) {
			$("#contenedor").replaceWith(fragmento);
		});
};
// registro de curso 

var guardarCurso = function () {
	var nombre = $("nombre").val();
	var descripcion = $("#descripcion").val();
	$.get("curso/guardarcurso", { 'nombre': nombre, 'descripcion': descripcion },
		function (fragmento) {
			$("#contenedor").replaceWith(fragmento);
		});
};

$(document).ready(function () {


	jQuery.validator.addMethod("sololetrasyespacios", function (value, element) {
		return this.optional(element) || /^[a-z\s]+$/i.test(value);
	}, "Solo letras y espacios");


	jQuery.extend(jQuery.validator.messages, {
		required: "Este campo es obligatorio.",
		remote: "Por favor, rellena este campo.",
		email: "Por favor, escribe una dirección de correo válida",
		url: "Por favor, escribe una URL válida.",
		date: "Por favor, escribe una fecha válida.",
		dateISO: "Por favor, escribe una fecha (ISO) válida.",
		number: "Por favor, escribe un número entero válido.",
		digits: "Por favor, escribe sólo dígitos.",
		creditcard: "Por favor, escribe un número de tarjeta válido.",
		equalTo: "Por favor, escribe el mismo valor de nuevo.",
		accept: "Por favor, escribe un valor con una extensión aceptada.",
		maxlength: jQuery.validator.format("Por favor, no escribas más de {0} caracteres."),
		minlength: jQuery.validator.format("Por favor, no escribas menos de {0} caracteres."),
		rangelength: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1} caracteres."),
		range: jQuery.validator.format("Por favor, escribe un valor entre {0} y {1}."),
		max: jQuery.validator.format("Por favor, escribe un valor menor o igual a {0}."),
		min: jQuery.validator.format("Por favor, escribe un valor mayor o igual a {0}.")
	});

	// ***************** Formularios ***************************************************

	//froma de login
	$("#forma-login").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			correo: {
				required: true,
				maxlength: 100,
				email: true
			},
			contrasenia: {
				required: true
			}
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var email = $("#correo").val();
			var password = $("#contrasenia").val();


			$.post("/usuario/login", { 'correo': email, 'contrasenia': password }, function (fragmento) {

				var newDoc = document.open("text/html", "replace");
				newDoc.write(fragmento);
				newDoc.close();


			});

			return false;
		}

	});

	// registro de usuario 
	$("#forma-registro").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			correo: {
				required: true,
				maxlength: 100,
				email: true
			},
			contrasenia: {
				required: true
			},
			nombre: {
				required: true

			},
			apellidoPat: {
				required: true
			},
			apellidoMat: {
				required: true
			},
			matricula: {
				required: true
			},
			curso: {
				required: true
			}

		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var nombre = $("#nombre").val();
			var apellidopat = $("#apellidoPat").val();
			var apellidomat = $("#apellidoMat").val();
			var matricula = $("#matricula").val();
			var correo = $("#correo").val();
			var password = $("#contrasenia").val();
			var curso = $("#curso").val();

			$.get("/usuario/registro", {
				'nombre': nombre, 'apellidoPat': apellidopat, 'apellidoMat': apellidomat, 'matricula': matricula,
				'correo': correo, 'contrasenia': password, 'curso': curso
			}, function (fragmento) {



				$('#modalMensaje').replaceWith(fragmento);

				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();

			});

			return false;
		}

	});


	//forma registrocurso
	$("#forma-registro-curso").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {

			nombre: {
				required: true

			}
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var nombre = $("#nombre").val();
			var descripcion = $("#descripcion").val();


			$.get("/curso/guardarcurso", { 'nombre': nombre, 'descripcion': descripcion }, function (fragmento) {


				$('#modalMensaje').replaceWith(fragmento);

				var myModalExample = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalGuardarCurso'));
				myModalExample.hide();
				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();

			});

			return false;
		}

	});


	//agregar actividada 

	$("#agregar-actividad-forma").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			curso: {
				required: true
			},
			descripcion: {
				required: true

			},
			objetivos: {
				required: true

			},
			puntosEvaluar: {
				required: true
			},
			puntaje: {
				required: true,
				digits: true
			}

		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var curso = $("#curso").val();
			var descripcion = $("#descripcion").val();
			var objetivos = $("#objetivos").val();
			var puntosEvaluar = $("#puntosEvaluar").val();
			var puntaje = $("#puntaje").val();


			$.post("/actividad/guardar",
				{
					'curso': curso, 'descripcion': descripcion,
					'objetivos': objetivos,
					'puntosEvaluar': puntosEvaluar,
					'puntaje': puntaje
				}, function (fragmento) {


					$('#modalMensaje').replaceWith(fragmento);

					var myModalExample = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalActividad'));
					myModalExample.hide();
					var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
					myModal.show();

				});

			return false;
		}

	});
	// modificar actividad
	$("#edita-actividad-forma").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			curso: {
				required: true
			},
			descripcion: {
				required: true

			},
			objetivos: {
				required: true

			},
			puntosEvaluar: {
				required: true
			},
			puntaje: {
				required: true,
				digits: true
			}

		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {
			var curso = $("#curso").val();
			var descripcion = $("#descripcion").val();
			var objetivos = $("#objetivos").val();
			var puntosEvaluar = $("#puntosEvaluar").val();
			var puntaje = $("#puntaje").val();


			$.post("/actividad/editarA",
				{
					'curso': curso, 'descripcion': descripcion,
					'objetivos': objetivos,
					'puntosEvaluar': puntosEvaluar,
					'puntaje': puntaje
				}, function (fragmento) {


					$('#modalMensaje').replaceWith(fragmento);

					var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
					myModal.show();
				});

			return false;
		}

	});

	// respuesta de actividad 
	$("#forma-respuesta").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			actividad: {
				required: true
			},
			github: {
				required: true,
				url: true
			},
			drive: {
				url: true
			}
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var actividad = $("#actividad").val();
			var github = $("#github").val();
			var drive = $("#drive").val();

			$.post("/respuesta/guardar", { 'github': github, 'drive': drive, 'actividad': actividad }, function (fragmento) {


				$('#modalMensaje').replaceWith(fragmento);

				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();

			});

			return false;
		}

	});
	// eliminacion de actividades 

	$("#borrar-actividad").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			id: {
				required: true,
				number: true
			}
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var id_actividad = $("#id").val();
			$.get("/actividad/eliminaractividad", { 'id': id_actividad }, function (fragmento) {

				$('#modalMensaje').replaceWith(fragmento);

				var myModalExample = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalBorrarActividad'));
				myModalExample.hide();
				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();

			});
			return false;
		}
	});

	// borrado de cursos 

	$("#borrar-curso").submit(function (e) {

		e.preventDefault();

	}).validate({
		rules: {
			id: {
				required: true
			}
		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var id_curso = $("#id").val();
			$.get("curso/eliminarcurso", { 'id': id_curso }, function (fragmento) {

				setTimeout(function () {
					$("#modalBorrar").modal("hide").fadeIn("slow");
				}, 1500);

				$('#modalMensaje').replaceWith(fragmento);

				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();

			});
			return false;
		}
	});


	//************************** */ funciones *****************	


	// obtencion de cursos y actividades para su gestion

	getActividad = function (selectObject) {
		var value = selectObject.value;
		//console.log(value);
		
	
	}

	getCurso = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

	getUsuario = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

	obtenerCursos = function () {

		$.get("/curso/buscar", {}, function (fragmento) {
			var newDoc = document.open("text/html", "replace");
			newDoc.write(fragmento);
			newDoc.close();

		});

	};

	obtenerActividades = function () {

		$.get("/actividad/buscar", {}, function (fragmento) {
			var newDoc = document.open("text/html", "replace");
			newDoc.write(fragmento);
			newDoc.close();

		});

	};

	// obtencion de los usuarios para el select de evaluación 
	obtenerUsuarioSelect = function () {

		$.get("/usuario/buscar", {}, function (fragmento) {

			var newDoc = document.open("text/html", "replace");
			newDoc.write(fragmento);
			newDoc.close();
		});
	};



	// obtencion de los cursos para el formulario de registro 
	obtenerCursoSelect = function () {

		$.get("/curso/buscars", {}, function (fragmento) {
			var newDoc = document.open("text/html", "replace");
			newDoc.write(fragmento);
			newDoc.close();

		});

	};

	//
	obtenerCursoA = function () {

		$.get("/curso/buscarCA", {}, function (fragmento) {
			var newDoc = document.open("text/html", "replace");
			newDoc.write(fragmento);
			newDoc.close();

		});

	};
	// obtencion de actividades para el select de respuestas 
	obtenerActividadSelect = function () {

		$.get("/actividad/buscars", {}, function (fragmento) {
			var newDoc = document.open("text/html", "replace");
			newDoc.write(fragmento);
			newDoc.close();

		});

	};



});

//borrar usuario
$(document).ready(function (params) {
	$(".link-delete").on("click", function (e) {
		e.preventDefault();
		//alert($(this).attr("href"));
		link = $(this);
		usuarioId = link.attr("usuarioId");
		$("#siButton").attr("href", link.attr("href"));
		$("#confirmText").text("¿Estás seguro de que quieres eliminar el ID " + usuarioId + "?");
		$("#modalBorrar").modal();
	});
});





