FedraDawIndex
=============

Computing DAW Index for Fedra

USAGE: nt-file, url, and max permutation allowed (otherwise 10% of size of idset)

java -Xms3048m -Xmx3048m -cp FedraDawIndex-1.0-SNAPSHOT.jar DAW.Main ../src/test/resources/testAverage.nt http://www.google.fr 10
Building mip for:<http://dbpedia.org/property/wordnet_type>nb Id:100nb perm:10
processing perm:0 on 10
@prefix sd: <http://www.w3.org/ns/sparql-service-description#> .
[] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> sd:Service ;
sd:endpointUrl <"http://www.google.fr"> " ;
  sd:capability [
      sd:predicate <<http://dbpedia.org/property/wordnet_type>> ;
      sd:totalTriples   100 ;
      sd:avgSbjSel     "0.01" ;
      sd:avgObjSel     "0.5" ;
      sd:MIPs   "[-966999571, -973019646, -931351625, -922257806, -955322096, -987532292, -971795231, -972396212, -960402528, -985807572]" ; ] 


Processing information are sent to stderr
final DAW index are send to stdout
