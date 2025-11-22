package myfood.services;

import myfood.framework.Horario;
import myfood.models.Empresa;

public class AlterarFuncionamento {
    public static void run (String mercado, String abre, String fecha) {
        // Verifica se o horário de abertura e fechamento são válidos
        Horario.isValid(abre, fecha);

        // Verifica se o mercado existe
        Empresa mercadoObj = new Empresa().find(mercado);
        if (mercadoObj == null) {
            throw new RuntimeException("Empresa nao encontrada");
        }

        // Verifica se o tipo da empresa é mercado
        String tipoEmpresa = GetAtributoEmpresa.run(mercado, "tipoEmpresa");
        if (!tipoEmpresa.equals("mercado")) {
            throw new RuntimeException("Nao e um mercado valido");
        }
        
        // Altera o funcionamento do mercado
        mercadoObj.setProperty("abre", abre);
        mercadoObj.setProperty("fecha", fecha);
        mercadoObj.save();
    }
    
}
