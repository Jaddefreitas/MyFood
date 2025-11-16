package myfood.services;

import myfood.models.Empresa;
import myfood.models.Usuario;

public class GetAtributoEmpresa {
    public static String run(String id, String atributo) {
        Empresa empresa = new Empresa().find(id);
        if (empresa == null) {
            throw new RuntimeException("Empresa nao cadastrada");
        }

        if (atributo == null || atributo.isEmpty()) {
            throw new RuntimeException("Atributo invalido");
        }

        if (!empresa.hasProperty(atributo)) {
            throw new RuntimeException("Atributo invalido");
        }

        String atributoValor = (String) empresa.getProperty(atributo);

        // Se atributo for "dono", buscar o nome do dono
        if (atributo.equals("dono")) {
            String donoId = atributoValor;
            String donoNome = new Usuario().find(donoId).getProperty("nome").toString();
            return donoNome;
        }

        return atributoValor;
    }
}
