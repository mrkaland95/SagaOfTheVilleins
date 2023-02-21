# INF112 Project - Saga of the villeins

Team: Saga of the villeins (Gruppe 6-2)
Medlemmer: Leon Dammann, Markus Sylta, Malene Wathne og Steffen Arnesen Kaland
https://git.app.uib.no/SagaOfTheVilleins/sagaofthevilleins

Om spillet
En villein skal bekjempe seg fri fra å jobbe for overklassen. For å øke styrken og ferdighetene, samt bedre rustningen sin må han bekjempe en rekke fiender med økende vanskelighetsgrad.

### Spillkontroller:
* Flytt karateren rundt med høyre museknapp
* Man kan flytte kamera ved å trykke midt/skrolle knappen, eller ved WASD.
* Man can zoome inn/ut ved å skrolle på musen, eller ved Z/X


### Kjøring 
* Kompileres med "mvn package".
* Kjøres med java -jar target/SagaOfTheVilleins-1.0-SNAPSHOT-fat.jar
* Krever Java 17 eller senere

### Kjente feil
* Foreløpeling går det ann å flytte kameraet ved å holde inn enn knapp på musen og deretter scrolle.
Derimot er ikke denne helt 1-til-1 med tanke på posisjon for øyeblikket.

* Spriten blir rendret ned fra venstre hjørnet, heller enn fra midten av "Spriten".

* For øyeblikket må du sørge for at "assets" mappen ligger i samme rotmappe som den kompilerte pakken,
  for at programmet skal kunne kjøres


### Credits for "assetene" som er brukt

LibGDX

Markus - Laget tiles og kart

Leon - Laget "Spritene" og animasjonene.
