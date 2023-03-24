A1 – Roller

Leon – grafisk designer og algoritmeansvarlig
* På bakgrunn av fritidsinteresser og erfaring som innebærer grafikk, var grafisk designer et godt valg.

Malene – prosjektleder og testansvarlig
* På bakgrunn av lite programmeringsferdigheter, men mye prosjektutførelse, samt personlig kvalitet; svært strukturert, var dette en rolle som falt naturlig. I tillegg til dette kan det tenkes at testansvarlig kan være aktuelt, da det vil være overkommelig å fokusere på noe avgrenset og lære det godt.

Markus - developer
* Rollen ble git basert på historikk som viser til at medlemmet skaper god kode hyppig.

Steffen – techlead (git ansvarlig, integreringsavsnarlig)
* Rollen blir gitt på bakgrunn av mest erfaring i git.


A2 – røff beskrivelse av hva som inngår i spillet


* Spillfigur som kan gå frem, tilbake og diagonalt  
* Todimensjonal verden  
	* Plattform bestående av hexagon tiles som spillfiguren kan stå og bevege seg på   
* Turn-based interaksjon med fiende  
* Spilleren kan øke i «skill» ved å bekjempe fiender av stadig vanskeligere grad  
* Spilleren kan plukke med seg gjenstander  
* Spilleren følger et map som består av strategiske valg i form av spill med fiende, og impassable terreng 
* Målet med spillet er å bekjempe alle de ulike fiendene, plukke med seg gjenstander, forflytte seg til enden på   in-between-games-map og bedre skill/karma.  

Spill som illustrer konseptet: roguelike, civilization 5 og 6 og xcom.


A3 – Velge og tilpasse en prosjektmetodikk for teamet

Når det kommer til prosjektmetodikk, har teamet blitt enige om følgende;

* Parprogrammering er en metode som fungerer bra for å involvere medlem med svakere programmeringsferdigheter. Dette er derfor en metode vi har valgt å inkludere, men med et begrenset omfang.

* Extreme Programming er en metodikk som ligger nært slik vi arbeider naturlig og gir god fleksibilitet.



Organisering av møter/arbeidsøkter

Ved møter ønsker vi følgende struktur, med modifikasjoner tilpasset agenda;

* Første punkt for møtet er en kort orientering fra alle medlemmene over hva en har gjort fra forrige gang, tanker en har kommet på underveis og hva en tenker om veien videre.

* Neste punkt er dagens agenda. Da vil det variere om vi har satt en agenda på forhånd som skal følges, følges med modifikasjoner eller at en agenda blir diskutert og satt der og da.

* Tredje punkt er arbeid/diskusjoner. Da prioriteres arbeid som krever at vi gjør det i felleskap, i form av avgjørelser og utførelser. Deretter følger arbeid som er praktisk å utføre med de andre medlemmene lett tilgjengelig og til slutt kommer resterende arbeid.

* Fjerde punkt vil bestå av en oppsummering i enden av møtet/arbeidsøkten hvor en tar en lett gjennomgang på hva de ulike medlemmene skal jobbe med frem mot neste gang, samt agendaen for neste møte.


Hyppighet

Faste møter og arbeidsøkter:
* Torsdager 08:15 - 10:00
* Fredager 10:15 - 14:00


Kommunikasjon mellom møter
* All kommunikasjon mellom medlemmer foregår på Discord i relevant text channel


Arbeidsfordeling
* Da alle medlemmene har ulik bakgrunn, har det frem til nå vært intuitivt å bestemme hvem som skal ha hvilke arbeidsoppgaver. Kartleggingen av medlemmenes ferdigheter har også bidratt til at vi enkelt og raskt kan få en ide om hvem som kan utføre hva. Har tatt i bruk en digital Kanban hvor arbeidsoppgaver også blir reservert, gjennomført og  forflyttet i dokumentet.


Oppfølging av arbeid
* Oppfølging av arbeid foregår i ulike ledd
	* Oppfølging på møter fast hver uke
	* Oppfølging i løpet av arbeidsøkter
	* Oppfølging i Discord fortløpende mellom møter/arbeidsøkter
	* Oppfølging ved oppdateringer i git
	* Digital kanban fil
	* TODO tags i terminalen
	* Timelister og prosjektlog


