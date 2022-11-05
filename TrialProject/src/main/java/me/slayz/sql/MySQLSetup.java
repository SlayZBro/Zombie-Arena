package me.slayz.sql;

import me.slayz.Main;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLSetup {

    private Connection con =null;


    public  void setupSQL() {
        try{
            File file = new File(Main.getInstance().getDataFolder().getPath()+"/stats.db");
            if(!file.exists())
                file.createNewFile();

            con = DriverManager.getConnection("jdbc:sqlite:"+ Main.getInstance().getDataFolder().getPath()+"/stats.db");

        }catch(SQLException e){
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
