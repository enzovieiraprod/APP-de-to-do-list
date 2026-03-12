import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;
import java.time.LocalTime;

//Classe principal da interface
public class Interface extends Application {

    private Agenda agenda = new Agenda();

    //Quadro da tabela
    private TableView<Tarefa> tabela = new TableView<>();   // TableView<Tarefa> é o componente gráfico que cria a tabela na interface
    // <Tarefa> indica que cada linha da tabela será um objeto do tipo Tarefa
    // new TableView<>() cria uma tabela vazia

    //Fonte de dados da tabela
    private ObservableList<Tarefa> listaObservavel = FXCollections.observableArrayList();   //ObservableList<Tarefa> é uma lista que atualiza as mudanças automaticamente
    //FXCollections.observableArrayList() cria uma lista vazia
    @Override
    public void start(Stage stage){

        //--- Campos para adicionar tarefa ---------------
        TextField tituloField = new TextField();    //TextField cria um campo de texto onde o usuário pode digitar algo
        //tituloField é o nome da variável que representa esse campo, que iremos usar quando for para pegar o texto ou limpar, com o .getText() e .clear()
        tituloField.setPromptText("Título");        //setPromptText("Título") Define um texto cinza dentro do campo quando está vazio, para indicar que o usuário deve digitar

        TextField descricaoField = new TextField();
        descricaoField.setPromptText("Descrição");

        DatePicker datePicker = new DatePicker();   //DatePicker permite escolher datas com calendário popup
        TextField timeField = new TextField();      //JavaFX não tem um timepicker nativo, entao depois convertemos o texto em LocalTime
        timeField.setPromptText("HH:MM");

        //Cria botões e para definir o que acontece quando é clicado, utilizamos eventos de ação -> addButton.setOnAction
        Button addButton = new Button("Adicionar");
        Button concluirButton =  new Button("Concluir");
        Button removerButton = new Button("Remover");

        //--- Configurações da tabela ---

        //Cria uma coluna chamada "Título" que exibe o atribulo titulo de cada tarefa
        TableColumn<Tarefa, String> tituloCol = new TableColumn<>("Título");    //TableColumn<Tarefa, String> dados do tipo string e cada linha é um objeto do tipo Tarefa
        //new TableColumn<>("Título") cria a coluna e define o nome que aparece no cabeçalho
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));    //setCellValueFactory define onde a célula vai pegar o valor para exibir
        //new PropertyValueFactory<>("titulo") diz que cada célula desta cokuna deve pegar o valor do método getTitulo() da classe Tarefa

        TableColumn<Tarefa, String> descricaoCol = new TableColumn<>("Descrição");
        descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        TableColumn<Tarefa, LocalDate> dataCol = new TableColumn<>("Data");
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Tarefa, LocalTime> horaCol = new TableColumn<>("Hora");
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));

        TableColumn<Tarefa, Boolean> statusCol = new TableColumn<>("Concluída");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("concluida"));

        tabela.getColumns().addAll(tituloCol, descricaoCol, dataCol, horaCol, statusCol);   //tabela.getColumns() retorna a lista de colunas addAll() adiciona todas as colunas que eu criei
        tabela.setItems(listaObservavel);   //setItems() liga a tabela a uma lista de dados  listaObservavel contém todas as tarefas que quero mostrar
        //Resumindo: addAll define as colunas e setItems define os dados que aparecem nas linhas

        //--- Layout ---
        HBox inputBox = new HBox(10, tituloField, descricaoField, datePicker, timeField, addButton, concluirButton, removerButton);     //HBox: layout horizontal, 10: espaçamento horizontal Os parâmetros são os nodes
        inputBox.setPadding(new Insets(10));    //setPadding: margem interna de 10 pixels em todos os lados   Inputbox: linha de campos e botões horizontais

        VBox root = new VBox(10, tabela, inputBox);     //VBox: layout vertical
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 900, 400);    //Scene recebe o layout principal (root) e define o tamanho da janela
        stage.setScene(scene);      //Adiciona essa cena na janela principal
        stage.setTitle("Agenda JavaFX");
        stage.show();   //Exibe a janela
        //--- Eventos ---------
        addButton.setOnAction(e ->{
            String titulo = tituloField.getText();
            String descricao = descricaoField.getText();
            LocalDate data = datePicker.getValue();
            LocalTime hora;
            try {
                hora = LocalTime.parse(timeField.getText());
            } catch (DateTimeParseException ex) {
                showAlert("Hora inválida!");
                return;
            }

            if (titulo.isEmpty() || descricao.isEmpty() || data == null || timeField.getText().isEmpty()){
                showAlert("Preencha todos os campos!");
                return;
            }

            Tarefa nova = new Tarefa(titulo, descricao, data, hora);
            agenda.adicionarTarefa(nova);
            atualizarTabela();

            //Limpar campos
            tituloField.clear();
            descricaoField.clear();
            datePicker.setValue((null));
            timeField.clear();
        });

        concluirButton.setOnAction(e ->{
            Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                int indice = listaObservavel.indexOf(selecionada);
                agenda.marcarConcluida(indice);
                atualizarTabela();
            } else {
                showAlert("Selecione uma tarefa para concluir!");
            }
        });

        removerButton.setOnAction(e ->{
            Tarefa selecionada = tabela.getSelectionModel().getSelectedItem();      //selecionada entende qual tarefa o usuário clicou, mas nao sabe a posição da lista
            if (selecionada != null) {
                int indice = listaObservavel.indexOf(selecionada);      //Procura a posição do objeto Tarefa na lista
                agenda.removerTarefa(indice);                           //Remove a tarefa
                atualizarTabela();
            }
        });
    }
    private void atualizarTabela(){
        listaObservavel.setAll(agenda.getTarefas());
    }

    private void showAlert(String mensagem){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}

