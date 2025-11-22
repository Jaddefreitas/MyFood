package myfood.services;

import com.github.f4b6a3.uuid.UuidCreator;

import myfood.models.Empresa;
import myfood.models.Entregador;
import myfood.models.Usuario;

public class CadastrarEntregador {
    public static void run (String empresa, String entregador) {
        // Valida os dados fornecidos
        validaEmpresa(empresa);
        validaEntregador(entregador);

        // Verifica se a empresa existe
        validaEmpresaExiste(empresa);

        // Verifica se o entregador é válido
        validaEntregadorExiste(entregador);
        validaIsEntregador(entregador);

        // Salva o entregador na empresa
        salvarEntregadorNaEmpresa(empresa, entregador);
    }

    public static void validaEmpresa(String empresa) {
        if (empresa == null || empresa.isEmpty()) {
            throw new RuntimeException("Empresa invalida");
        }
    }

    public static void validaEntregador(String entregador) {
        if (entregador == null || entregador.isEmpty()) {
            throw new RuntimeException("Entregador invalido");
        }
    }

    public static void validaEmpresaExiste(String empresa) {
        Empresa emp = new Empresa().find(empresa);
        if (emp == null) {
            throw new RuntimeException("Empresa nao encontrada");
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

    public static void salvarEntregadorNaEmpresa(String empresa, String entregador) {
        Entregador ent = new Entregador();
        ent.setProperty("id", UuidCreator.getTimeOrdered().toString());
        ent.setProperty("empresa", empresa);
        ent.setProperty("entregador", entregador);
        ent.save();
    }
}
