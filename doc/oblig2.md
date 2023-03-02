PROSJEKTRAPPORT

ROSJEKTRAPPORT

* Hvordan rollene fungerer i teamet

  Rollene i



* Trenger vi andre roller?

  Per dags dato ser det ikke ut til at vi trenger flere roller. Dagens fordeling har ikke vist til gliper i dekningsevne av prosjektet frem til nå.



* Liste over hva de ulike rollene innebærer



    * Prosjektleder  

        - Opprette prosjektodokumenter som fremdriftsplan, timelister, referat osv. 

        - Føre prosjektlog 

        - Følge opp timelister 

        - Skrive prosessdokumentasjon i henhold til innleveringer 

        - Følge opp arbeidsoppgaver  

        - Skape oversikt over prosjektet fortløpende 

        - Planlegging av møtetider 

        - Sørge for kommunikasjon innad i gruppen 



    * Developer 

        - Produsere kode 

        - Ha oversikt over helheten i kode fortløpende 

        - Følge regler og prinsipper iht. kode 

     

    * Techlead 

        - Hovedansvar for git relaterte oppgaver (integreringsansvarlig) 

        - Komme med forslag til gitflow workflow 

        - Organisere og sette opp workflow 

        - Assistere team-medlemmer iht. git 

     

    * Grafisk designer og algoritmeansvarlig 

        - Lage grafikk og animasjon 

        - Få grafikk til å samhandle 

        - Lage algoritmer 



* Synes teamet at valgene vi har tatt er gode?

  Teamet fremstår samkjørte og enige om valgene som har blitt tatt. Det har enda ikke oppstått konflikt ved avgjørelser iht. spillet og veien videre.



* Hvordan har de ulike metodikkene fungert?

  Det ble brukt parprogrammering for å skrive tester for metodene til Player. Dette bidro til bratt læringskurve for gruppemedlem nr. 1 og god pedagogikk for gruppemedlem nr. 2.



    Når det kommer til anvendelse av den digitale kanbanen fant medlemmene det noe tungvindt og krunglete. Et par av medlemmene kom heller aldri helt i gang med å bruke det. Det å dra inn i dokumentet, skrive ned, flytte fortløpende og fargekode ble delvis overfladig, da vi har andre funksjoner som dekker samme behov. Da arbeidsoppgaver flyter naturlig mellom medlemmer, TODO tags innad i koden indikerer deler av hva som bør gjøres, kjente bugs blir notert i README filen og oversikt over løste problemer blir lagt inn i "dette har vi fikset siden sist" listen.  



* Hvordan er gruppedynamikken? Problemer som må løses?

* Hvordan fungerer kommuniksasjon?



* Hva har vi gjort klart til nå og hva må forbedres? (Prosjektstruktur, ikke kode)

  Forberingspotensialet ligger blant annet i å sørge for kommunikasjon fra hvert medlem til alle i fellesskap om nøyaktig hva de driver med og tenker om veien videre. Små tendenser til at alle holder på med sitt, men er ikke sikkert det krever stort til forbedring, da det forsåvidt fungerer greit. Det blir i større grad viktig at hvert medlem går inn og sjekker de ulike filene for å oppdatere seg selv. Etterhvert som prosjektet går på frem kan det muligens være lurt at hvert medlem går gjennom koden sin og presenterer den for de resterende medlemmene i gruppen slik at alle har oversikt og forståelse.



* Kort forklaring på hvorfor det er ulik balanse i hvem som comitter kode

  Som nevnt i orientering av medlemmenes ferdigheter er det stort sprik i tidligere erfaring i henhold til kode, men også spill. Ett av gruppemedlemmene har kun INF100 som bakgrunn, og lærer java fortløpende i prosjektet. Det vil derav være naturlig at vedkommende ikke produserer like mye kode, men forsøker etter evne og forhåpentligvis kan bidra mer og mer etterhvert. Gruppemedlemmet har prosjektlederrolle, som gjør at det er mange andre oppgaver forutenom kode som også skal gjennomføres. Frem til nå har de andre gruppemedlemmene vært svært hjelpsomme og viser stor grad av toleranse og pedagogisk evne ved assistanse.



    Det kan også nevnes at samme gruppemedlem har hatt oppstartsproblemer med Port22 feil ut februar måned, og derav hatt store problemer med å committe til git. Problemet ble løst i samarbeid med gruppemedlemmer, gruppeleder og foreleser.  



* Referater fra møter



* Enighet om tre forbedringspunkter fra retrospektivet



KRAV OG SPESIFIKASJON



* Hvilke krav har vi prioritert? Hvor langt har vi kommet og hva har vi gjort siden forrige gang? Forklare hvordan vi prioriterer ny funkjsonalitet.

  Ved oppstart av prosjektet gjennomførte vi en "brainstorming" for å kartlegge hva de ulike medlemmene tenkte måtte/ønsket at skulle inngå i spillet. De ulike punktene ble så kategorisert vha. tall fra 1-5 i prioritert rekkefølge. Dette førte til at vi fikk god oversikt over hva som skal prioriteres først, og hvilke vi skal bygge på etterhvert som de andre punktene kommer på plass.



    Ved prioritering ble det satt fokus på å sørge for en grunnmur som var lett å bygge på. Ved å opprette rikig rekkefølge, sørger vi for å være sikre på at det viktigste kommer med, samtidig som vi kan bygge videre så langt det rekker, helt til siste tidsfrist.  



Grov liste som resultat av brainstorm

- Angreknapp (3)

- End turn (2)

- Flere karakterer (5)

- Klikke bevegelse (1)

- Høyde (3)

- Karma system (3)

- Inventory system (2)

- Consumables (4)

- Hjelpeside (1)

- Startside (1)

- Karakterer player og fiende (1)

- Impassable terrain (4)

- Items in map (4)

- Shop (5)

- Character info ved å klikke på tile (2)

- Lifebars (2)

- Zoome funksjon (3)

- Path map (4)

- Turnbased (1)

- Skill tree (3)

- Buffs/debuffs (3)



* Sette opp for hvert krav

    * brukerhistorier

    * akseptansekriterier (i form av tester)

    * arbeidsoppgaver

* Har vi gjort justeringer i kravene til MVP? eventuelt hvorfor?

* Oppgi hvilke bugs som finnes



PRODUKT OG KODE



* Liste over "dette har vi fikset siden sist"

    * At det nå går det ann å flytte kameraet ved å holde inn enn knapp på musen og deretter scrolle.

    * At spillfiguren kun er i bevegelse som en respons av klikk, ikke kontinuerlig som tidligere.

    * Gått fra at spillfiguren flytter seg fra tærne til klikket punkt, til at spillfiguren sentreres over klikket punkt.

    * Fikset at kameraposisjonen ikke lengre kan reise gjennom spillet (gå bak grafikken og vri den).

    * Fikset at player tar inn nødvendige parametere

    * Fikset sentrering av spillfigur på tiles

    * Fikset at kameraet er en-til-en ved fordlyttning



* Klassediagram









