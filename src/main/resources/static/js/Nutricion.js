
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
			}

		},
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		},
		submitHandler: function (form) {

			var nombre = $("#nombre").val();
			var apellidopat = $("#apellidoPat").val();
			var apellidomat = $("#apellidoMat").val();
			var correo = $("#correo").val();
			var password = $("#contrasenia").val();
			

			$.get("/usuario/registro", {
				'nombre': nombre, 'apellidoPat': apellidopat, 'apellidoMat': apellidomat,
				'correo': correo, 'contrasenia': password
			}, function (fragmento) {



				$('#modalMensaje').replaceWith(fragmento);

				var myModal = bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalExitosoError'));
				myModal.show();

			});

			return false;
		}

	});


	
	
	// eliminacion de actividades 

	//************************** */ funciones *****************	


	

	getUsuario = function (selectObject) {
		var value = selectObject.value;
		console.log(value);
	}

	

	// obtencion de los usuarios para el select de evaluación 
	obtenerUsuarioSelect = function () {

		$.get("/usuario/buscar", {}, function (fragmento) {

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





