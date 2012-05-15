NoSQL
=====

Bazy NoSQL - laby


Repozytorium zawiera Netbeansowy projekt napisany w Javie.

W głównej klasie 'NoSQL' są potworzone wszelkie obiekty i wywołania ich metod potrzebne do przetestowania mojej pracy. Wykorzystując obiekty poniższych klas, zaimplementowałem w niej również krótkie metody eksportujące dane z jednej bazy do drugiej.

Klasa 'CSVtoJSON' ma za zadanie przekonwertować plik csv z danymi do strawnego przez MongoDB jsona.

Klasa 'MongoDB' odpowiada za połączenie z bazą i operacje na niej wykonywanych, zawiera:
- metodę ładującą dane do bazy z jsona;
- metodę eksportującą dane z bazy do pliku.json;
- metodę czyszczącą kolekcję;
- metodę wykonywującą mapreduce na danych (zlicznie ile zdarzeń wystąpiło w każdym stanie US);


Klasa 'RMySQL' odpowiada za połączenie z bazą MySQL i operacje wykonywane na niej, zawiera:
- metodę ładującą dane do bazy z pliku .json;
- metodę eksportującą do pliku .json;



## MapReduce

Do bazy wczytuję dane z pliku .json, gdzie każdy wiersz zawiera dane, jak np:

```json
{ "id": "1", "DateOccurred": "1995-10-09", "DateReported": "1995-10-09", "Location": " Iowa City, IA", "ShortDescription": "", "Duration": "", "LongDescription": "Man repts. witnessing &quot;flash, followed by a classic UFO, w/ a tailfin at back.&quot; Red color on top half of tailfin. Became triangular.", "USCity": "Iowa City", "USState": "ia", "YearMonth": "1995-10" }
```

Napisałem w javie prosty mapreduce, obliczający ilość obserwacji ufo w poszczególnym stanie:
- "ak" : 186.0
- "al" : 424.0
- "ar" : 458.0
- "az" : 1976.0
- "ca" : 6861.0
- "co" : 1066.0


Kod MapReduce: [MongoDB.java](https://github.com/Tkacz/NoSQL/blob/master/NoSQL/src/nosql/MongoDB.java#L128)

```java
public void map_reduce(){
        String map = "function() { emit(this.USState, 1); };";
        String reduce = "function(key, values) { var result = 0; values.forEach(function(count) { result += count; }); return result; };";
                
        MapReduceOutput out = coll.mapReduce(map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
        for (DBObject obj : out.results()) {
            System.out.println(obj);
        }
}
```