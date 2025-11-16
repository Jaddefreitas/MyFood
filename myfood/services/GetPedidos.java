package myfood.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import myfood.framework.Outputer;
import myfood.models.Empresa;
import myfood.models.Pedido;
import myfood.models.Produto;
import myfood.models.ProdutoPedido;
import myfood.models.Usuario;

public class GetPedidos {
    public static String run(String pedido, String atributo) {
        // Verifica se o atributo solicitado é válido
        if (atributo == null || atributo.isEmpty()) {
            throw new RuntimeException("Atributo invalido");
        }

        // Recupera o pedido pelo número
        Pedido p = new Pedido().findBy("numero", pedido);
        if (p == null) {
            throw new RuntimeException("Pedido nao encontrado");
        }

        if (atributo.equals("empresa")) {
            // Recupera o nome da empresa do pedido
            Empresa empresa = new Empresa().find(p.getProperty("empresa"));
            if (empresa == null) {
                throw new RuntimeException("Empresa do pedido nao encontrada");
            }
            return empresa.getProperty("nome");
        }

        if (atributo.equals("cliente")) {
            // Recupera o nome do cliente do pedido
            Usuario cliente = new Usuario().find(p.getProperty("cliente"));
            if (cliente == null) {
                throw new RuntimeException("Cliente do pedido nao encontrado");
            }
            return cliente.getProperty("nome");
        }

        if (atributo.equals("produtos")) {
            // Recupera os produtos do pedido
            List<ProdutoPedido> produtosPedidos = new ProdutoPedido().getBy("pedido", p.getProperty("id"));
            
            // Para cada produto pedido. cria uma Property com o nome do produto
            List<Properties> produtosList = new ArrayList<>();
            for (ProdutoPedido pp : produtosPedidos) {
                Produto produto = new Produto().find(pp.getProperty("produto"));
                if (produto == null) {
                    throw new RuntimeException("Produto do pedido nao encontrado");
                }
                Properties prodProps = new Properties();
                prodProps.setProperty("nome", produto.getProperty("nome"));
                produtosList.add(prodProps);
            }

            // Converte a lista de produtos para string
            return new Outputer<Properties>().toString(produtosList, List.of("nome"));
        }

        if (atributo.equals("valor")) {
            // Calcula o valor total do pedido
            List<ProdutoPedido> produtosPedidos = new ProdutoPedido().getBy("pedido", p.getProperty("id"));
            double valorTotal = 0.0;
            for (ProdutoPedido pp : produtosPedidos) {
                Produto produto = new Produto().find(pp.getProperty("produto"));
                if (produto == null) {
                    throw new RuntimeException("Produto do pedido nao encontrado");
                }
                String valorStr = produto.getProperty("valor");
                try {
                    double valor = Double.parseDouble(valorStr);
                    valorTotal += valor;
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Valor do produto invalido");
                }
            }
            return String.format("%.2f", valorTotal).replace(',', '.');
        }

        String valor = p.getProperty(atributo);
        if (valor == null) {
            throw new RuntimeException("Atributo nao existe");
        }

        return valor;
    }
}
