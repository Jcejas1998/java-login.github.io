package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    //en value va la ruta que se va a usar
    //localhost://8080/<value>
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono(123456);
        return usuario;
    };

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader (value = "Authorization") String token){
        if(!validarToken(token)){return null;}

       String usuarioId = jwtUtil.getKey(token);
       if(usuarioId == null){
           return new ArrayList<>();
       }

       return usuarioDao.getUsuario();
    };

    private boolean validarToken(String token){
        String idUsuario =  jwtUtil.getKey(token);
        return idUsuario != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST
    )
    //en value va la ruta que se va a usar
    //localhost://8080/<value>
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    };

    @RequestMapping(value = "usuario1")
    public Usuario editar(){

        Usuario usuario = new Usuario();
        usuario.setNombre("lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@gmail.com");
        usuario.setTelefono(123456);
        return usuario;
    };
    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id,@RequestHeader (value = "Authorization") String token){
        if(!validarToken(token)){return;}

        usuarioDao.eliminar(id);
    };

}
