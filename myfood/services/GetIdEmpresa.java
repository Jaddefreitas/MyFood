package myfood.services;

import java.util.List;

import myfood.models.Empresa;

public class GetIdEmpresa {
    public static String run(String idDono, String nomeEmpresa, String indice) {
        if (nomeEmpresa == null || nomeEmpresa.isEmpty()) {
            throw new RuntimeException("Nome invalido");
        }

        List<Empresa> empresas = new Empresa().getBy("dono", idDono);
        List<Empresa> empresasFiltradas = empresas.stream()
                .filter(e -> e.getProperty("nome").toString().equals(nomeEmpresa))
                .toList();

        if (empresasFiltradas.size() == 0) {
            throw new RuntimeException("Nao existe empresa com esse nome");
        }

        int idx = Integer.parseInt(indice);

        if (idx < 0) {
            throw new RuntimeException("Indice invalido");
        }

        if (idx > empresasFiltradas.size() - 1) {
            throw new RuntimeException("Indice maior que o esperado");
        }

        return empresasFiltradas.get(idx).getProperty("id").toString();
    }
}
