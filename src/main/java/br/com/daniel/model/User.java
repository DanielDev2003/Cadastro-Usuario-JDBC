package br.com.daniel.model;

public class User {
    
    private long id;
    private String username;
    private String password;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    
}
