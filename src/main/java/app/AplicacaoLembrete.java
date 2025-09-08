package app;

import static spark.Spark.*;

import service.LembreteService;

//http://localhost:4567/lembretes

public class AplicacaoLembrete {
    public static void main(String[] args) throws Exception {
         LembreteService service = new LembreteService();

        // Listar lembretes
        get("/lembretes", service::listar);

        // Exibir formulário para novo lembrete
        get("/lembretes/novo", service::formNovo);

        // Criar lembrete
        post("/lembretes/criar", service::criar);

        // Exibir formulário para editar lembrete
        get("/lembretes/editar/:codigo", service::formEditar);

        // Atualizar lembrete
        post("/lembretes/atualizar/:codigo", service::atualizar);

        // Excluir lembrete
        get("/lembretes/excluir/:codigo", service::excluir);

        System.out.println("Servidor rodando em: http://localhost:4567/lembretes");
    }

}

