import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppEventos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Menu:");
        System.out.println("1.Ver proximos eventos");
        System.out.println("2.Cadastrar novo evento");
        System.out.println("3.Ver eventos passados");
        System.out.println("4.Marcar presença em evento disponivel");
        System.out.println("5.Ver eventos que estou cadastrado");
        int opcaoMenu = scanner.nextInt();
        scanner.nextLine();
        switch (opcaoMenu) {
            case 1:
                System.out.println("Proximos eventos:");
                 List<Evento> proximosEventos = lerEventosDoArquivo("eventos.data");
                 for (Evento evento : proximosEventos) {
                    System.out.println(evento);
                }
                
                break;
            case 2:
                System.out.println("Digite tipo do evento, exemplo: show,etc");
                String eventoTipo = scanner.nextLine();
                System.out.println("Digite o nome do evento");
                String eventoNome = scanner.nextLine();
                System.out.println("Local do evento:");
                String eventoLocal = scanner.nextLine();
                System.out.println("Data e hora do evento, formato: yyyy-dd-MM-dd hh:mm");
                String eventoDataStr = scanner.nextLine();
                System.out.println("Digite a descrição do evento");
                String eventoDescricao = scanner.nextLine();
                //converte a string data para LocalDate
                LocalDateTime eventoData = LocalDateTime.parse(eventoDataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                //imprementar logica para salvar os eventos(talvez vou utilizar o mesmo usado para salvar os usuarios)
                //Evento novoEvento = new Evento(eventoNome, eventoTipo, eventoLocal, eventoData); <-- criar novo evento (não funcional ainda)

                break;
            case 3:
                System.out.println("Eventos passados:");
                //colocar aqui como vai ser importado os eventos e comparados com a data no eventos.data
            case 4:
                System.out.println("Lista de eventos disponiveis:");
                //colocar eventos que estão pra acontecer ordenado por proximidade de local e hora.
                System.out.println("Escolhe o evento que gostaria de ir! :)");
                String eventoSelecionado = scanner.nextLine();
                System.out.println("Confirmar presença no evento (x)? [s/n]");
                String confirmacao = scanner.nextLine();
                if (confirmacao.equalsIgnoreCase("s")) {
                    // Adicione aqui a lógica para confirmar a presença do usuário no evento selecionado
                }
                break;


            case 5:
                System.out.println("Eventos que marquei presença:");
                //colocar eventos de acordo com o usuario
                
                break;
        
            default:
            System.out.println("Opção invalida.");
                break;
        }
        salvarUsuarios(usuarios);
        scanner.close();

        
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
            System.out.println("Usuários salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
//metodo para ler arquivo eventos.data   
    public static List<Evento> lerEventosDoArquivo(String nomeArquivo) {
    try (FileInputStream fileInputStream = new FileInputStream(nomeArquivo);
         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
        List<Evento> eventos = (List<Evento>) objectInputStream.readObject();
        return eventos;
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }
}

 
}