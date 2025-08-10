package br.com.daniel;

import java.io.Console;

import br.com.daniel.dao.UserDao;
import br.com.daniel.dao.UserImplDao;
import br.com.daniel.model.User;

public class Main {

    static Console console = System.console();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(console.readLine());
            switch (opcao) {
                case 0 -> salvarUsuario();
                case 1 -> buscarTodosUsuarios();
                case 2 -> buscarUsuarioPorId();
                case 3 -> atualizarUsuario();
                case 4 -> excluirUsuario();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        } while (true);
        
    }

    private static void exibirMenu() {
        System.out.println("\n### Menu de Operações ###");
        System.out.println("0. Salvar novo usuário");
        System.out.println("1. Buscar todos usuários");
        System.out.println("2. Buscar usuário por ID");
        System.out.println("3. Atualizar usuário");
        System.out.println("4. Excluir usuário");
        System.out.println("5. Sair do programa");
        System.out.print("Escolha uma opção: ");
    }
     private static void salvarUsuario() {
        System.out.println("\n### Criar Novo usuário ###");
        System.out.print("Digite o nome do usuário: ");
        String nome = console.readLine();
        System.out.print("Digite a senha do usuário: ");
        String senha = console.readLine();
        User usuario = new User(nome, senha);
        UserDao userDao = new UserImplDao();

        try {
            userDao.salvarUsuario(usuario);
            System.out.println("Usuário cadastrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarTodosUsuarios() {
        System.out.println("\n### Buscar Todos ###");
        UserDao userDao = new UserImplDao();
        try {
            for(User user : userDao.buscarTodosUsuario()){
                System.out.println(String.format("Nome: %s, Senha: %s", user.getUsername(), user.getPassword()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    private static void buscarUsuarioPorId() {
        System.out.println("\n### Buscar usuário por ID ###");
        UserDao userDao = new UserImplDao();
        System.out.print("Digite o id do usuário: ");
        Long id = Long.parseLong(console.readLine());
        try {
            User user = userDao.buscarUsuarioPorId(id);
            if(user != null){
                System.out.print("usuário encontrado: ");
                System.out.println(user.getUsername());
            }else{
                System.out.println("Usuario não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void atualizarUsuario() {
        System.out.println("\n### Atualizar usuário ###");

        System.out.print("Digite o id do usuario que vai ser atualizado: ");
        Long id = Long.parseLong(console.readLine());

        UserDao userDao = new UserImplDao();
        try {

            User user = userDao.buscarUsuarioPorId(id);

            if(user!=null){

                System.out.print("Digite o novo nome (Nome atual: "+user.getUsername()+"): ");
                String nome = console.readLine();
                System.out.print("Digite a nova senha(Senha atual: "+user.getPassword()+"): ");
                String senha = console.readLine();

                User newUser = new User(id, nome, senha);
                try {
                    userDao.atualizarUsuario(newUser);
                    System.out.println("Usuario atualizado!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Usuario não encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void excluirUsuario() {
        System.out.println("\n### Excluir usuário ###");
        
        System.out.print("Digite o id do usuario que vai ser Deletado: ");
        Long id = Long.parseLong(console.readLine());
        UserDao userDao = new UserImplDao();
        try {
            User user = userDao.buscarUsuarioPorId(id);
            if(user!= null){
                System.out.print("Usuario deletado: " + user.getUsername());
                userDao.excluirUsuario(id);
            }else{
                System.out.println("Usuario não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}