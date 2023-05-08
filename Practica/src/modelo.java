import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modelo implements ActionListener{
    private static Connection con = null;
    private boolean currrentst=false;
    public boolean currrentsrch=false;
    private Display display;
    public modelo(){
        display=new Display(this);
        System.out.println("Starting Server...");
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pcdealguien","ash","ash");
        } catch (SQLException exception) {
            System.out.println("Cannot establish connection to server");
            exception.printStackTrace();
        }
        if (con != null) {
            System.out.println("Conexion established");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Registrar"){
            display.tipo();
            currrentst=false;
        }else if(e.getActionCommand()=="Buscar"){
            display.tipo();
            currrentst=true;
        }else if(e.getActionCommand()=="Chback"){
            display.inicio();
        }else if(e.getActionCommand()=="Entrenador"){
            if(currrentst){
                display.busqueda();
                currrentsrch=false;
            }else{
                display.registraren();
            }
        }else if(e.getActionCommand()=="Pokemon"){
            if(currrentst){
                display.busqueda();
                currrentsrch=true;
            }else{
                display.registrarpk();
            }
        }else if(e.getActionCommand()=="Tyback"){
            display.tipo();
        }else if(e.getActionCommand()=="Scback"){
            display.busqueda();
        }
    }
    public  String registraren(int id,String nombre,int region){

        String query = "select idEntrenador from entrenador where idEntrenador = "+id+" ;";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String> res=new ArrayList<String>();
            while (rs.next()) {
                res.add(rs.getString("idEntrenador"));
            }

            if(res.size()==0){
                query="insert into `entrenador` values ("+id+",'"+nombre+"',0,"+region+");";
                    boolean rs2 = stmt.execute(query);
                    if(rs2=true){
                        return "Se ha creado el usuario";
                    }else{
                        return "Ha ocurrido un error";
                    }
                }else{
                return "Este usuario ya existe";
            }
            
            } catch (SQLException e) {
                System.err.println("Error "+e.getSQLState());
                return "Ha ocurrido un error";
        }
    }
    public  String registrarpok(int id,String nombre,String apodo,int lvl){

        String query = "select idEntrenador from entrenador where idEntrenador = "+id+" ;";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String> res=new ArrayList<String>();
            while (rs.next()) {
                res.add(rs.getString("idEntrenador"));
            }

            if(res.size()==1){
                query="select idpok from pokemon where Nombre='"+nombre+"';";
                try (Statement stmt2 = con.createStatement()) {
                    rs = stmt2.executeQuery(query);
                    ArrayList<String> res2=new ArrayList<String>();
                    while (rs.next()) {
                        res2.add(rs.getString("idpok"));
                    }
                    if(res2.size()==1){
                        query="insert into `captura`(idpok, idEntrenador, apodo, nivel) values ("+res2.get(0)+","+id+",'"+apodo+"',"+lvl+");";
                        try (Statement stmt3 = con.createStatement()) {
                            stmt3.execute(query);
                            return "Registrado con exito";
                        } catch (SQLException e) {
                            System.err.println("Error "+e.getSQLState());
                            return "Ha ocurrido un error";
                        }
                    }else{
                        return "Nombre de pokemon incorrecto";
                    }
                } catch (SQLException e) {
                    System.err.println("Error "+e.getSQLState());
                    return "Ha ocurrido un error";
                }
                
            }else{
                return "Este usuario no existe";
            }
            
            } catch (SQLException e) {
                System.err.println("Error "+e.getSQLState());
                return "Ha ocurrido un error";
        }
    }
    public String busquedaen(String tex){
        try (Statement stmt = con.createStatement()) {
        String query="select nombre,idEntrenador from entrenador where nombre like '%"+tex+"%';";
        String res="";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            res+=rs.getString("nombre")+","+rs.getString("idEntrenador")+";";
        }
        return res;
        } catch (SQLException e) {
            System.err.println("Error "+e.getSQLState());
            return "Ha ocurrido un error";
        }
    }
    public String busquedapk(String tex){
        try (Statement stmt = con.createStatement()) {
        String query="select Nombre,idpok from pokemon where Nombre like '%"+tex+"%';";
        String res="";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            res+=rs.getString("Nombre")+","+rs.getString("idpok")+";";
        }
        return res;
        } catch (SQLException e) {
            System.err.println("Error "+e.getSQLState());
            return "Ha ocurrido un error";
        }
    }
    public String pokemon(int id){
        try (Statement stmt = con.createStatement()) {
        String query="select idpok,Nombre,region.Región,tipos.Tipo1,tipos.Tipo2 from pokemon join region on region.idGen=pokemon.idGen join tipos on pokemon.idTipos=tipos.idTipos where idpok ="+id+";";
        String res="";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            res+=rs.getString("idpok")+","+rs.getString("Nombre")+","+rs.getString("region.Región")+","+rs.getString("tipos.Tipo1")+","+rs.getString("tipos.Tipo2")+";";
            break;
        }
        return res;
        } catch (SQLException e) {
            System.err.println("Error "+e.getSQLState());
            return "Ha ocurrido un error";
        }
    }
    public String entrenador(int id){
        try (Statement stmt = con.createStatement()) {
        String query="select entrenador.Nombre, idEntrenador, region.Región from entrenador join region on entrenador.idGen=region.idGen where entrenador.idEntrenador="+id+";";
        String res="";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            res+=rs.getString("entrenador.Nombre")+","+rs.getString("idEntrenador")+","+rs.getString("region.Región")+";";
            break;
        }
        return res;
        } catch (SQLException e) {
            System.err.println("Error "+e.getSQLState());
            return "Ha ocurrido un error";
        }
    }
    public String pokemons(int id){
        try (Statement stmt = con.createStatement()) {
        String query="select pokemon.Nombre, pokemon.idpok, captura.apodo from pokemon join captura on captura.idpok=pokemon.idpok join entrenador on captura.idEntrenador=entrenador.idEntrenador where entrenador.idEntrenador="+id+";";
        String res="";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            res+=rs.getString("pokemon.Nombre")+","+rs.getString("pokemon.idpok")+","+rs.getString("captura.apodo")+";";
        }
        return res;
        } catch (SQLException e) {
            System.err.println("Error "+e.getSQLState());
            return "Ha ocurrido un error";
        }
    }
}
