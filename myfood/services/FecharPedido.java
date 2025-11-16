package myfood.services;

import myfood.models.Pedido;

public class FecharPedido {
    public static void run(String numero) {
        // Recupera o pedido pelo número
        Pedido p = new Pedido().findBy("numero", numero);
        if (p == null) {
            throw new RuntimeException("Pedido nao encontrado");
        }

        // Verifica se o pedido já está fechado
        if (p.getProperty("estado").equals("preparando")) {
            throw new RuntimeException("Pedido ja esta preparando");
        }

        // Fecha o pedido
        p.setProperty("estado", "preparando");
        p.save();
    }
}
