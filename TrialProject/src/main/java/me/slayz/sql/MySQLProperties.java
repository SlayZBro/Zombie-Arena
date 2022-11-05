package me.slayz.sql;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLProperties {
    public static MySQLSetup m;


    public MySQLProperties(MySQLSetup m) {
        this.m=m;
        createTable();
    }

    public void createTable(){
        PreparedStatement posted;
        try {
            posted = m.getCon().prepareStatement("CREATE TABLE IF NOT EXISTS Stats"
                    + "  (UUID           TEXT,"
                    + "   KILLS            INTEGER,"
                    + "   SESSIONS          INTEGER,"
                    + "   DEATHS           INTEGER)");
            posted.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player p) {
        PreparedStatement posted;
        try {
            posted = m.getCon().prepareStatement("INSERT INTO Stats (UUID, KILLS, SESSIONS, DEATHS) VALUES ('"
                    +p.getUniqueId()+"', '"+0+"', '"+0+"', '"+0+"')");
            posted.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean isPlayerExists(Player p) {
        try {
            PreparedStatement posted = m.getCon().prepareStatement("SELECT * FROM Stats WHERE UUID = '"+p.getUniqueId()+"'");
            ResultSet result = posted.executeQuery();
            if(result.next())
                return true;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getKills(Player p) {
        int a=-1;
        try {
            PreparedStatement poster = m.getCon().prepareStatement("SELECT * FROM Stats WHERE UUID = '"+p.getUniqueId().toString()+"'");
            ResultSet result = poster.executeQuery();
            result.next();
            a=result.getInt("KILLS");
            return a;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getDeaths(Player p) {
        int a=-1;
        try {
            PreparedStatement poster = m.getCon().prepareStatement("SELECT * FROM Stats WHERE UUID = '"+p.getUniqueId().toString()+"'");
            ResultSet result = poster.executeQuery();
            result.next();
            a=result.getInt("DEATHS");
            return a;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getSessions(Player p) {
        int a=-1;
        try {
            PreparedStatement poster = m.getCon().prepareStatement("SELECT * FROM Stats WHERE UUID = '"+p.getUniqueId().toString()+"'");
            ResultSet result = poster.executeQuery();
            result.next();
            a=result.getInt("SESSIONS");
            return a;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return a;
    }


    public void updateKills(Player p, int a) {
        PreparedStatement statement;
        try {
            statement = m.getCon().prepareStatement("UPDATE Stats SET KILLS = '"+a+"' WHERE UUID = '"+p.getUniqueId().toString()+"'");
            statement.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDeaths(Player p, int a) {
        PreparedStatement statement;
        try {
            statement = m.getCon().prepareStatement("UPDATE Stats SET DEATHS = '"+a+"' WHERE UUID = '"+p.getUniqueId().toString()+"'");
            statement.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateSessions(Player p, int a) {
        PreparedStatement statement;
        try {
            statement = m.getCon().prepareStatement("UPDATE Stats SET SESSIONS = '"+a+"' WHERE UUID = '"+p.getUniqueId().toString()+"'");
            statement.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }




}
