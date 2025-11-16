package myfood.services;

import java.util.List;

import myfood.framework.Outputer;
import myfood.models.Empresa;
import myfood.models.Produto;

public class ListarProdutos {
    public static String run(String empresa) {
        if (empresa == null || empresa.isEmpty()) {
            throw new IllegalArgumentException("Empresa invalido");
        }

        // Verifica se a empresa existe
        Empresa e = new Empresa().find(empresa);
        if (e == null) {
            throw new IllegalArgumentException("Empresa nao encontrada");
        }

        // Pega todos os produtos da empresa
        List<Produto> produtos = new Produto().getBy("empresa", empresa);

        return new Outputer<Produto>().toString(produtos, List.of("nome"));
    }
}
