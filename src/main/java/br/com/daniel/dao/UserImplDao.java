package br.com.daniel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.daniel.model.User;
import br.com.daniel.utils.ConnectionFactory;

public class UserImplDao implements UserDao {

    @Override
    public void salvarUsuario(User user) throws Exception{
        String sql = "insert into tb_usuario (username, password) values (?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<User> buscarTodosUsuario() throws Exception{
        List<User> usuarios = new ArrayList<>();
        String sql = "select * from tb_usuario";
        try (Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("username"), resultSet.getString("password"));
                usuarios.add(user);
            }
        }
        return usuarios;
    }
    
    @Override
    public User buscarUsuarioPorId(Long id) throws Exception{
        String sql = "select * from tb_usuario where id = ?";
        User user = null;
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getString("username"), resultSet.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void atualizarUsuario(User user) throws Exception{
        String sql = "update tb_usuario set username = ?, password = ? where id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void excluirUsuario(Long id) throws Exception{
        String sql = "delete from tb_usuario where id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
