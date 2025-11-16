package myfood.services;

import java.util.List;

import com.github.f4b6a3.uuid.UuidCreator;

import myfood.models.Empresa;
import myfood.models.Pedido;

public class CriarPedido {
    public static String run(String cliente, String empresa) {
        // O cliente não pode ser dono da empresa
        List<Empresa> empresasDoCliente = new Empresa().getBy("dono", cliente);
        for (Empresa emp : empresasDoCliente) {
            if (emp.getProperty("id").equals(empresa)) {
                throw new RuntimeException("Dono de empresa nao pode fazer um pedido");
            }
        }

        // Não pode haver um pedido "em aberto" para a mesma empresa no mesmo cliente
        List<Pedido> pedidosExistentes = new Pedido().getBy("empresa", empresa);
        for (Pedido p : pedidosExistentes) {
            if (p.getProperty("estado").equals("aberto") && p.getProperty("cliente").equals(cliente)) {
                throw new RuntimeException("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
            }
        }

        // Pega o ultimo número de pedido no sistema
        List<Pedido> todosPedidos = new Pedido().all();
        int ultimoNumero = 0;
        for (Pedido p : todosPedidos) {
            int numeroAtual = Integer.parseInt(p.getProperty("numero"));
            if (numeroAtual > ultimoNumero) {
                ultimoNumero = numeroAtual;
            }
        }

        Pedido pedido = new Pedido();
        pedido.setProperty("id", UuidCreator.getTimeOrdered().toString());
        pedido.setProperty("numero", String.valueOf(ultimoNumero + 1));
        pedido.setProperty("estado", "aberto");
        pedido.setProperty("cliente", cliente);
        pedido.setProperty("empresa", empresa);
        pedido.save();
        return pedido.getProperty("numero");
    }
}
