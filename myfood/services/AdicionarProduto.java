package myfood.services;

import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;

import myfood.exceptions.PedidoNotFoundException;
import myfood.models.Empresa;
import myfood.models.Pedido;
import myfood.models.Produto;
import myfood.models.ProdutoPedido;

public class AdicionarProduto {
    public static void run(String numero, String produto) {
        // Verifica se o pedido existe
        Pedido pedido = new Pedido().findBy("numero", numero);
        if (pedido == null) {
            throw new PedidoNotFoundException();
        }

        // Verifica se o pedido está em estado "preparando"
        if (pedido.getProperty("estado").equals("preparando")) {
            throw new RuntimeException("Nao e possivel adcionar produtos a um pedido fechado");
        }

        // Verifica se o produto pertence à empresa do pedido
        String empresaDoPedido = pedido.getProperty("empresa");
        Empresa empresa = new Empresa().findBy("id", empresaDoPedido);
        if (empresa == null) {
            throw new RuntimeException("Empresa do pedido nao encontrada");
        }

        List<Produto> produtosDaEmpresa = new Produto().getBy("empresa", empresa.getProperty("id"));
        boolean produtoValido = false;
        for (Produto p : produtosDaEmpresa) {
            if (p.getProperty("id").equals(produto)) {
                produtoValido = true;
                break;
            }
        }
        if (!produtoValido) {
            throw new RuntimeException("O produto nao pertence a essa empresa");
        }

        // Adiciona o produto ao pedido
        ProdutoPedido produtoPedido = new ProdutoPedido();
        produtoPedido.setProperty("id", UuidCreator.getTimeOrdered().toString());
        produtoPedido.setProperty("pedido", pedido.getProperty("id"));
        produtoPedido.setProperty("produto", produto);
        produtoPedido.save();
    }
}
