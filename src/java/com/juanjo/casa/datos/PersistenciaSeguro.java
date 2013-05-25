/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juanjo.casa.datos;

import com.juanjo.casa.negocio.Seguro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jj
 */
public class PersistenciaSeguro  {
    
   
    public void insert(Seguro seguro) {
        
        try{
            
            String sql = "INSERT INTO Seguros (idSeguro, nif, nombre, ape1, ape2, edad, numHijos, fechaCreacion) VALUES (?,?,?,?,?,?,?,?)";
            
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            
            preparedStatement.setInt(1,seguro.getIdSeguro());
            preparedStatement.setString(2,seguro.getNif());
            preparedStatement.setString(3,seguro.getNombre());
            preparedStatement.setString(4,seguro.getApe1());
            preparedStatement.setString(5,seguro.getApe2());
            preparedStatement.setInt(6,seguro.getEdad());
            preparedStatement.setInt(7,seguro.getNumHijos());
            preparedStatement.setDate(8,seguro.getFechaCreacion());
            
            preparedStatement.executeUpdate();


        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    
    public Seguro get(int idSeguro){
        try{
            String sql = "SELECT * FROM Seguro WHERE IdSeguro = ?";
            
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idSeguro);
            ResultSet rst = preparedStatement.executeQuery();
            if(rst.next()){
                Seguro seguro = new Seguro(rst.getInt("idSeguro"), rst.getString("nif"),
                        rst.getString("ape1"), rst.getString("ape2"), rst.getInt("edad"),
                        rst.getInt("numHijos"), rst.getDate("fechaCreacion"));
            
            
            return seguro;
            }else{
                return null;
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    
    private Connection getConnection(){
        
        try{
            String url = "jdbc:mysql://localhost:3306/seguros";
            String user = "root";
            String password = "";
            String driver = "com.mysql.jdbc.Driver";
            
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            
            return connection;
        }catch (Exception ex){
            throw new RuntimeException (ex);
            
        }
    }
    
}
