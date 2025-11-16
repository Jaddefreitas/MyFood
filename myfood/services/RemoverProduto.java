package myfood.services;

import java.util.List;

import myfood.models.Pedido;
import myfood.models.Produto;
import myfood.models.ProdutoPedido;

public class RemoverProduto {
    public static void run(String pedido, String produto) {
        // Verifica se o produto é válido
        if (produto == null || produto.isEmpty()) {
            throw new RuntimeException("Produto invalido");
        }

        // Verifica se o pedido não está fechado
        Pedido ped = new Pedido().findBy("numero", pedido);
        if (ped == null) {
            throw new RuntimeException("Pedido nao encontrado");
        }

        if (ped.getProperty("estado").equals("preparando")) {
            throw new RuntimeException("Nao e possivel remover produtos de um pedido fechado");
        }

        // Verifica se o produto existe no pedido
        List<ProdutoPedido> produtosPedidos = new ProdutoPedido().getBy("pedido", ped.getProperty("id"));
        for (ProdutoPedido pp : produtosPedidos) {
            Produto prod = new Produto().find(pp.getProperty("produto"));
            if (prod == null) {
                throw new RuntimeException("Produto do pedido nao encontrado");
            }

            if (prod.getProperty("nome").equals(produto)) {
                // Remove o produto do pedido
                pp.delete();
                return;
            }
        }

        throw new RuntimeException("Produto nao encontrado");
    }
}
