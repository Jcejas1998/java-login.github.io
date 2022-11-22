// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on Ready
});

async function iniciarSesion(){

        let datos = {};
        datos.email = document.getElementById("txtEmailLogin").value;
        datos.password = document.getElementById("txtPasswordLogin").value;

      const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const respuesta = await request.text();
        if(respuesta != "fail"){
        localStorage.token = respuesta;
        localStorage.email = datos.email;
            window.location.href = 'usuarios.html'
        }else{
            alert("las credenciales son incorrectas")
        }
}