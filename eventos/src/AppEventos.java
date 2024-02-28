import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
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
                    return;
                }

                break;
            case 2: 
                scanner.nextLine();
                System.out.println("Cria um novo login:");
                String newLogin = scanner.nextLine();
                System.out.println("Crie uma senha:");1
                String newSenha = scanner.nextLine();
                System.out.println("Insira sua cidade:");
                String cidade = scanner.nextLine();
                System.out.println("Insira seu email:");
                String email = scanner.nextLine();
                //adiciona o novo usuario no arquivo usuarios.txt
                usuarios.put(newLogin, newSenha);
                try {
                    FileWriter writer = new FileWriter("usuarios.txt", true);
                    writer.write(newLogin + "," + newSenha + "," + cidade + "," + email + "," + "\n");
                    writer.close();
                    System.out.println("Usuario cadastrado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao escrever no arquivo de usuarios." + e.getMessage());
               
                    
                }
                break;
        
            default:
                System.out.println("Opção invalida.");
                break;
        }
        //Seção de menu dos eventos
        System.out.println("Login efetuado com sucesso.");
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
                //colocar aqui como vai ser importado os eventos do arquivo eventos.data
                break;
            case 2:
                System.out.println("Digite tipo do evento, exemplo: show,etc");
                String eventoTipo = scanner.nextLine();
                System.out.println("Digite o nome do evento");
                String eventoNome = scanner.nextLine();
                System.out.println("Local e data do evento, formato: yyyy-dd-MM-dd hh:mm");
                String eventoData = scanner.nextLine();
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

    private static void carregarUsuarios(Map<String, String> usuarios) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'carregarUsuarios'");
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
                    usuarios.put(dadosUsuario[0], dadosUsuario[1]);       
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
 
}