package myfood.models;

import myfood.framework.database.Model;

public class Pedido extends Model<Pedido> {
    @Override
    public String table() {
        return "pedidos";
    }
}
