package app;

import java.util.*;

import dao.LembreteDAO;
import model.Lembrete;

public class AplicacaoLembrete {
    
    public static void main(String[] args) throws Exception {
        LembreteDAO lembreteDAO = new LembreteDAO();
        int op = 0;
        Scanner sc = new Scanner(System.in);

      
        do{
            System.out.print("Escolha uma opção: ");
            op= sc.nextInt();
            sc.nextLine(); 

            System.out.println("1 - Listar");
            System.out.println("2 - Inserir: ");
            System.out.println("3 - Atualizar: ");
            System.out.println("4 - Excluir: ");
            System.out.println("5 - Sair: ");

            switch (op) {
                case 1:
                    System.out.println("Lembretes");
                    List<Lembrete> lembretes = lembreteDAO.get();
                    if (lembretes.isEmpty()) {
                        System.out.println("Nenhum lembrete encontrado.");
                    } else {
                        for (Lembrete l : lembretes) {
                            System.out.println(l);
                        }
                    }
                    break;

                case 2:
                    System.out.println("\nInserir Lembrete");
                    System.out.print("Digite o código que deseja inserir: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o titulo que deseja inserir: ");
                    String titulo = sc.nextLine();
                    System.out.print("Digite a descricão  que deseja inserir: ");
                    String descricao = sc.nextLine();
                    System.out.print("Digite o valor que deseja inserir: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Digite a data que deseja inserir: ");
                    String dataLembrete = sc.nextLine();

                    Lembrete novoLembrete = new Lembrete(codigo, titulo, descricao, valor, dataLembrete);
                    if (lembreteDAO.insert(novoLembrete)) {
                        System.out.println("Lembrete inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir lembrete.");
                    }

                    break;

                case 3:
                    System.out.print("Digite o código do lembrete a ser atualizado: ");
                    int codigoAtualizar = sc.nextInt();
                    sc.nextLine();
                    
                    Lembrete lembreteExistente = lembreteDAO.get(codigoAtualizar);
                    if (lembreteExistente != null) {
                        System.out.print("Novo Título (" + lembreteExistente.getTitulo() + "): ");
                        String novoTitulo = sc.nextLine();
                        System.out.print("Nova Descrição (" + lembreteExistente.getDescricao() + "): ");
                        String novaDescricao = sc.nextLine();
                        System.out.print("Novo Valor (" + lembreteExistente.getValor() + "): ");
                        double novoValor = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Nova Data (" + lembreteExistente.getDataLembrete() + "): ");
                        String novaData = sc.nextLine();
                        
                        lembreteExistente.setTitulo(novoTitulo);
                        lembreteExistente.setDescricao(novaDescricao);
                        lembreteExistente.setValor(novoValor);
                        lembreteExistente.setDataLembrete(novaData);
                        
                        if (lembreteDAO.update(lembreteExistente)) {
                            System.out.println("Lembrete atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar lembrete.");
                        }
                    } else {
                        System.out.println("Lembrete não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o código do lembrete a ser excluído: ");
                    int codigoExcluir = sc.nextInt();
                    sc.nextLine();

                    if (lembreteDAO.delete(codigoExcluir)) {
                        System.out.println("Lembrete excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir lembrete.");
                    }
                    break;
            
                case 5: 
                    System.out.println("Saindo do programa....");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while(op != 5);

        sc.close();	

	}
}
