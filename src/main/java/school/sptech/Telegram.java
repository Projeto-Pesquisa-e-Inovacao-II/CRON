package school.sptech;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.*;


public class Telegram {

    private List<String> mensagens = new ArrayList<>();
    private static final String TOKEN = "7753286594:AAHkbUrC_rgxK63_Qok9k0F2rCsXhdrUJxw";
    private static final String CHAT_ID = "7794980695";
    private static final String BASE_URL = "https://api.telegram.org/bot" + TOKEN + "/sendMessage";
    private static final HttpClient client = HttpClient.newHttpClient();
    private final Random random = new Random();

    public Telegram(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Telegram() {
    }

    public void enviarMensagemAleatoria(List<Long> usuariosNotificacoes) {
        for (Long usuario : usuariosNotificacoes) {

            String mensagem = mensagens.get(random.nextInt(mensagens.size()));

            System.out.println(usuario);
            System.out.println(mensagem);

            JSONObject payload = new JSONObject();
            payload.put("chat_id", usuario);
            payload.put("text", mensagem);

            try {
                HttpRequest request = HttpRequest.newBuilder(URI.create(BASE_URL))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    System.out.println("Mensagem enviada com sucesso!");
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Long> verificarNotificacoesAtivas() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dataway");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(basicDataSource);

        List<Long> verificacaoUsuarioNotificacaoAtiva = jdbcTemplate.query(
                "SELECT telegramChatId FROM Usuario " +
                        "JOIN Empresa ON Usuario.fkEmpresa = Empresa.idEmpresa " +
                        "Where Empresa.ativo = 1 AND usuario.notificacoesAtivas = 1;", (rs, rowNum) -> rs.getLong("telegramChatId"));

        return verificacaoUsuarioNotificacaoAtiva;
    }
}
