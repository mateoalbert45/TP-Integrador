"use strict";
document.querySelector("#submitPostViaje").addEventListener("click", postViaje);
document.querySelector("#submitApvVuelo").addEventListener("click", asignarPlanVuelo);
document.querySelector("#submitGetVuelos").addEventListener("click", getVuelos);
document.querySelector("#submitLogin").addEventListener("click", login);
document.querySelector("#submitAsignarPlan").addEventListener("click", asignarPlan);
document.querySelector("#submitRegister").addEventListener("click", postUsuario);
document.querySelector("#submitPlanesSegunViaje").addEventListener("click", planesSegunViaje);
document.querySelector("#submitPorRealizar").addEventListener("click", getReporteViajesPorRealizar);
document.querySelector("#submitFinalizados").addEventListener("click", getReporteViajesFinalizados);
document.querySelector("#submitRango").addEventListener("click", getReporteViajesRango);
document.querySelector("#submitViajeFiltroZona").addEventListener("click", getViajesFiltroZona);
document.querySelector("#submitGetUsuarioMasViajes").addEventListener("click", getUsuarioMasViajes);
document.querySelector("#submitGetZona").addEventListener("click", getViajesZona);
document.querySelector("#submitAphHotel").addEventListener("click", asignarPlanHotel);
let token = " ";









function postViaje() { //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base
	let idUsuario = document.querySelector("#idUsuario").value;
	let id = document.querySelector("#idAddViaje").value;
	let nombre = document.querySelector("#idAddNombre").value;
	let fechaIni = document.querySelector("#idAddFechaInicio").value;
	let fechaFin = document.querySelector("#idAddFechaFin").value;
	let descripcion = document.querySelector("#idAddDescripcion").value;
	let urlAddViaje = "viaje/add";
	let urlAsignarUsuario = "viaje/asignarUsuario/" + id + "/" + idUsuario;

	let viaje = {
		"id": id,
		"nombre": nombre,
		"fechaInicio": fechaIni,
		"fechaFin": fechaFin,
		"descripcion": descripcion
	};
	console.log(viaje);
	console.log(token);
	fetch(urlAddViaje, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json',
			'Authorization': token
		},
		'body': JSON.stringify(viaje)
	}).then(function () {
		fetch(urlAsignarUsuario, {
			'method': 'POST',
			'headers': {
				'Content-Type': 'application/json',
				'Accept': 'application/json',
				'Authorization': token
			},
		})
	}, function () {
		// rechazo
	});
}


//carga datos desde archivo .txt
let openFile1 = function (event) {
	let input = event.target;
	let reader = new FileReader();
	reader.onload = function () {
		let texto = reader.result;
		let texto_array = texto.split("\r\n");
		let id;
		let nombre;
		let fechaIni;
		let fechaFin;
		let descripcion;
		let i = 0;
		for (let element in texto_array) {
			let json_element;
			if (i % 5 == 0)
				id = texto_array[element];
			else if (i % 5 == 1)
				nombre = texto_array[element];
			else if (i % 5 == 2)
				fechaIni = texto_array[element];
			else if (i % 5 == 3)
				fechaFin = texto_array[element];
			else if (i % 5 == 4) {
				descripcion = texto_array[element];
				let viaje = {
					"id": id,
					"nombre": nombre,
					"fechaInicio": fechaIni,
					"fechaFin": fechaFin,
					"descripcion": descripcion
				};
				console.log(viaje);
				let url = "viaje/add";
				fetch(url, {
					'method': 'POST',
					'headers': {
						'Content-Type': 'application/json',
						'Accept': 'application/json',
						'Authorization': token
					},
					'body': JSON.stringify(viaje)
				})
			}
			i++;
		}
	};
	reader.readAsText(input.files[0]);
}


function postUsuario() { //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base

	let id = document.querySelector("#idUsuarioReg").value;
	let nombre = document.querySelector("#idNombreReg").value;
	let contraseña = document.querySelector("#idContraseñaReg").value;
	let mail = document.querySelector("#idMailReg").value;

	let usuario = {
		"id": id,
		"nombre": nombre,
		"contraseña": contraseña,
		"mail": mail,

	};
	console.log(usuario);
	let url = "usuario/add";
	fetch(url, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': JSON.stringify(usuario)
	})
}

