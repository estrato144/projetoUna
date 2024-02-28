import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppEventos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> usuarios = new HashMap<>();
        //carregarUsuarios(usuarios); <-- metodo ainda n criado
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
                break;
            case 2: 
                scanner.nextLine();
                System.out.println("Cria um novo login:");
                String newLogin = scanner.nextLine();
                System.out.println("Crie uma senha:");
                String newSenha = scanner.nextLine();
                System.out.println("Insira sua cidade:");
                String cidade = scanner.nextLine();
                System.out.println("Insira seu email:");
                String email = scanner.nextLine();
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
        System.out.println("4.Ver eventos que estou cadastrado");
        int opcaoMenu = scanner.nextInt();
        switch (opcaoMenu) {
            case 1:
                System.out.println("Proximos eventos:");
            case 2:
                System.out.println("Digite tipo do evento, exemplo: show,etc");
                String eventoTipo = scanner.nextLine();
                System.out.println("Digite o nome do evento");
                String eventoNome = scanner.nextLine();
                System.out.println("Local e data do evento, formato: yyyy-dd-mm-ddhh:mm");
                String dataEvento = scanner.nextLine();
            case 3:
                System.out.println("Eventos passados:");
            case 4:
                System.out.println("Eventos que marquei presença:");
                
                break;
        
            default:
                break;
        }


        
    } 
        //salvarUsuarios(usuarios);

    
    //carrega os usuarios salvos no arquivo "usuarios.txt"
    //private static void carregarUsuarios(Map<String, String> usuarios ) {}
    //escreve o usuario em um arquivo de texto "usuario.txt"
    //private static void salvarUsuarios(Map<String, String> usuarios) {
    //    try (FileWriter writer = new FileWriter("usuarios.txt")) {
    //        for (Map.Entry<String, String> entry : usuarios.entrySet()) {            
    //            writer.write(entry.getKey() + ":" + entry.getValue() + "\n");       
    //        }
    //    }   
    //        catch (IOException e) {
    //      System.out.println("Erro ao salvar usuários: " + e.getMessage());
    //        }
    
    



    
}