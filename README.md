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
- metodę wykonywującą mapreduce na danych (zlicznie ile zdarzeń wystąpiło w każdym stanie US), kilka przykładowych wyników:
  - "ak" : 186.0
  - "al" : 424.0
  - "ar" : 458.0
  - "az" : 1976.0
  - "ca" : 6861.0
  - "co" : 1066.0


Klasa 'RMySQL' odpowiada za połączenie z bazą MySQL i operacje, wykonywane na nie, zawiera:
- metodę ładującą dane do bazy z pliku .json;
- metodę eksportującą do pliku .json;