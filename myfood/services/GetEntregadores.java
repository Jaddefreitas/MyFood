package myfood.services;

import java.util.ArrayList;
import java.util.List;

import myfood.framework.Outputer;
import myfood.models.Empresa;
import myfood.models.Entregador;
import myfood.models.Usuario;

public class GetEntregadores {
    public static String run(String empresa) {
        // Valida os dados fornecidos
        validaEmpresa(empresa);

        // Verifica se a empresa existe
        validaEmpresaExiste(empresa);

        // Retorna a lista de emails dos entregadores da empresa
        List<Usuario> entregadoresUsuarios = new ArrayList<>();
        List<Entregador> entregadores = new Entregador().getBy("empresa", empresa);
        for (Entregador ent : entregadores) {
            String entregadorId = (String) ent.getProperty("entregador");
            Usuario user = new Usuario().find(entregadorId);
            if (user != null) {
                entregadoresUsuarios.add(user);
            }
        }

        return new Outputer<Usuario>().toString(entregadoresUsuarios, List.of("email"));
    }

    public static void validaEmpresa(String empresa) {
        if (empresa == null || empresa.isEmpty()) {
            throw new RuntimeException("Empresa invalida");
        }
    }

    public static void validaEmpresaExiste(String empresa) {
        Empresa emp = new Empresa().find(empresa);
        if (emp == null) {
            throw new RuntimeException("Empresa nao encontrada");
        }
    }
}