function asignarPlanVuelo() { //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base
	let idViaje = document.querySelector("#idApvViaje").value;
	let idPlan = document.querySelector("#idApvPlan").value;
	let descripcion = document.querySelector("#idApvDescripcion").value;
	let idVuelo = document.querySelector("#idApvVuelo").value;
	let compañia = document.querySelector("#idApvCompañia").value;
	let fechaSalida = document.querySelector("#idApvFechaSalida").value;
	let fechaLlegada = document.querySelector("#idApvFechaLlegada").value;
	let aeropuertoSalida = document.querySelector("#idApvAeropuertoSalida").value;
	let aeropuertoLlegada = document.querySelector("#idApvAeropuertoLlegada").value;
	let codigoReserva = document.querySelector("#idApvReserva").value;
	let tiempoEntreEscalas = document.querySelector("#idApvTiempo").value;
	let informacionAeronave = document.querySelector("#idApvInfo").value;

	let urlAddPlanVuelo = "plan/addPlanVuelo/" + idPlan + "/" + descripcion;
	let urlAsignarPlanVuelo = "viajePlan/asignarPlanViaje/" + idViaje + "/" + idPlan;

	let vuelo = {
		"id": idVuelo,
		"compañia": compañia,
		"fechaSalida": fechaSalida,
		"fechaLlegada": fechaLlegada,
		"aeropuertoSalida": aeropuertoSalida,
		"aeropuertoLlegada": aeropuertoLlegada,
		"codigoReserva": codigoReserva,
		"tiempoEntreEscalas": tiempoEntreEscalas,
		"informacionAeronave": informacionAeronave
	};

	let plan = {
		"id": idPlan,
		"descripcion": descripcion,
		"vuelo": vuelo
	}
	console.log(plan);

	fetch(urlAddPlanVuelo, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json',
			'Authorization': token
		},
		'body': JSON.stringify(vuelo)
	}).then(function () {
		fetch(urlAsignarPlanVuelo, {
			'method': 'POST',
			'headers': {
				'Content-Type': 'application/json',
				'Accept': 'application/json',
				'Authorization': token
			},
		})
	}, function () {
		// rechazo
	});
}

function asignarPlanHotel() { //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base
	let idViaje = document.querySelector("#idAphViaje").value;
	let idPlan = document.querySelector("#idAphPlan").value;
	let descripcion = document.querySelector("#idAphDescripcion").value;
	let idHotel = document.querySelector("#idAphHotel").value;
	let nombre = document.querySelector("#idAphNombre").value;
	let ciudad = document.querySelector("#idAphCiudad").value;
	let fechaLlegada = document.querySelector("#idAphFechaLlegada").value;
	let fechaSalida = document.querySelector("#idAphFechaSalida").value;

	let urlAddPlanHotel = "plan/addPlanHotel/" + idPlan + "/" + descripcion;
	let urlAsignarPlanHotel = "viajePlan/asignarPlanViaje/" + idViaje + "/" + idPlan;

	let hotel = {
		"id": idHotel,
		"nombre": nombre,
		"ciudad": ciudad,
		"fechaLlegada": fechaLlegada,
		"fechaSalida": fechaSalida
	};

	let plan = {
		"id": idPlan,
		"descripcion": descripcion,
		"hotel": hotel
	}
	console.log(plan);

	fetch(urlAddPlanHotel, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json',
			'Authorization': token
		},
		'body': JSON.stringify(hotel)
	}).then(function () {
		fetch(urlAsignarPlanHotel, {
			'method': 'POST',
			'headers': {
				'Content-Type': 'application/json',
				'Accept': 'application/json',
				'Authorization': token
			},
		})
	}, function () {
		// rechazo
	});
}

//carga datos desde archivo .txt
let openFile2 = function (event) {
	let input = event.target;
	let reader = new FileReader();
	reader.onload = function () {
		let texto = reader.result;
		let texto_array = texto.split("\r\n");
		let idViaje;
		let idPlan;
		let descrip;
		let idHotel;
		let nombre;
		let ciudad;
		let cantEstre;
		let fechaIni;
		let fechaFin;
		let i = 0;
		for (let element in texto_array) {
			let json_element;
			if (i % 9 == 0)
				idViaje = texto_array[element];
			else if (i % 9 == 1)
				idPlan = texto_array[element];
			else if (i % 9 == 2)
				descrip = texto_array[element];
			else if (i % 9 == 3)
				idHotel = texto_array[element];
			else if (i % 9 == 4)
				nombre = texto_array[element];
			else if (i % 9 == 5)
				ciudad = texto_array[element];
			else if (i % 9 == 6)
				cantEstre = texto_array[element];
			else if (i % 9 == 7)
				fechaIni = texto_array[element];
			else if (i % 9 == 8) {
				fechaFin = texto_array[element];
				let hotel = {
					"id": idHotel,
					"nombre": nombre,
					"ciudad": ciudad,
					"fechaLlegada": fechaIni,
					"fechaSalida": fechaFin
				};

				let plan = {
					"id": idPlan,
					"descripcion": descrip,
					"hotel": hotel
				}

				let urlAddPlanHotel = "plan/addPlanHotel/" + idPlan + "/" + descrip;
				let urlAsignarPlanHotel = "viajePlan/asignarPlanViaje/" + idViaje + "/" + idPlan;

				fetch(urlAddPlanHotel, {
					'method': 'POST',
					'headers': {
						'Content-Type': 'application/json',
						'Accept': 'application/json',
						'Authorization': token
					},
					'body': JSON.stringify(hotel)
				}).then(function () {
					fetch(urlAsignarPlanHotel, {
						'method': 'POST',
						'headers': {
							'Content-Type': 'application/json',
							'Accept': 'application/json',
							'Authorization': token
						},
					})
				}, function () {
					// rechazo
				});


			}
			i++;
		}
	};
	reader.readAsText(input.files[0]);
}

