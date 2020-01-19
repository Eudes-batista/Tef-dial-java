package tef.config;

public class ArquivoConfiguracao {

    private static Pastas pastas;

    public static Pastas getPastas() {
        if (pastas == null) {
            pastas = new Pastas();
        }
        return pastas;
    }
}
