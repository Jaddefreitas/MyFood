package myfood.services;

import myfood.models.Usuario;

public class Login {
    public static String run(String email, String senha) {
        // Busca o usuario pelo email
        Usuario usuario = new Usuario().findBy("email", email);
        if (usuario == null) {
            throw new RuntimeException("Login ou senha invalidos");
        }
        if (!usuario.getProperty("senha").equals(senha)) {
            throw new RuntimeException("Login ou senha invalidos");
        }
        return usuario.getProperty("id");
    }
}
