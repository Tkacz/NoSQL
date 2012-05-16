/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.lang.model.element.Element;

/**
 *
 * @author Rafal Tkaczyk
 */
public class RMySQL {

    static private Connection con;
    final private String sql_connect;
    final private String sql_driver;
    static private Statement st;
    final private String sql_login;
    final private String sql_password;
    static private ResultSet rs;

    public RMySQL() {
        con = null;
        sql_connect = "jdbc:mysql://127.0.0.1/ufo?characterEncoding=utf8";
        sql_driver = "com.mysql.jdbc.Driver";
        sql_login = "root";
        sql_password = "pass";

        if (setDriver()) {
            System.out.println("Udane połączenie z bazą danych");
        } else {
            System.out.println("Nie udalo sie nawiazac polaczenia z baza danych");
        }
    }

    private boolean setDriver() {
        boolean ret = false;
        try {
            Class.forName(sql_driver);
            createStatement();
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Exception" + ex);
        } catch (Exception ex) {
            System.out.println("Driver Exception" + ex);
        }
        return ret;
    }

    private boolean open() {
        boolean ret = false;
        try {
            if ((con == null) || con.isClosed()) {
                System.out.println("Laczenie z baza danych");
                con = (Connection) DriverManager.getConnection(sql_connect, sql_login, sql_password);
                System.out.println("Polaczony z baza danych");
            }
            ret = true;
        } catch (SQLException ex) {
            System.out.println("Open Exception" + ex);
            while (ex != null) {
                ex = ex.getNextException();
            }
        }
        return ret;
    }

    private void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Close Con Exception" + ex);
        }
    }

    private void createStatement() {
        if (open()) {
            try {
                if (st == null || st.isClosed()) {
                    st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                }
            } catch (SQLException ex) {
                System.out.println("createStatement Exception" + ex);
                while (ex != null) {
                    ex = ex.getNextException();
                }
            }
        }
    }

    private void select(String comm) {//zapytania
        if (comm != null) {
            try {
                rs = st.executeQuery(comm);
                System.out.println("Select complete");
            } catch (SQLException ex) {
                System.out.println("executeQuery Exception");
            }
        } else {
            System.out.println("query == null");
        }
    }

    private void execute(String comm) {//dodawanie do tabeli
        if (comm != null) {
            try {
                st.execute(comm);
            } catch (SQLException ex) {
                System.out.println("executeUpdate Exception: " + ex.toString());
            }
        }
    }

    ///////////////////////
    public void loadDataFromFile(String fileName) {//ładowanie danych z pliku .json

        FileReader fr = null;
        String begin = "INSERT INTO Events (DateOccurred, DateReported, Location, ShortDescription, Duration, LongDescription, USCity, USState, YearMonth) VALUES (";
        String end = ");";
        String updt;

        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            JsonParser parser = new JsonParser();
            JsonObject json;
            String str;
            while ((str = br.readLine()) != null) {
                json = (JsonObject) parser.parse(str);
                updt = begin;
                updt += "'" + json.get("DateOccurred").getAsString() + "', ";
                updt += "'" + json.get("DateReported").getAsString() + "', ";
                updt += "'" + json.get("Location").getAsString() + "', ";
                updt += "'" + json.get("ShortDescription").getAsString() + "', ";
                updt += "'" + json.get("Duration").getAsString() + "', ";
                updt += "'" + json.get("LongDescription").getAsString() + "', ";
                updt += "'" + json.get("USCity").getAsString() + "', ";
                updt += "'" + json.get("USState").getAsString() + "', ";
                updt += "'" + json.get("YearMonth").getAsString() + "'";
                updt += end;
                execute(updt);
            }

            fr.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void exportToJson() {//konwertowanie ResulSet'a do obiektu i dodanie do tablicy
        try {
            select("SELECT * FROM Events;");

            FileWriter fw = new FileWriter("ufo_export_sql.json");
            String str;

            while (rs.next()) {
                str = "{ ";
                str += "\"id\": \"" + Integer.toString(rs.getInt(1)) + "\"";
                str += ", \"DateOccurred\": \"" + rs.getString(2) + "\"";
                str += ", \"DateReported\": \"" + rs.getString(3) + "\"";
                str += ", \"Location\": \"" + rs.getString(4) + "\"";
                str += ", \"ShortDescription\": \"" + rs.getString(5) + "\"";
                str += ", \"Duration\": \"" + rs.getString(6) + "\"";
                str += ", \"LongDescription\": \"" + rs.getString(7) + "\"";
                str += ", \"USCity\": \"" + rs.getString(8) + "\"";
                str += ", \"USState\": \"" + rs.getString(9) + "\"";
                str += ", \"YearMonth\": \"" + rs.getString(10) + "\"";
                str += " }\n";
                fw.write(str);
            }

            fw.close();
        } catch (SQLException ex) {
            System.out.println("RS getString Exception: " + ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public ArrayList<Event> findAll() {//konwertowanie ResulSet'a do obiektu i dodanie do tablicy
        ArrayList<Event> result = new ArrayList<Event>();
        try {
            select("SELECT * FROM Events;");

            FileWriter fw = new FileWriter("ufo_export_sql.json");
            String str;
            while (rs.next()) {
                Event temp = new Event();
                temp.setId(Integer.toString(rs.getInt(1)));
                temp.setDateOccurred(rs.getString(2));
                temp.setDateReported(rs.getString(3));
                temp.setLocation(rs.getString(4));
                temp.setShortDescription(rs.getString(5));
                temp.setDuration(rs.getString(6));
                temp.setLongDescription(rs.getString(7));
                temp.setUSCity(rs.getString(8));
                temp.setUSState(rs.getString(9));
                temp.setYearMonth(rs.getString(10));
                result.add(temp);
            }

            fw.close();
        } catch (SQLException ex) {
            System.out.println("RS getString Exception: " + ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return result;
    }
    
    public void removeAll() {
        execute("DELETE FROM Events;");
    }
}
