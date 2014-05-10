FedraDawIndex
=============

Computing DAW Index for Fedra

USAGE: nt-file, url, and max permutation allowed (otherwise 10% of size of idset)
```
% java -Xms3048m -Xmx3048m -cp FedraDawIndex-1.0-SNAPSHOT.jar DAW.Main ../src/test/resources/testAverage.nt http://www.google.fr 10 > out.daw
```

* Processing information are sent to stderr
* DAW Index are sent to stdout

Exemple of output:
```
momonet-2:target pascal$ cat out.daw 
@prefix sd: <http://www.w3.org/ns/sparql-service-description#> .
[] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> sd:Service ;
sd:endpointUrl <"http://www.google.fr"> " ;
  sd:capability [
      sd:predicate <<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>> ;
      sd:totalTriples   8174838 ;
      sd:avgSbjSel     "0" ;
      sd:avgObjSel     "0" ;
      sd:MIPs   "[-991205809, -991205125, -991205951, -991205954, -991205385, -991205777, -991205663, -991205863, -991205808, -991205764, -991205909, -991205778, -991205537, -991205888, -991204896, -991205338, -991205891, -991205826, -991205954, -991204323, -991205944, -991205847, -991205963, -991205921, -991205337, -991205955, -991205877, -991205808, -991205534, -991205973, -991205957, -991205813, -991205844, -991205889, -991205701, -991204920, -991205694, -991205967, -991205248, -991205773, -991205107, -991205772, -991205924, -991205890, -991205516, -991205769, -991205337, -991204787, -991205427, -991205290, -991205658, -991205921, -991205432, -991205960, -991205290, -991205270, -991205865, -991205538, -991205673, -991205631, -991205949, -991205963, -991205791, -991205889, -991205893, -991205642, -991205930, -991205490, -991205749, -991205890, -991205325, -991205705, -991205843, -991205239, -991205662, -991205628, -991205855, -991205978, -991205877, 
```


