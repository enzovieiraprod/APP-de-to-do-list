import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa {
    //Atributos
    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private boolean concluida;

    //Construtor
    public Tarefa(String titulo, String descricao, LocalDate data, LocalTime hora) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
        this.concluida = false;
        }

    //Método
    public void MarcarConcluida(){
        this.concluida = true;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public boolean isConcluida() {
        return concluida;
    }
}
