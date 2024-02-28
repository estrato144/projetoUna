import java.util.Scanner;

public class AppEventos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                break;
        
            default:
                System.out.println("Opção invalida.");
                break;
        } 
    }

    
}