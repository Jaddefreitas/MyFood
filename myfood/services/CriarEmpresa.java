package myfood.services;

import java.util.List;
import com.github.f4b6a3.uuid.UuidCreator;

import myfood.framework.Data;
import myfood.models.Empresa;
import myfood.models.Usuario;

public class CriarEmpresa {
    public static String run (String tipoEmpresa, String dono, String nome, String endereco, String tipoCozinha) {
        validaTipoEmpresa(tipoEmpresa);
        validaNome(nome);
        validaEndereco(endereco);
        validaEmpresaExistente(nome, dono, endereco);
        validaIsDono(dono);

        return salvarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha, null, null, null);
    }

    public static String run (String tipoEmpresa, String dono, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        validaTipoEmpresa(tipoEmpresa);
        validaNome(nome);
        validaEndereco(endereco);
        validaTipoMercado(tipoMercado);
        Data.isValid(abre, fecha);
        validaEmpresaExistente(nome, dono, endereco);
        validaIsDono(dono);

        return salvarEmpresa(tipoEmpresa, dono, nome, endereco, null, abre, fecha, tipoMercado);
    }

    private static void validaTipoEmpresa(String tipoEmpresa) {
        // Verifica se o tipo de empresa é válido
        if (tipoEmpresa == null || tipoEmpresa.isEmpty()) {
            throw new RuntimeException("Tipo de empresa invalido");
        }
    }

    private static void validaNome(String nome) {
        // Verifica se o nome da empresa é válido
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("Nome invalido");
        }
    }

    private static void validaEndereco(String endereco) {
        // Verifica se o endereço da empresa é válido
        if (endereco == null || endereco.isEmpty()) {
            throw new RuntimeException("Endereco da empresa invalido");
        }
    }

    private static void validaTipoMercado(String tipoMercado) {
        // Verifica se o tipo de mercado é válido
        if (tipoMercado == null || tipoMercado.isEmpty()) {
            throw new RuntimeException("Tipo de mercado invalido");
        }
    }

    private static void validaEmpresaExistente(String nome, String dono, String endereco) {
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
    }

    private static void validaIsDono(String dono) {
        // Verifica se o usuário é do tipo dono
        Usuario usuarioDono = new Usuario().find(dono);
        if (usuarioDono != null && !usuarioDono.isDono()) {
            throw new RuntimeException("Usuario nao pode criar uma empresa");
        }
    }

    private static String salvarEmpresa(String tipoEmpresa, String dono, String nome, String endereco, String tipoCozinha, String abre, String fecha, String tipoMercado) {
        Empresa empresa = new Empresa();
        empresa.setProperty("id", UuidCreator.getTimeOrdered().toString());
        empresa.setProperty("tipoEmpresa", tipoEmpresa);
        empresa.setProperty("dono", dono);
        empresa.setProperty("nome", nome);
        empresa.setProperty("endereco", endereco);
        if (tipoCozinha != null) {
            empresa.setProperty("tipoCozinha", tipoCozinha);
        }
        if (abre != null) {
            empresa.setProperty("abre", abre);
        }
        if (fecha != null) {
            empresa.setProperty("fecha", fecha);
        }
        if (tipoMercado != null) {
            empresa.setProperty("tipoMercado", tipoMercado);
        }
        empresa.save();
        return empresa.getProperty("id");
    }
}
