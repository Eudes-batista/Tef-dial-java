package br.com.saiphpdv.tef.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pastas {
    
    private static final String CAMINHO_TEF = "tef";
    private static final String CAMINHO_REQUISICAO = "C:\\TEF_DIAL\\req";
    private static final String CAMINHO_RESPOSTA = "C:\\TEF_DIAL\\resp";

    public void criarPastas() throws IOException {
        Path pathTef = Paths.get(CAMINHO_TEF);
        Files.createDirectories(pathTef);
    }    
    public String getCaminhoTefTemp() {
        return CAMINHO_TEF;
    }

    public String getCaminhoRequisicao() {
        return CAMINHO_REQUISICAO;
    }

    public String getCaminhoResposta() {
        return CAMINHO_RESPOSTA;
    }
}
