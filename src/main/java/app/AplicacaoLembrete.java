package app;

import static spark.Spark.*;

import service.LembreteService;

//http://localhost:4567/lembretes

public class AplicacaoLembrete {
    public static void main(String[] args) throws Exception {
         LembreteService service = new LembreteService();

        staticFiles.location("/public");

        // Exibir lembretes
        get("/lembretes", service::listar);

        // Form para criar
        get("/lembretes/novo", service::formNovo);

        // Criar novo lembrete
        post("/lembretes/criar", service::criar);

        // Form para editar
        get("/lembretes/editar/:codigo", service::formEditar);

        // Atualizar lembrete
        post("/lembretes/atualizar/:codigo", service::atualizar);

        // Excluir lembrete
        get("/lembretes/excluir/:codigo", service::excluir);

        System.out.println("http://localhost:4567/lembretes");
    }

}

