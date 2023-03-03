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
	* Plattform bestående av tiles som spillfiguren kan stå og bevege seg på
* Turn-based interaksjon med fiende
* Spilleren kan øke i «skill» ved å bekjempe fiender av stadig vanskeligere grad
* Spilleren kan, potensielt, øke inventory kapasitet ved å plukke med seg gjenstander
* Spilleren følger et map som består av strategiske valg i form av spill med fiende, samt andre quests
* Målet med spillet er å bekjempe alle de ulike fiendene, plukke med seg gjenstander, forflytte seg til enden på   in-between-games-map og bedre skill/karma.

Spill som illustrer konseptet: roguelike, civilization 5 og 6 og xcom.


A3 – Velge og tilpasse en prosjektmetodikk for teamet

Når det kommer til prosjektmetodikk, har teamet blitt enige om følgende;

* Parprogrammering er en metode som fungerer bra for å involvere medlem med svakere programmeringsferdigheter. Dette er derfor en metode vi har valgt å inkludere, men med et begrenset omfang.

* Kanban er en oversiktlig metodikk hvor vi alle får innsikt i hva som foregår og hva som trengs. Bruker derav en digital kanban som kan modifiseres fortløpende.



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
1. Som spiller trenger en å forstå hvilket type spill det er ved å se på det for å kunne avgjøre interesse
2. Som spiller trenger en å intuivit forstå hvilken spillfigur det er mulig å styre for å kunne iteragere i spillet
3. Som spiller trenger en å forstå hvor det er mulig å forflytte spillfiguren, samt hvor det ikke er det, for å kunne interagere i spillet
4. Som spiller trenger en å vite hvordan en forflytter spillfiguren for å kunne interagere i spillet
5. Som spiller trenger en å vite hvordan en styrer kameraet, slik at en kan orientere seg på skjermen
6. Som spiller trenger en å bli presentert egen og motstander(nes) health slik at en har en oversikt over spillets gang og kan ta strategiske valg videre
7. Som spiller trenger en å bli presentert for konsekvensen av ulike handlinger, slik at en skal kunne ta strategiske valg
8. Som spiller trenger en å kunne avslutte spillet og lukke det, slik at en ikke blir låst inne


Programmerers perpektiv
1. Som programmerer trenger en å vite plasseringen til spillfiguren slik at en kan avgjøre hvilke handlinger som er gjennomførbare
2. Som programmerer trenger en lett leslig kode for å kunne forstå inneholdet, spesielt også ved behov for små justeringer og ytterligere endringer
3. Som programmerer trenger en å ta i bruk objektorienterte prinsipper for å oppnå god kode og struktur
4. Som programmerer trenger en å ha et systematisk system i form av koordinater eller lignende for å kunne avgjøre posisjonering av figurer i henhold til tiles og kartet mer generelt
5. Som programmerer trenger en at parameterene som påvirkes på tvers av karakterene lagres ned slik at handlingene forblir avhengige av hverandre
6. Som programmerer trenger en at mapper og filer har intuitive navn og plasseringer slik at en kan få oversikt, samt orientere seg til ønsket sted
7. Som programmerer trenger en en liste som holder kontroll på hvilken karakter som skal handle, for at riktig karakter skal handle på riktig tidspunkt



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
* Foreløpig ujevn arbeidsmengde gjennomført av de ulike medlemmene, av ulike årsaker


Som tiltak for å bedre status til neste gang ønsker vi å implimentere følgende;
* Aktiv bruk av timelister
* Aktiv bruk av digital Kanban
* Følge møteplan strukturert og systematisk

Ting som har fungert under middels har vært basert på typisk oppstartsfase faktorer, samt gjennomføringsevne.  