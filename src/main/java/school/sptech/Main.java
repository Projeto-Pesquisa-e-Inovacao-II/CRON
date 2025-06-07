package school.sptech;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> mensagensSemanais = List.of(
                "📌 17371 veículos evadiram no mês de maio na Ecovias Imigrantes",
                "🔍 Dados constantes, evasões persistentes. Reveja os pontos críticos da semana.",
                "📊 Reforce o monitoramento: as tendências semanais continuam estáveis ou mudaram?"
        );

        Telegram telegram = new Telegram(mensagensSemanais);
//        Telegram telegram = new Telegram();

        List<Long> usuariosNotificacoes = telegram.verificarNotificacoesAtivas();

        telegram.enviarMensagemAleatoria(usuariosNotificacoes);


    }
}
