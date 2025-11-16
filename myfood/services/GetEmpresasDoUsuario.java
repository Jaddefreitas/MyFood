package myfood.services;

import java.util.List;

import myfood.framework.Outputer;
import myfood.models.Empresa;
import myfood.models.Usuario;

public class GetEmpresasDoUsuario {
    public static String run (String idUsuario) {
        // Verifica se o usuário não é do tipo dono
        Usuario usuario = new Usuario().find(idUsuario);
        if (usuario != null && !usuario.isDono()) {
            throw new RuntimeException("Usuario nao pode criar uma empresa");
        }

        // Pega todas as empresas do usuário
        List<Empresa> empresasDoUsuario = new Empresa().getBy("dono", idUsuario);

        return new Outputer<Empresa>().toString(empresasDoUsuario, List.of("nome", "endereco"));
    }
}
