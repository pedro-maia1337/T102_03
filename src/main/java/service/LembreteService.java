package service;

import dao.LembreteDAO;
import model.Lembrete;
import spark.Request;
import spark.Response;
import java.util.List;

//Isso aqui lembra muito NODE JS ;)

public class LembreteService {

    private final LembreteDAO dao = new LembreteDAO();

    //Carrega HTML estático
    private String carregarHTML(String arquivo) throws Exception {
        try (java.io.InputStream inputStream = getClass().getClassLoader().getResourceAsStream(arquivo)) {
            if (inputStream == null) {
                throw new Exception("Arquivo não encontrado no resources: " + arquivo);
            }
            return new String(inputStream.readAllBytes(), "UTF-8");
        }
    }

    private String substituir(String template, String marcador, String valor) {
        return template.replace(marcador, valor);
    }


    //Listar os lembretes na tela inicial de lembretes
    public Object listar(Request req, Response res) {
        try {
            List<Lembrete> lista = dao.get();

            StringBuilder linhas = new StringBuilder();
            for (Lembrete l : lista) {
                linhas.append("<tr>")
                      .append("<td>").append(l.getCodigo()).append("</td>")
                      .append("<td>").append(l.getTitulo()).append("</td>")
                      .append("<td>").append(l.getDescricao()).append("</td>")
                      .append("<td>").append(l.getValor()).append("</td>")
                      .append("<td>").append(l.getDataLembrete()).append("</td>")
                      .append("<td>")
                      .append("<a href='/lembretes/editar/").append(l.getCodigo()).append("'>Editar</a> | ")
                      .append("<a href='/lembretes/excluir/").append(l.getCodigo()).append("'>Excluir</a>")
                      .append("</td>")
                      .append("</tr>");
            }

            String html = carregarHTML("lembrete.html");
            return substituir(html, "{{tabelaLembretes}}", linhas.toString());

        } catch (Exception e) {
            res.status(500);
            return "Erro ao listar lembretes: " + e.getMessage();
        }
    }

    //Form para novo lembrete
    public Object formNovo(Request req, Response res) {
        try {
            String html = carregarHTML("form.html");

            return html.replace("{{tituloPagina}}", "Novo Lembrete")
                       .replace("{{action}}", "/lembretes/criar")
                       .replace("{{codigo}}", "")
                       .replace("{{codigoReadonly}}", "")
                       .replace("{{titulo}}", "")
                       .replace("{{descricao}}", "")
                       .replace("{{valor}}", "")
                       .replace("{{dataLembrete}}", "");
        } catch (Exception e) {
            res.status(500);
            return "Erro ao abrir formulário: " + e.getMessage();
        }
    }

    //Criar novo lembrete
    public Object criar(Request req, Response res) {
        try {
            int codigo = Integer.parseInt(req.queryParams("codigo"));
            String titulo = req.queryParams("titulo");
            String descricao = req.queryParams("descricao");
            double valor = Double.parseDouble(req.queryParams("valor"));
            String data = req.queryParams("dataLembrete");

            Lembrete l = new Lembrete(codigo, titulo, descricao, valor, data);
            dao.insert(l);

            res.redirect("/lembretes");
            return null;
        } catch (Exception e) {
            res.status(500);
            return "Erro ao criar lembrete: " + e.getMessage();
        }
    }

    //Exibir form de edição
    public Object formEditar(Request req, Response res) {
        try {
            int codigo = Integer.parseInt(req.params("codigo"));
            Lembrete l = dao.get(codigo);

            if (l == null) {
                res.status(404);
                return "Lembrete não encontrado";
            }

            String html = carregarHTML("form.html");
            return html.replace("{{tituloPagina}}", "Editar Lembrete")
                       .replace("{{action}}", "/lembretes/atualizar/" + codigo)
                       .replace("{{codigo}}", String.valueOf(l.getCodigo()))
                       .replace("{{codigoReadonly}}", "readonly")
                       .replace("{{titulo}}", l.getTitulo())
                       .replace("{{descricao}}", l.getDescricao())
                       .replace("{{valor}}", String.valueOf(l.getValor()))
                       .replace("{{dataLembrete}}", l.getDataLembrete());
        } catch (Exception e) {
            res.status(500);
            return "Erro ao abrir formulário: " + e.getMessage();
        }
    }

    //Metódo de atualizar
    public Object atualizar(Request req, Response res) {
        try {
            int codigo = Integer.parseInt(req.params("codigo"));
            String titulo = req.queryParams("titulo");
            String descricao = req.queryParams("descricao");
            double valor = Double.parseDouble(req.queryParams("valor"));
            String data = req.queryParams("dataLembrete");

            Lembrete l = new Lembrete(codigo, titulo, descricao, valor, data);
            dao.update(l);

            res.redirect("/lembretes");
            return null;
        } catch (Exception e) {
            res.status(500);
            return "Erro ao atualizar lembrete: " + e.getMessage();
        }
    }

    //Metódo de excluir
    public Object excluir(Request req, Response res) {
        try {
            int codigo = Integer.parseInt(req.params("codigo"));
            dao.delete(codigo);

            res.redirect("/lembretes");
            return null;
        } catch (Exception e) {
            res.status(500);
            return "Erro ao excluir lembrete: " + e.getMessage();
        }
    }
}
