package myfood.framework;

public class Data {
    public static void isValid(String abre, String fecha) {
        // Verifica se o horário de abertura e fechamento são válidos
        if (abre == null || fecha == null) {
            throw new RuntimeException("Horario invalido");
        }
        if (abre.isEmpty() || fecha.isEmpty()) {
            throw new RuntimeException("Formato de hora invalido");
        }

        // Verifica se os horários de abertura e fechamento são sintaticamente válidos
        if (abre.matches("^[0-9][0-9]:[0-9][0-9]$") == false ||
            fecha.matches("^[0-9][0-9]:[0-9][0-9]$") == false) {
            throw new RuntimeException("Formato de hora invalido");
        }

        // Verifica se os horários de abertura e fechamento são semanticamente válidos
        String[] partesAbre = abre.split(":");
        String[] partesFecha = fecha.split(":");
        int horaAbre = Integer.parseInt(partesAbre[0]);
        int minutoAbre = Integer.parseInt(partesAbre[1]);
        int horaFecha = Integer.parseInt(partesFecha[0]);
        int minutoFecha = Integer.parseInt(partesFecha[1]);
        if (horaAbre < 0 || horaAbre > 23 || minutoAbre < 0 || minutoAbre > 59 ||
            horaFecha < 0 || horaFecha > 23 || minutoFecha < 0 || minutoFecha > 59) {
            throw new RuntimeException("Horario invalido");
        }

        // Verifica se o horário de abertura é antes do horário de fechamento
        if (horaAbre > horaFecha || (horaAbre == horaFecha && minutoAbre >= minutoFecha)) {
            throw new RuntimeException("Horario invalido");
        }
    }
}
