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
- metodę wykonywującą mapreduce na danych (zlicznie ile zdarzeń wystąpiło w każdym stanie US):
--"ak" : 186.0
--"al" : 424.0
--"ar" : 458.0
--"az" : 1976.0
--"ca" : 6861.0
--"co" : 1066.0
--"ct" : 518.0
--"de" : 114.0
--"fl" : 2558.0
--"ga" : 866.0
--"hi" : 214.0
--"ia" : 438.0
--"id" : 333.0
--"il" : 1860.0
--"in" : 929.0
--"ks" : 442.0
--"ky" : 580.0
--"la" : 382.0
--"ma" : 781.0
--"md" : 533.0
--"me" : 347.0
--"mi" : 1370.0
--"mn" : 669.0
--"mo" : 1054.0
--"ms" : 258.0
--"mt" : 312.0
--"nc" : 1076.0
--"nd" : 96.0
--"ne" : 280.0
--"nh" : 304.0
--"nj" : 986.0
--"nm" : 565.0
--"nv" : 655.0
--"ny" : 2060.0
--"oh" : 1455.0
--"ok" : 483.0
--"or" : 1303.0
--"pa" : 1578.0
--"ri" : 165.0
--"sc" : 518.0
--"sd" : 121.0
--"tn" : 796.0
--"tx" : 2657.0
--"ut" : 428.0
--"va" : 815.0
--"vt" : 156.0
--"wa" : 3126.0
--"wi" : 810.0
--"wv" : 294.0
--"wy" : 141.0

Klasa 'RMySQL' odpowiada za połączenie z bazą MySQL i operacje, wykonywane na nie, zawiera:
- metodę ładującą dane do bazy z pliku .json;
- metodę eksportującą do pliku .json;