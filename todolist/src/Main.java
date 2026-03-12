import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while(opcao != 0){
            System.out.println("----------MENU----------");
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefa");
            System.out.println("3 - Marcar tarefa como concluida");
            System.out.println("4 - Remover tarefa");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opcao: ");

            opcao = scanner.nextInt();

            switch (opcao){
                case 1:

                    scanner.nextLine(); // limpa buffer

                    System.out.print("Digite o título da tarefa: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Digite a descrição: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Digite a data (AAAA-MM-DD): ");
                    String dataStr = scanner.nextLine();
                    LocalDate data = LocalDate.parse(dataStr);

                    System.out.print("Digite a hora (HH:MM): ");
                    String horaStr = scanner.nextLine();
                    LocalTime hora = LocalTime.parse(horaStr);

                    Tarefa novaTarefa = new Tarefa(titulo, descricao, data, hora);

                    agenda.adicionarTarefa(novaTarefa);

                    System.out.println("Tarefa adicionada com sucesso!");

                    break;

                case 2:
                    agenda.listarTarefas();
                    break;

                case 3:
                    //Lista as tarefas
                    agenda.listarTarefas();

                    //Pergunta qual tarefa marcar
                    System.out.println("Digite o número da tarefa que deseja marcar como concluída: ");
                    int numero = scanner.nextInt();
                    int indice = numero - 1;

                    // validação do índice
                    if (indice >= 0 && indice < agenda.getTarefas().size()) {
                        agenda.marcarConcluida(indice);
                        System.out.println("Tarefa marcada como concluída!");
                    } else {
                        System.out.println("Número inválido! Nenhuma tarefa foi marcada.");
                    }

                    break;
                case 4:
                    agenda.listarTarefas();

                    System.out.println("Digite o número da tarefa que deseja remover: ");
                    int numeroRemover = scanner.nextInt();
                    int indiceRemover = numeroRemover - 1;

                    if (indiceRemover >= 0 && indiceRemover < agenda.getTarefas().size()){
                        agenda.removerTarefa(indiceRemover);
                        System.out.println("Tarefa removida com sucesso!");
                    } else {
                        System.out.println("Número inválido! Nenhuma tarefa foi removida.");
                    }

                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
