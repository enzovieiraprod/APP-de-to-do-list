import java.util.ArrayList;

public class Agenda {
    private ArrayList<Tarefa> tarefas;      //O array só guarda elementos do tipo Tarefa
                                            // tarefas = lista de objetos do tipo Tarefa
    //Construtor
    public Agenda() {
        tarefas = new ArrayList<>();
    }

    //Métodos
    public void adicionarTarefa(Tarefa tarefa){     //Tarefa = tipo tarefa = nome da variável
        tarefas.add(tarefa);
    }

    public void listarTarefas(){
        for (int i = 0; i < tarefas.size(); i++){
        Tarefa t = tarefas.get(i);      //Variável chamada t do tipo Tarefa     Pegue a tarefa na posicao i e guarde na variavel t
        System.out.println("Tarefa " + i + 1);
        System.out.println("Titulo " + t.getTitulo());  //Peça ao objeto t para retornar o seu título
        System.out.println("Descricao " + t.getDescricao());
        System.out.println("Data " + t.getData());
        System.out.println("Hora " + t.getHora());

        if (t.isConcluida()){
            System.out.println("Status: Concluida");
        } else{
            System.out.println("Status: Pendente");
        }
        System.out.println("-------------------------------------");
        }
    }
    public void marcarConcluida(int indice){
        if (indice >= 0 && indice < tarefas.size()){
            tarefas.get(indice).MarcarConcluida();  // pegue a tarefa na posição indice e execute o método marcarConcluida dela
        }
    }

    public void removerTarefa(int indice){
        if (indice >= 0 && indice < tarefas.size()){
            tarefas.remove(indice);
        }
    }
    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }
}
