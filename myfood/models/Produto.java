package myfood.models;

import myfood.framework.database.Model;

public class Produto extends Model<Produto> {
    @Override
    public String table() {
        return "produtos";
    }
    
}
