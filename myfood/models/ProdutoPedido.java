package myfood.models;

import myfood.framework.database.Model;

public class ProdutoPedido extends Model<ProdutoPedido> {
    @Override
    public String table() {
        return "produtos_pedidos";
    }
}
