package myfood.services;

import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;

import myfood.models.Produto;

public class CriarProduto {
    public static String run(String empresa, String nome, String valor, String categoria) {
        // Valida os parametros
        if (empresa == null || empresa.isEmpty()) {
            throw new IllegalArgumentException("Empresa invalido");
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

        // Verifica se o produto ja existe
        List<Produto> produtosDaEmpresa = new Produto().getBy("empresa", empresa);
        for (Produto p : produtosDaEmpresa) {
            if (p.getProperty("nome").equals(nome)) {
                throw new IllegalArgumentException("Ja existe um produto com esse nome para essa empresa");
            }
        }

        // Cria o produto
        Produto produto = new Produto();
        produto.setProperty("id", UuidCreator.getTimeOrdered().toString());
        produto.setProperty("empresa", empresa);
        produto.setProperty("nome", nome);
        produto.setProperty("valor", valor);
        produto.setProperty("categoria", categoria);
        produto.save();
        return produto.getProperty("id");
    }

}
