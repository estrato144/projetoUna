import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class AppEventos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dataAtual = LocalDateTime.now();
        Map<String, String> usuarios = carregarUsuarios();
        System.out.println("Seja bem vindo ao EventosAPP");
        System.out.println("1.Já possuo login.");
        System.out.println("2.Criar login.");
        int opcao = scanner.nextInt();
        
        switch (opcao) {
            case 1: 
                System.out.println("Digite seu login:");
                String login = scanner.next();
                System.out.println("Digite sua senha:");
                String senha = scanner.next();
                if (validarLogin(login, senha, usuarios)) {
                    System.out.println("Login efetuado com sucesso!");
                    
                }
                else {
                    System.out.println("Login ou senha incorretos.");
                    scanner.close();
                    return;
                }

                break;
            case 2: 
                scanner.nextLine();
                System.out.println("Crie um novo login:");
                String newLogin = scanner.nextLine();
                System.out.println("Crie uma senha:");
                String newSenha = scanner.nextLine();
                System.out.println("Insira sua cidade:");
                String cidade = scanner.nextLine();
                System.out.println("Insira seu email:");
                String email = scanner.nextLine();
                //adiciona o novo usuario no arquivo usuarios.txt
                usuarios.put(newLogin, newSenha);
                try {
                    FileWriter writer = new FileWriter("usuarios.txt", true);
                    writer.write(newLogin + "," + newSenha + "," + cidade + "," + email + "\n");
                    writer.close();
                    System.out.println("Usuario cadastrado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao escrever no arquivo de usuarios." + e.getMessage());
               
                    
                }
                break;
        
            default:
                System.out.println("Opção invalida.");
                scanner.close();
                return;
                
        }
        
        //Seção de menu dos eventos
        while (true) {
            exibirMenu();
            int opcaoMenu = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada
       
        switch (opcaoMenu) {
            case 1:
                System.out.println("Todos os eventos:");
                 List<Evento> todosEventos = lerEventosDoArquivo("eventos.txt");
                 Collections.sort(todosEventos, Comparator.comparing(Evento::getData));
                 for (Evento evento : todosEventos) {
                    System.out.println(evento);

                }
                
                break;
            case 2:
                cadastrarNovoEvento(scanner);
                break;
            case 3:
                System.out.println("Eventos passados:");
                List<Evento> eventosPassados = lerEventosDoArquivo("eventos.txt");
                Collections.sort(eventosPassados, Comparator.comparing(Evento::getData));
                if (eventosPassados.isEmpty()) {
                    System.out.println("Não há eventos passados.");
                }  else { 
                    for (Evento evento : eventosPassados) {
                     if (evento.getData().isBefore(dataAtual)) {
                         System.out.println(evento);
                     }
                    }
                   }
                break;
            case 4:
                System.out.println("Lista de eventos disponiveis:");
                //colocar eventos que estão pra acontecer ordenado por proximidade de local e hora.
                List<Evento> eventosDisponiveis = lerEventosDoArquivo("eventos.txt");
                Collections.sort(eventosDisponiveis, Comparator.comparing(Evento::getData));
                if (eventosDisponiveis.isEmpty()){
                    System.out.println("Não há eventos disponiveis.");
                } else {
                for (Evento evento : eventosDisponiveis) {
                    if (evento.getData().isAfter(dataAtual)) 
                    System.out.println(evento);                         
                    } 
                  }
                        
                System.out.println("Escolha o evento que gostaria de ir.");
                String eventoSelecionado = scanner.nextLine();
                //verificar se evento existe
                boolean eventoEncontrado = false;
                for (Evento evento : eventosDisponiveis){
                    if (evento.getData().isAfter(dataAtual) && evento.getNome().equalsIgnoreCase(eventoSelecionado)) {
                        eventoEncontrado = true;
                        System.out.println("Confirmar presença no evento " + eventoSelecionado +  "?  [s/n]");
                        String confirmacao = scanner.nextLine();
                        if (confirmacao.equalsIgnoreCase("s")) {
                            System.out.println("Presença confirmada em " + eventoSelecionado);            
                    }
                    break;
                }
                }
                if (!eventoEncontrado) {
                    System.out.println("Evento não encontrado ou não disponível.");
                    break;
                }
                
            case 5:
                System.out.println("Eventos que marquei presença:");
                //System.out.println(eventoSelecionado);
                //colocar eventos de acordo com o usuario
                
                break;
            case 6:
                //encerrar programa
                System.out.println("Saindo do programa...");
                scanner.close();
                return;
        
            default:
            System.out.println("Opção invalida.");
                break;
        }
        salvarUsuarios(usuarios);
    }

        
    } 
    //carrega os usuarios salvos no arquivo "usuarios.txt"
    private static Map<String, String> carregarUsuarios() {
        Map<String, String> usuarios = new HashMap<>();
        try {
            File arquivoUsuarios = new File("usuarios.txt");
            if (arquivoUsuarios.exists()) { 
                Scanner leitor = new Scanner(arquivoUsuarios);
                while (leitor.hasNextLine()) {
                    String[] dadosUsuario = leitor.nextLine().split(",");
                    if (dadosUsuario.length >= 2) {
                        usuarios.put(dadosUsuario[0], dadosUsuario[1]);
                    } else {
                        System.out.println("Usuario ou senha incompletos no arquivo usuarios.txt");
                    }
                        
                } 
                leitor.close();
                
            }
        } catch (IOException e){
            System.out.println("Erro ao carregar usuarios." + e.getMessage());
        } return usuarios;
    }
    //validar o login com login e senha existentes no arquivo usuarios.txt
    private static boolean validarLogin(String login, String senha, Map<String, String> usuarios) {
        return usuarios.containsKey(login) && usuarios.get(login).equals(senha);
    }
    //metodo para salvar os usuarios 
    private static void salvarUsuarios(Map<String, String> usuarios) {
        try {
            FileWriter writer = new FileWriter("usuarios.txt");
            for (Map.Entry<String, String> entry : usuarios.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
    //menu de eventos
    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Ver todos os eventos");
        System.out.println("2. Cadastrar novo evento");
        System.out.println("3. Ver eventos passados");
        System.out.println("4. Partcipar de evento disponível");
        System.out.println("5. Ver eventos que estou cadastrado");
        System.out.println("6. Sair do programa");
        System.out.print("Escolha uma opção: ");
    }
    //metodo para cadastrar evento
    private static void cadastrarNovoEvento(Scanner scanner) {
        System.out.println("Digite o nome do evento");
        String eventoNome = scanner.nextLine();
        System.out.println("Digite tipo do evento, exemplo: show, etc");
        String eventoTipo = scanner.nextLine();
        System.out.println("Local do evento:");
        String eventoLocal = scanner.nextLine();
        System.out.println("Data e hora do evento, formato: yyyy-MM-dd HH:mm");
        String eventoDataStr = scanner.nextLine();
        System.out.println("Digite a descrição do evento");
        String eventoDescricao = scanner.nextLine();
        // Converte a string data para LocalDateTime
        LocalDateTime eventoData = LocalDateTime.parse(eventoDataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        // Criar novo evento
        Evento novoEvento = new Evento(eventoNome, eventoTipo, eventoLocal, eventoDescricao, eventoData);
        // Salvar o novo evento
        salvarEvento(novoEvento);
    }
    //metodo para salvar evento no arquivo evento.txt

    private static void salvarEvento(Evento evento) {
        try {
            FileWriter writer = new FileWriter("eventos.txt", true);
            String dataFormatada = evento.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            writer.write(evento.getNome() + "," + evento.getTipo() + "," + evento.getLocal() + "," + dataFormatada + "," + evento.getDescricao() + "\n");
            writer.close();
            System.out.println("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo de eventos: " + e.getMessage());
        }
    }
//metodo para ler arquivo eventos.txt   
    public static List<Evento> lerEventosDoArquivo(String nomeArquivo) {
        List<Evento> eventos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 5) {
                    String nome = partes[0];
                    String tipo = partes[1];
                    String local = partes[2];
                    LocalDateTime data = LocalDateTime.parse(partes[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    String descricao = partes[4];
                    eventos.add(new Evento(nome, tipo, local, descricao, data));
                } else {
                    System.out.println("Formato inválido para evento: " + linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventos;
    }
//metodo para comparar os eventos com a data atual


 
}