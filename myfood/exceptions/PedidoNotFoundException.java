package myfood.exceptions;

public class PedidoNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Nao existe pedido em aberto";
    }
}
