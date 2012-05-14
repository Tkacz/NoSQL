/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;


/**
 *
 * @author Rafal Tkaczyk
 */
public class NoSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MongoDB mongo = new MongoDB();
        mongo.map_reduce();
        //mongo.removeAll();
        //mongo.loadDataFromFile("ufo_export.json");
        //mongo.findAll();
        //mongo.exportToJson();
    }
}
