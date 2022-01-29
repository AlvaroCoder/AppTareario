package Backend;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conexion {
    private int numTareasNoEmp, numTareasEnProc, numTareasTerm, numTarea;
    
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE_NAME = "bd_tareas";
    
    public Connection conexion = null;
    public Statement statement ;
    public Statement [] statementsTarea ;
    public ResultSet resultset;
    public ResultSet [] resultTareas;
    public PreparedStatement crear, eliminar, consultar;
    
    int fechaDia, fechaMes, fechaAño;
    private String URL ="jdbc:mysql://localhost:3306/";
    public Conexion(){
        URL+=DATABASE_NAME+"?serverTimezone=UTC";
    }
    public Conexion(int fechaDia, int fechaMes, int fechaAño){
        URL+=DATABASE_NAME+"?serverTimezone=UTC";
        this.fechaDia=fechaDia;
        this.fechaMes = fechaMes;
        this.fechaAño = fechaAño;
    }
    public void setNumTareasNoEmp(int dia, int mes, int año){
        String query_NumTareasNoEmp = "SELECT COUNT(estado) AS 'numTareas' FROM tareas WHERE estado = 0 AND fechaDia = %d AND fechaMes = %d AND fechaAño = %d; ".formatted(dia,mes,año);
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset =statement.executeQuery(query_NumTareasNoEmp);
            resultset.next();
            numTareasNoEmp = resultset.getInt("numTareas");
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void setNumTareasEnProc(int dia, int mes, int año){
        String query_NumTareasEnProc = "SELECT COUNT(estado) AS 'numTareas' FROM tareas WHERE estado = 1 AND fechaDia = %d AND fechaMes = %d AND fechaAño = %d; ".formatted(dia,mes,año);
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset =statement.executeQuery(query_NumTareasEnProc);
            resultset.next();
            numTareasEnProc = resultset.getInt("numTareas");
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void setNumTareasTerm(int dia, int mes, int año){
        String query_NumTareasTerm = "SELECT COUNT(estado) AS 'numTareas' FROM tareas WHERE estado = 2 AND fechaDia = %d AND fechaMes = %d AND fechaAño = %d; ".formatted(dia,mes,año);
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset =statement.executeQuery(query_NumTareasTerm);
            resultset.next();
            numTareasTerm = resultset.getInt("numTareas");
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void setNumTarea(int dia, int mes, int año){
        String query_NumTareasTerm = "SELECT COUNT(estado) AS 'numTareas' FROM tareas WHERE (estado = 0 OR estado = 1) AND fechaDia = %d AND fechaMes = %d AND fechaAño = %d; ".formatted(dia,mes,año);
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset =statement.executeQuery(query_NumTareasTerm);
            resultset.next();
            numTarea = resultset.getInt("numTareas");
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public int getNumTareas(){
        return this.numTarea;
    }
    public int getNumTareasNoEmp(){
        return this.numTareasNoEmp;
    }
    public int getNumTareasEnProc(){
        return this.numTareasEnProc;
    }
    public int getNumTareasTerm(){
        return this.numTareasTerm;
    }
    public HashMap traerTarea(int index){
        HashMap <String, String> hash = new HashMap<>();
        String queryTraerTarea = "SELECT * FROM tareas WHERE idtareas = "+index;
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset =statement.executeQuery(queryTraerTarea);
            resultset.next();
            hash.put("idTarea",Integer.toString(resultset.getInt("idtareas")));
            hash.put("nombre", resultset.getString("nombre"));
            hash.put("estado", resultset.getString("estado"));
            hash.put("descripcion", resultset.getString("descripcion"));
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
        return hash;
    }
    public ArrayList mostrarTareas(int fechaDia){
        ArrayList array = new ArrayList<String>();
        HashMap<String, String> datos ;
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset = statement.executeQuery("SELECT * FROM tareas WHERE fechaDia = "+fechaDia);
            
            while(resultset.next()){
                
                datos = new HashMap<>();
                datos.put("idTarea", Integer.toString(resultset.getInt("idtareas")));
                datos.put("nombre", resultset.getString("nombre"));
                datos.put("descripcion",resultset.getString("descripcion"));
                datos.put("duracion", Integer.toString(resultset.getInt("duracion")));
                datos.put("estado",Integer.toString(resultset.getInt("estado")));
                array.add(datos);
            }
           
            conexion.close();
           
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
        return array;
    }
    public void empezarTarea(int indexTarea){
        try{
            String query = "UPDATE tareas SET estado = 1 WHERE idtareas = "+indexTarea+";";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            crear = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            crear.execute();
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void pararTarea(int indexTarea){
        try{
            String query = "UPDATE tareas SET estado = 0 WHERE idtareas = "+indexTarea+";";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            crear = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            crear.execute();
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void reiniciarTarea(int indexTarea){
        try{
            String query = "UPDATE tareas SET estado = 0 WHERE idtareas = "+indexTarea+";";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            crear = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            crear.execute();
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void checkTarea(int indexTarea){
        try{
            String query = "UPDATE tareas SET estado = 2 WHERE idtareas = "+indexTarea+";";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            crear = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            crear.execute();
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    public void crearTarea(String nombreTarea, String descripcionTarea, int estado){
        try{
            LocalDate date = LocalDate.now();
            String query = "INSERT INTO tareas (nombre,descripcion,duracion,estado,fechaDia,fechaMes,fechaAño) VALUES ('"+nombreTarea+"','"+descripcionTarea+"',"+0+","+estado+","+date.getDayOfMonth()+","+date.getMonthValue()+","+date.getYear()+");";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            crear = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            crear.execute();
            conexion.close();
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
    }
    
    public void eliminarTarea(int index){
        try{
            String query = "DELETE FROM tareas WHERE idtareas = "+index;
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            eliminar = conexion.prepareStatement(query);
            eliminar.execute();
            conexion.close();
        }catch(SQLException err){
            System.out.println(err);
        }
    }
    public String crearUsuario(String nombre, String password, String email){
        String mensaje = "El usuario ha sido creado correctamente :) ";
        try{
            String query = "INSERT INTO usuario (nombre,descripcion,duracion,estado,fechaDia,fechaMes,fechaAño) VALUES ('"+nombre+"','"+password+"','"+email+"');";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            crear = conexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            crear.execute();
            conexion.close();

        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
        return mensaje;
    }
    public HashMap traerUsuario(String nombre, String contraseña){
        HashMap user = new HashMap<String, String>();
        try{
            String query = "SELECT * FROM usuario WHERE nombre = "+nombre+"',"+" AND contraseña = '"+contraseña+"';";
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
            statement = conexion.createStatement();
            resultset = statement.executeQuery(query);
            if(resultset.next()){
                user.put("idusuario", Integer.toString(resultset.getInt("idusuario")));
                user.put("nombre", resultset.getString("nombre"));
                user.put("contraseña", resultset.getString("contraseña"));
                user.put("email", resultset.getString("email"));
            }
        }catch(SQLException err){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, err);
        }
        return user;
    }
}
