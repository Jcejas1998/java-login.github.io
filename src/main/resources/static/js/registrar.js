// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on Ready
});

async function registrarUsuarios(){

        let datos = {};
        datos.nombre = document.getElementById("txtNombre").value;
        datos.apellido = document.getElementById("txtApellido").value;
        datos.email = document.getElementById("txtEmail").value;
        datos.password = document.getElementById("txtPassword").value;

        let repetirPassword = document.getElementById("txtRepeatPassword").value;

        if(repetirPassword != datos.password){
            alert("la contraseña no coincide");
            return
        }

      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      alert("la cuenta se creo con existo");
                    window.location.href = 'login.html#';


}
