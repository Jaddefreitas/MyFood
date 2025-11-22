package myfood.services;

import java.util.ArrayList;
import java.util.List;

import myfood.framework.Outputer;
import myfood.models.Empresa;
import myfood.models.Entregador;
import myfood.models.Usuario;

public class GetEmpresas {
    public static String run (String entregador) {
        // Valida os dados fornecidos
        validaEntregador(entregador);

        // Verifica se o entregador existe
        validaEntregadorExiste(entregador);

        // Verifica se é um entregador
        validaIsEntregador(entregador);

        // Retorna nome e endereco das empresas do entregador
        List<Empresa> empresas = new ArrayList<>();
        List<Entregador> ent = new Entregador().getBy("entregador", entregador);
        for (Entregador e : ent) {
            String empresaId = (String) e.getProperty("empresa");
            Empresa emp = new Empresa().find(empresaId);
            if (emp != null) {
                empresas.add(emp);
            }
        }

        // Retorna o nome e endereco das empresas
        return new Outputer<Empresa>().toString(empresas, List.of("nome", "endereco"));
    }

    public static void validaEntregador(String entregador) {
        if (entregador == null || entregador.isEmpty()) {
            throw new RuntimeException("Entregador invalido");
        }
    }

    public static void validaEntregadorExiste(String entregador) {
        Usuario user = new Usuario().find(entregador);
        if (user == null) {
            throw new RuntimeException("Entregador nao encontrado");
        }
    }

    public static void validaIsEntregador(String entregador) {
        Usuario user = new Usuario().find(entregador);
        if (user != null) {
            String veiculo = user.getProperty("veiculo");
            String placa = user.getProperty("placa");
            if (veiculo == null || placa == null) {
                throw new RuntimeException("Usuario nao e um entregador");
            }
        }
    }
}
