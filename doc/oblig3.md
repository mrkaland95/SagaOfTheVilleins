PROSJEKTRAPPORT


* Hvordan rollene fungerer i teamet


* Trenger vi andre roller? 


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
    

* Hvordan har de ulike metodikkene fungert?
    
 
* Hvordan er gruppedynamikken? Problemer som må løses?
 

* Hvordan fungerer kommuniksasjon?
    

* Hva har vi klart til nå og hva må forbedres? (Prosjektstruktur, ikke kode)
   

* Kort forklaring på hvorfor det er ulik balanse i hvem som commiter kode
    Som nevnt i orientering av medlemmenes ferdigheter er det stort sprik i tidligere erfaring i henhold til kode, men også spill. Ett av gruppemedlemmene har kun INF100 som bakgrunn, og lærer java fortløpende i prosjektet. Det vil derav være naturlig at vedkommende ikke produserer like mye kode, men forsøker etter evne og forhåpentligvis kan bidra mer og mer etterhvert. Gruppemedlemmet har prosjektlederrolle, som gjør at det er mange andre oppgaver forutenom kode som også skal gjennomføres. Frem til nå har de andre gruppemedlemmene vært svært hjelpsomme og viser stor grad av toleranse og pedagogisk evne ved assistanse. 

    Det kan også nevnes at samme gruppemedlem har hatt oppstartsproblemer med Port22 feil ut februar måned, og derav hatt store problemer med å committe til git. Problemet ble løst i samarbeid med gruppemedlemmer, gruppeleder og foreleser. 

    Forutenom prosjektleder, er det også noe ubalanse blant de andre medlemmene. Dette kan begrunnes med at techlead har måttet klippe og lime en del ved oppståtte branch conflicts, og derav endt opp med å både ha git blame på mye kode, samt flest commits. Dette er anses også naturlig iom. at techlead har hovedavsvar for git og er best på det.


* Referater fra møter
    Disse kan lokaliseres i egen fil; Porsjektlog.md.

* Enighet om forbedringspunkter fra retrospektivet
   


KRAV OG SPESIFIKASJON

* Hvilke krav vi har prioritert og hva vi har gjort siden sist:


Per dags dato har vi:
* En spillfigur med animasjon som kan bevege vertikalt, horisontalt og diagonalt. 
* Et tile-basert map med koordinater som spillfiguren kan stå og bevege seg på.
* Et turn-system som separerer spillere og fiender/monstere, og stopper spilleren fra å gjøre endringer når det ikke er deres tur.
* En godt optimisert pathfinding funksjon for hexgridet vårt som funker raskt og alltid gir en optimal path. Denne kan også utvides for å kunne unngå tiles som ikke er lovlig å gå på uten endringer til det vi allerede har gjort, bare legge til litt ekstra.
* Gode tester for nesten alt til nå som kan testes med junit-tests. 
* En synlig healthbar som følger spillfigur
* Turnbased funskjon

* Sette opp for hvert krav
    * brukerhistorier (finnes i oblig 1 filen)
    * akseptansekriterier
    * arbeidsoppgaver (legges til etter hvert som de oppstår, i form av issues)

* Har vi gjort justeringer i kravene til MVP? eventuelt hvorfor?

* Oppgi hvilke bugs som finnes
    Disse er oppgitt i README filen.

PRODUKT OG KODE

* Liste over "dette har vi fikset siden sist"
    * Turnbased funksjon
    * Visudalisering av helathbar

* Klassediagram
