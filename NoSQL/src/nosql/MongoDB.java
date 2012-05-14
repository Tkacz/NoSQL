/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import java.io.IOException;
import java.net.UnknownHostException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author rufus
 */
public class MongoDB {

    Mongo m;
    DB db;
    DBCollection coll;

    public MongoDB() {//w konstruktorze łaczenie z bazą danych
        try {
            m = new Mongo("127.0.0.1");
            db = m.getDB("ufos");
            coll = db.getCollection("event");
        } catch (UnknownHostException ex) {
            System.out.println(ex.toString());
        } catch (MongoException ex) {
            System.out.println(ex.toString());
        }
    }

    public void loadDataFromFile(String fileName) {//ładowanie danych z pliku .json

        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            JsonParser parser = new JsonParser();
            JsonObject json;
            String str;
            while ((str = br.readLine()) != null) {
                BasicDBObject doc = new BasicDBObject();
                json = (JsonObject) parser.parse(str);
                doc.put("id", json.get("id").toString());
                doc.put("DateOccurred", json.get("DateOccurred").toString());
                doc.put("DateReported", json.get("DateReported").toString());
                doc.put("Location", json.get("Location").toString());
                doc.put("ShortDescription", json.get("ShortDescription").toString());
                doc.put("Duration", json.get("Duration").toString());
                doc.put("LongDescription", json.get("LongDescription").toString());
                doc.put("USCity", json.get("USCity").toString());
                doc.put("USState", json.get("USState").toString());
                doc.put("YearMonth", json.get("YearMonth").toString());
                coll.insert(doc);
            }

            fr.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (MongoException ex) {
            System.out.println(ex.toString());
        }

    }

    public void findAll() {//wyświetla wszystkie rekordy z bazy
        DBCursor cur = coll.find();
        DBObject obj;
        while (cur.hasNext()) {
            obj = cur.next();
            System.out.println("id: " + obj.get("id").toString());
            System.out.println("DateOccurred: " + obj.get("DateOccurred").toString());
            System.out.println("DateReported: " + obj.get("DateReported").toString());
            System.out.println("Location: " + obj.get("Location").toString());
            System.out.println("ShortDescription: " + obj.get("ShortDescription").toString());
            System.out.println("Duration: " + obj.get("Duration").toString());
            System.out.println("LongDescription: " + obj.get("LongDescription").toString());
            System.out.println("USCity: " + obj.get("USCity").toString());
            System.out.println("USState: " + obj.get("USState").toString());
            System.out.println("YearMonth: " + obj.get("YearMonth").toString());
            System.out.println("\n----------------------------------------------------------------\n");
        }
    }
    
    public void exportToJson() {//exportuje zawartość bazy do pliku .json
        DBCursor cur = coll.find();
        DBObject obj;
        String str;
                
        try {
            FileWriter fw = new FileWriter("ufo_export.json");
            while (cur.hasNext()) {
                obj = cur.next();
                str = "{ ";
                str += "\"id\": " + obj.get("id").toString();
                str += ", \"DateOccurred\": " + obj.get("DateOccurred").toString();
                str += ", \"DateReported\": " + obj.get("DateReported").toString();
                str += ", \"Location\": " + obj.get("Location").toString();
                str += ", \"ShortDescription\": " + obj.get("ShortDescription").toString();
                str += ", \"Duration\": " + obj.get("Duration").toString();
                str += ", \"LongDescription\": " + obj.get("LongDescription").toString();
                str += ", \"USCity\": " + obj.get("USCity").toString();
                str += ", \"USState\": " + obj.get("USState").toString();
                str += ", \"YearMonth\": " + obj.get("YearMonth").toString();
                str += " }\n";
                fw.write(str);
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void map_reduce(){
        String map = "function() { emit(this.USState, 1); };";
        String reduce = "function(key, values) { var result = 0; values.forEach(function(count) { result += count; }); return result; };";
                
        MapReduceOutput out = coll.mapReduce(map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
        for (DBObject obj : out.results()) {
            System.out.println(obj);
        }
    }

    public void removeAll() {//usuwa zawartość bazy
        coll.drop();
    }
}
