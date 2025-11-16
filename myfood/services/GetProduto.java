package myfood.services;

import myfood.models.Empresa;
import myfood.models.Produto;

public class GetProduto {
    public static String run(String nome, String empresa, String atributo) {
        // Valida os parametros
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        if (empresa == null || empresa.isEmpty()) {
            throw new IllegalArgumentException("Empresa invalido");
        }
        if (atributo == null || atributo.isEmpty()) {
            throw new IllegalArgumentException("Atributo invalido");
        }

        // Busca o produto
        Produto p = null;
        for (Produto produto : new Produto().getBy("empresa", empresa)) {
            if (produto.getProperty("nome").equals(nome)) {
                p = produto;
                break;
            }
        }
        if (p == null) {
            throw new IllegalArgumentException("Produto nao encontrado");
        }

        // Retorna o atributo
        String valor = p.getProperty(atributo);
        if (valor == null) {
            throw new IllegalArgumentException("Atributo nao existe");
        }

        if (atributo.equals("empresa")) {
            Empresa e = new Empresa().find(valor);
            if (e == null) {
                throw new IllegalArgumentException("Empresa nao encontrada");
            }

            valor = e.getProperty("nome");
        }

        return valor;
    }
}