async function getVuelos() {
	let idUsuario = document.querySelector("#idUsuario").value;
	let url = "vuelo/getVuelosSegunUsuario/" + idUsuario;
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
			 'mode': 'cors',
			 'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorVuelo");
	contenedor.innerHTML = JSON.stringify(json);
}



async function login() {
	let nombre = document.querySelector("#idNombre").value;
	let contraseña = document.querySelector("#idContraseña").value;
	let url = "user" + "?user=" + nombre + "&password=" + contraseña;
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	//json['id'][1]['powers'][2]
	let contenedor = document.querySelector("#idUsuario");
	contenedor.value = JSON.stringify(json["id"]);
	token = json["token"];
}


function asignarPlan() {
	let idViaje = document.querySelector("#idAsignarPlanViaje").value;
	let idPlan = document.querySelector("#idAsignarPlan").value;
	let info = document.querySelector("#idAsignarDescripcion").value;
	let plan = {
		"id": idPlan,
		"descripcion": info,
	};
	console.log(plan);
	let url = "viajePlan/asignarPlanViaje/" + idViaje + "/" + idPlan;
	let urlAddPlan = "plan/add";

	fetch(urlAddPlan, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json',
			'Authorization': token
		},
		'body': JSON.stringify(plan)
	}).then(function () {
		fetch(url, {
			'method': 'POST',
			'headers': {
				'Content-Type': 'application/json',
				'Accept': 'application/json',
				'Authorization': token
			},
		})
	}, function () {
		// rechazo
	});
}

async function getReporteViajesPorRealizar() {
	let idUsuario = document.querySelector("#idUsuario").value;
	let url = "usuario/viajesPendientes/" + idUsuario;
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorViajeRealizar");
	contenedor.innerHTML = JSON.stringify(json);
}

async function getReporteViajesFinalizados() {
	let idUsuario = document.querySelector("#idUsuario").value;
	let url = "usuario/viajesFinalizados/" + idUsuario;
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorViajeFinalizado");
	contenedor.innerHTML = JSON.stringify(json);
}



async function planesSegunViaje() {
	let idViaje = document.querySelector("#idViajeTraerPlanes").value;

	let url = "viajePlan/planSegunViaje/" + idViaje;

	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorPlanSegunViaje");
	contenedor.innerHTML = JSON.stringify(json);
}


async function getReporteViajesRango() {
	let idUsuario = document.querySelector("#idUsuario").value;
	let fecha1 = document.querySelector("#idFecha1Rango").value;
	let fecha2 = document.querySelector("#idFecha2Rango").value;
	let url = "usuario/viajesRangoFecha/" + idUsuario + "/" + fecha1 + "/" + fecha2;
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorViajeRango");
	contenedor.innerHTML = JSON.stringify(json);
}
async function getViajesFiltroZona() {
	let idUsuario = document.querySelector("#idUsuario").value;
	let zona = document.querySelector("#idZona").value;
	let url = "usuario/viajesPorZona/" + idUsuario + "/" + zona;

	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorViajeFiltroZona");
	contenedor.innerHTML = JSON.stringify(json);
}
async function getUsuarioMasViajes() {
	let url = "viaje/usuarioMasViajes";
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorViajesUsuario");
	contenedor.innerHTML = JSON.stringify(json);
}

async function getViajesZona() {
	let url = "viaje/masViajesPorZona";
	let r = await fetch(url, {
		'method': 'GET',
		headers:{
		'mode': 'cors',
		'Authorization': token}
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorViajesZona");
	contenedor.innerHTML = JSON.stringify(json);
}
