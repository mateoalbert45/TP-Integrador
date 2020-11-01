"use strict";
document.querySelector("#submitPostViaje").addEventListener("click", postViaje);
document.querySelector("#submitApvVuelo").addEventListener("click", asignarPlanVuelo);
document.querySelector("#submitGetVuelos").addEventListener("click", getVuelos);
document.querySelector("#submitLogin").addEventListener("click", getIdUsuario);
document.querySelector("#submitAsignarPlan").addEventListener("click", asignarPlan);
document.querySelector("#submitRegister").addEventListener("click", postUsuario);


function postViaje(){ //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base

	let id = document.querySelector("#idAddViaje").value;
	let nombre = document.querySelector("#idAddNombre").value;
	let fechaIni = document.querySelector("#idAddFechaInicio").value;
	let fechaFin = document.querySelector("#idAddFechaFin").value;
	let descripcion = document.querySelector("#idAddDescripcion").value;

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
	        'Accept': 'application/json'
	     },
	     'body': JSON.stringify(viaje)
	 })
}


//carga datos desde archivo .txt
 let openFile = function(event)
     {
         let input = event.target;
         let reader = new FileReader();
         reader.onload = function()
         {
             let texto = reader.result;
             let texto_array = texto.split("\r\n");
             let id;
             let nombre;
             let fechaIni;
             let fechaFin;
             let descripcion;
             let i = 0;
             for (let element in texto_array)
             {
                 let json_element;
                 if(i%5 == 0)
                     id = texto_array[element];
                 else if(i%5 == 1)
                     nombre = texto_array[element];
                 else if(i%5 == 2)
                     fechaIni = texto_array[element];
                 else if(i%5 == 3)
                     fechaFin = texto_array[element];
                 else if(i%5 == 4)
                 {
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
                           'Accept': 'application/json'
                        },
                        'body': JSON.stringify(viaje)
                    })
                 }
                 i++;
             }
         };
         reader.readAsText(input.files[0]);
     }


function postUsuario(){ //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base

	let id = document.querySelector("#idUsuario").value;
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

function asignarPlanVuelo(){ //Método para postear un viaje, agarramos los values de los inputs de la pagina, creamos un objeto con esos datos y lo subimos a la base
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
		 "vuelo" : vuelo
	 }
	 console.log(plan);

	 	 fetch(urlAddPlanVuelo, {
	 	     'method': 'POST',
	 	      'headers': {
	 	        'Content-Type': 'application/json',
	 	        'Accept': 'application/json'
	 	     },
	 	     'body': JSON.stringify(vuelo)
	 	 }).then(function() {
	 		 fetch(urlAsignarPlanVuelo, {
	 		     'method': 'POST',
	 		      'headers': {
	 		        'Content-Type': 'application/json',
	 		        'Accept': 'application/json'
	 		     },
	 		 })	   }, function() {
	 	   // rechazo
	 	 });



	 // fetch(urlAddPlan, {
		// 	 'method': 'POST',
		// 		'headers': {
		// 			'Content-Type': 'application/json',
		// 			'Accept': 'application/json'
		// 	 },
		// 	 'body': JSON.stringify(plan)
	 // }).then(function() {
		//  fetch(url, {
		// 		 'method': 'POST',
		// 			'headers': {
		// 				'Content-Type': 'application/json',
		// 				'Accept': 'application/json'
		// 		 },
		// 		 'body': JSON.stringify(plan)
		//  })	   }, function() {
		//  // rechazo
	 // });
 }




async function getVuelos(){
  let  url = "vuelo/getAll";
let r = await fetch(url, {
     'method': 'GET',
      'mode':'cors'
 });
let json = await r.json();
console.log(json);
let contenedor = document.querySelector("#contenedorVuelo");
contenedor.innerHTML = JSON.stringify(json);
}



async function getIdUsuario(){
	let mail = document.querySelector("#idMail").value;
	let contraseña = document.querySelector("#idContraseña").value;
  let url = "usuario/getId/";
let r = await fetch(url + mail + "/" + contraseña , {
     'method': 'GET',
      'mode':'cors'
 });
let json = await r.json();
console.log(json);
let contenedor = document.querySelector("#idUsuario");
contenedor.innerHTML = JSON.stringify(json);
}


function asignarPlan(){
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
	        'Accept': 'application/json'
	     },
	     'body': JSON.stringify(plan)
	 }).then(function() {
		 fetch(url, {
		     'method': 'POST',
		      'headers': {
		        'Content-Type': 'application/json',
		        'Accept': 'application/json'
		     },
		 })	   }, function() {
	   // rechazo
	 });


}