Deling og oppbevaring av felles data
* Har opprettet en onenote notatbok hvor alle har tilgang og kan skrive ned ideer, kommentarer og lignende.
* Har opprettet et google sheet for logføring av timer og oppgaver
* Koden oppbevares i develop brach i gitlab


A3 - oversikt over forventet produkt


Spesifikasjon
* Det overordnede målet for applikasjonen er å bekjempe en rekke fiender for å komme til enden av mapet og bli "fri".


Krav til MVP
* Vise et tile-basert spillbrett
* Vise en spillfigur på spillbrettet
* Bevege spillfiguren i henhold til spillbrettet
* (Kunne kontrollere kameraposisjon)
* Fiende med simpel ai som kan lese posisjonen til spilleren og interagere basert på det
* Karakterene skal kunne interagere og påføre skade som fører til død
* Visuell fremvisning av antall liv per spillfigur
* (Visuell fremvisning av innholdet på hver tile)
* Bekjempelse av fiende etterlater en gjenstand som kan plukkes opp og føre til økt ferdighet
* Lydeffekter ved interaksjon mellom spillfigur og fiende
* Etter bekjempelse genereres det et nytt spill hvor økt ferdighet er beholdt og ny fiende oppstår
* Spillbrettet inneholder en barriere hvor spilleren ikke kan befinne seg
* Spillet skal inneholde en forside hvor en kan navigere seg videre til spillet
* Spillet skal inneholde en hjelpeside som formidler hvordan spillet fungerer
* Opprette enheter i spillet vha. objektfabrikker
* Koden skal inneholde tester med minimum 75% coverage
* Automatiske tester skal kunne kjøres uten interaksjon


Brukerhistorier

Spillers perspektiv
1. Som spiller ønsker jeg å forstå hvilket type spill det er (ved å se på det), slik at jeg kan avgjøre interesse
2. Som spiller ønsker jeg å intuivit forstå hvilken spillfigur det er mulig å styre, slik at jeg kan iteragere i spillet
3. Som spiller ønsker jeg å forstå hvor det er mulig å forflytte spillfiguren, samt hvor det ikke er det, slik at jeg lykkes med å interagere i spillet
4. Som spiller ønsker jeg å vite hvordan jeg forflytter spillfiguren, slik at jeg kan interagere i spillet
5. Som spiller ønsker jeg å vite hvordan en styrer kameraet, slik at jeg kan orientere meg på skjermen
6. Som spiller ønsker jeg å bli presentert egen og motstander(nes) health, slik at jeg har en oversikt over spillets gang og kan ta strategiske valg videre
7. Som spiller ønsker jeg å bli presentert for konsekvensen av ulike handlinger, slik at jeg skal kunne ta strategiske valg
8. Som spiller ønsker jeg å kunne avslutte spillet og lukke det, slik at jeg ikke blir låst inne
9. Som spiller ønsker jeg å kunne velge når spillet starter, slik at jeg er forberedt.
10. Som spiller ønsker jeg å kunne vite spillereglene før spillet starter, slik at jeg kan ta informerte valg

Løsningsbeskrivelse


Akseptasekriterier
Gitt at , så skal 
1. 


A5 - oppsummering

Frem til nå har følgende gått bra, middels og under middels:

Bra
* Kommunikasjon mellom møter
* Oppmøte på avtalte tidspunkt
* Bistand mellom medlemmer ved tekniske problemer
* Diskusjonsprosesser i henhold til spillet, samt å ta valg deretter
* Opparbeidet god mengde kode med ryddig struktur
* Hyppig pushing til git


Middels
* Holde oversikt over hva alle medlemmene har gjort til nåværende tidspunkt
* Dokumentere ideer til et felles sted


Under middels
* Logføring av timearbeid per medlem utenom felles møtetider/arbeidsøkter
* Fordeling av varierte arbeidsoppgaver


Som tiltak for å bedre status til neste gang ønsker vi å implimentere følgende;
* Aktiv bruk av timelister
* Følge møteplan strukturert og systematisk

Ting som har fungert under middels har vært basert på typisk oppstartsfase faktorer, samt gjennomføringsevne.  