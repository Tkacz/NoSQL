/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

import javax.swing.JFrame;


/**
 *
 * @author Rafal Tkaczyk
 */
public class NoSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new NoSQLView();
                frame.setTitle("UFO in US");
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(NoSQLView.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        
        
        //// CSV to JSON ////
        //CSVtoJSON ctj = new CSVtoJSON();//konwertowanie pliku csv do jsona
        //ctj.convert("ufo.us.csv");
        
        
        //// MngoDB Test ////
        //MongoDB mongo = new MongoDB();
        //mongo.loadDataFromFile("ufo.us.json");//ładowanie danych z jsona
        //mongo.map_reduce();//ilość zdarzeń w kązdym stanie US
        //mongo.findAll();//drukuje wszystkie dane
        //mongo.exportToJson();//eksport do jsona
        //mongo.removeAll();//czyszczenie kolekcji z danych
        
        
        //// MySQL Test ////
        //RMySQL sql = new RMySQL();
        //sql.loadDataFromFile("ufo.us.json");//wczytywanie danych z jsona
        //sql.exportToJson();//eksport do jsona
        //sql.removeAll();//usuwanie zawartości tabeli
        
        
        //// Export ////
        //MongoToSql(mongo, sql);//eksport z mongo do mysqla
        //MySqlToMongo(mongo, sql);//eksport z mysqla do mongo
    }
    
    static void MySqlToMongo(MongoDB m, RMySQL sql) {
        sql.exportToJson();
        m.loadDataFromFile("ufo_export_sql.json");
    }
    
    static void MongoToSql(MongoDB m, RMySQL sql) {
        m.exportToJson();
        sql.loadDataFromFile("ufo_export_mongo.json");
    }
}
