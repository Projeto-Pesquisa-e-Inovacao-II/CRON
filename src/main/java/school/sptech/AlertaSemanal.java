package school.sptech;

import java.util.List;

public class AlertaSemanal {
    public static void main(String[] args) {
        List<String> mensagensSemanais = List.of(
                "📌 Lembrete semanal: continue acompanhando as praças com maiores índices de evasão.",
                "🔍 Dados constantes, evasões persistentes. Reveja os pontos críticos da semana.",
                "📊 Reforce o monitoramento: as tendências semanais continuam estáveis ou mudaram?"
        );

        Telegram telegram = new Telegram(mensagensSemanais);
        telegram.enviarMensagemAleatoria();
    }
}
