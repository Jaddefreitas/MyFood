package myfood.models;

import myfood.framework.database.Model;

public class Entregador extends Model<Entregador> {
    @Override
    public String table() {
        return "entregadores";
    }
}
