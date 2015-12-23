package com.ipartek.migracion.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	   /**Parametros de conexion*/
	   static String bd = "iparsex";
	   static String login = "root";
	   static String password = "";
	   static String url = "jdbc:mysql://localhost/"+bd;
	 
	   Connection connection = null;
	 
	   /** Constructor de DbConnection */
	   public DbConnection() {
	      try{
	         //obtenemos el driver de para mysql
	         Class.forName("com.mysql.jdbc.Driver");
	         //obtenemos la conexi�n
	         connection = DriverManager.getConnection(url,login,password);
	 
	         if (connection!=null){
	            System.out.println("Conexion a base de datos "+bd+" OK\n");
	         }
	      }
	      catch(SQLException e){
	         System.out.println(e);
	      }catch(ClassNotFoundException e){
	         System.out.println(e);
	      }catch(Exception e){
	         System.out.println(e);
	      }
	   }
	   
	   
	   /**Permite retornar la conexi�n*/
	   public Connection getConnection(){
	      return connection;
	   }
	 
	   public void desconectar(){
		   System.out.println("Conexion a base de datos "+bd+" cerrada\n");
	      connection = null;
	   }
	}
