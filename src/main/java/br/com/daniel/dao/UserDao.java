package br.com.daniel.dao;

import java.util.List;

import br.com.daniel.model.User;

public interface UserDao {
    void salvarUsuario(User user) throws Exception;
    List<User> buscarTodosUsuario() throws Exception;
    User buscarUsuarioPorId(Long id) throws Exception;
    void atualizarUsuario(User user) throws Exception;
    void excluirUsuario(Long id) throws Exception;
}
