package myfood.services;

import java.util.List;
import com.github.f4b6a3.uuid.UuidCreator;

import myfood.models.Empresa;
import myfood.models.Usuario;

public class CriarEmpresa {
    public static String run (String tipoEmpresa, String dono, String nome, String endereco, String tipoCozinha) {
        // Busca se já existe uma empresa com o mesmo nome e dono diferente
        List<Empresa> empresasExistentes = new Empresa().getBy("nome", nome);
        for (Empresa empresa : empresasExistentes) {
            if (!empresa.getProperty("dono").equals(dono)) {
                throw new RuntimeException("Empresa com esse nome ja existe");
            }
        }

        // Se for do mesmo dono a empresa de mesmo nome, verifica se o endereço é o mesmo
        for (Empresa empresa : empresasExistentes) {
            if (empresa.getProperty("dono").equals(dono) &&
                empresa.getProperty("endereco").equals(endereco)) {
                throw new RuntimeException("Proibido cadastrar duas empresas com o mesmo nome e local");
            }
        }

        // Verifica se o usuário é do tipo dono
        Usuario usuarioDono = new Usuario().find(dono);
        if (usuarioDono != null && !usuarioDono.isDono()) {
            throw new RuntimeException("Usuario nao pode criar uma empresa");
        }

        Empresa empresa = new Empresa();
        empresa.setProperty("id", UuidCreator.getTimeOrdered().toString());
        empresa.setProperty("tipoEmpresa", tipoEmpresa);
        empresa.setProperty("dono", dono);
        empresa.setProperty("nome", nome);
        empresa.setProperty("endereco", endereco);
        empresa.setProperty("tipoCozinha", tipoCozinha);
        empresa.save();
        return empresa.getProperty("id");
    }
}
