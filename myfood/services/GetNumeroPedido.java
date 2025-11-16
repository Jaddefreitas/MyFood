package myfood.services;

import java.util.ArrayList;
import java.util.List;

import myfood.models.Pedido;

public class GetNumeroPedido {
    public static String run(String cliente, String empresa, String indice) {
        // Verifica se os parâmetros são válidos
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Cliente invalido");
        }
        if (empresa == null || empresa.isEmpty()) {
            throw new RuntimeException("Empresa invalida");
        }
        if (indice == null || indice.isEmpty()) {
            throw new RuntimeException("Indice invalido");
        }

        // Recupera os pedidos da empresa e do cliente
        List<Pedido> pedidosDaEmpresa = new Pedido().getBy("empresa", empresa);
        List<Pedido> pedidosDoCliente = new ArrayList<>();
        for (Pedido p : pedidosDaEmpresa) {
            if (p.getProperty("cliente").equals(cliente)) {
                pedidosDoCliente.add(p);
            }
        }

        // Verifica se o índice é válido
        if (pedidosDoCliente.isEmpty()) {
            throw new RuntimeException("Nenhum pedido encontrado para esse cliente e empresa");
        }

        int idx;
        try {
            idx = Integer.parseInt(indice);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Indice invalido");
        }

        if (idx < 0 || idx >= pedidosDoCliente.size()) {
            throw new RuntimeException("Indice fora do intervalo");
        }

        return pedidosDoCliente.get(idx).getProperty("numero");
    }
}
