package myfood.services;

import myfood.models.Usuario;

public class GetAtributoUsuario {
    public static String run(String id, String atributo) {
        Usuario usuario = new Usuario().find(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario nao cadastrado.");
        }
        return (String) usuario.getProperty(atributo);
    }
}