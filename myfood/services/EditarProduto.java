package myfood.services;

import myfood.models.Produto;

public class EditarProduto {
    public static void run(String produto, String nome, String valor, String categoria) {
        // Valida os parametros
        if (produto == null || produto.isEmpty()) {
            throw new IllegalArgumentException("Produto invalido");
        }
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }
        try {
            Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor invalido");
        }
        if (valor == null || valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            throw new IllegalArgumentException("Valor invalido");
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Categoria invalido");
        }

        // Edita o produto
        Produto p = new Produto().find(produto);
        if (p == null) {
            throw new IllegalArgumentException("Produto nao cadastrado");
        }
        p.setProperty("nome", nome);
        p.setProperty("valor", valor);
        p.setProperty("categoria", categoria);
        p.save();
    }
}
