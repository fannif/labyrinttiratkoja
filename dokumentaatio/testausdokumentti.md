## Testausdokumentti

Projektiin on kirjoitettu yksikkötestejä sekä testailtu toimintaa ja suoritusnopeuksia manuaalisesti graafisen käyttöliittymän kautta sekä automatisoidusti erillisen Tester-luokan avulla. Olen vertaillut nopeuksia, ja pohtinut mistä niiden erot johtuvat.

### Yksikkötestaus ja testaus käyttöliittymän kautta

Yksikkötestejä olen kirjoittanut projektiin alusta asti. Pyrkimyksenä on ollut pitää kaikkien sovelluslogiikan luokkien testikattavuus hyvänä. Olen pyrkinyt testaamaan erityisesti sitä, että luokkien ydintoiminnallisuus toimii niin, kuin sen minun käyttötarkoituksessani ja siinä ilmenevissä mahdollisissa tilanteissa pitäisikin. Esimerkiksi generointialgoritmeille olen aina rakentanut testit, jotka varmistavat, että generoitu labyrintti on mahdollista ratkaista, ja että seiniä on oikeissa paikoissa. Ratkaisualgoritmeille puolestaan olen aina testannut, että ne oikeasti pääsevät maaliin asti.

Main-luokkaa, joka sisältää käyttöliittymän, ja Tester-luokkaa, joka sisältää suorituskykytestit, ei olla testattu yksikkötesteillä. Tämä johtuu siitä, että niiden testaaminen ei kurssin kannalta olisi ollut oleellista.

Yksikkötestien ohella olen tehnyt alusta asti jatkuvaa manuaalista testausta graafisesta käyttöliittymästä. Siksi toteutinkin ohjelmaan heti graafisen käyttöliittymän. Olen tarkastellut sieltä, millaisia labyrintteja algoritmini luovat: ovatko ne järkevän näköisiä, tarpeeksi monimutkaisia ja ratkaistavissa. Siinä samalla olen myös heti huomannut, jos seiniä on jostain syystä väärässä paikassa. Ratkaisualgoritmien yhteydessä olen pystynyt graafisesta käyttöliittymästä tarkastelemaan niiden reittejä. Olen erikokoisilla sokkeloilla valmistanut, että ratkaisupolku menee maaliin asti ja on yhtenäinen, eikä mene seinien läpi.

Myöhemmin lisäsin projektiini myös mahdollisuuden tarkastella algoritmien suoritusaikoja käyttöliittymästä. Sen jälkeen olenkin tarkkaillut myös nopeutta algoritmeja toteuttaessani ja muokatessani. Olenkin testaillut generoida labyrintteja eri algoritmeilla ja tarkastellut niiden suoritusaikoja. Vastaavasti olen tarkastellut ratkaisualgoritmien suoritusaikoja, ja siten saanut käsitystä niiden nopeuksien suhteista sekä siitä, millaisten labyrinttien ratkaisussa kukin niistä on hyvä. Tästä olen saanut paremman käsityksen algoritmien heikkouksista ja olen päässyt pohtimaan, miten niitä voisi mahdollisesti parantaa.

Aluksi käytin yleensä syötteenä vertailussa 51x51-kokoista labyrinttia. Se on tarpeeksi iso, että muodostetuissa labyrinteissa, reiteissä ja suoritusajoissa näkyy jo algoritmikohtaisia eroja ja ominaispiirteitä, mutta ei kuitenkaan niin iso, että sen mahtumisessa näytölle tai käsittelyssä olisi ongelmia. Algoritmeja toteuttaessa ja kehittäessä olen usein aluksi käyttänyt ihan pieniä labyrinttejä, kuten 15x15-kokoisia. Näin minun on ollut helpompi simuloida itse päässä algoritmin toimintaa tällaisessa labyrintissa ja löytää syitä virhetilanteille. Kun olen sitten tällä tavalla testaamalla saanut virheitä karsittua pois, niin olen edennyt isompaan labyrinttiin. Pienillä labyrinteilla oli hyvä kehitysvaiheessa huomata, mitä yhteistä virheisiin johtavilla labyrinteilla oli, ja siten päätellä, mistä ongelma voisi johtua.

Manuaalisessa suorituskykytestauksessa huomasin, että käyttöliittymä oli ohjelman hitain elementti. Labyrintin kuvan generoimiseen menee itse algoritmien ajamiseen suhteessa todella paljon aikaa. Tämä teki isojen labyrinttien käsittelystä käyttöliittymän kautta kankeaa. Lisäsin siis käyttöliittymään mahdollisuuden käsitellä isompia labyrintteja ilman, että niistä näytetään kuvaa, jolloin toiminta nopeutui huomattavasti. Tätä ominaisuutta hyödyntäen on tehty suorituskykytestausta generoimalla ja ratkaisemalla labyrintteja vuorotellen eri algoritmilla. Testauksessa kävi ilmi, että algoritmit ovat melko tasanopeuksisia. Kun käsitellään 213*213-kokoista sokkeloa, niin Kruskalin algoritmilla menee noin 20 millisekuntia sekuntia, kun taas Sidewinderilla menee noin 30 millisekuntia ja rekusriivisella jakoalgoritmilla yleensä reilusti alle kymmenen millisekuntia.

Käyttöliittymätestailussa ratkaisualgoritmeista Wall Follower on nopein. Sillä menee isossakin labyrintissa yleensä alle 3 millisekuntia. Lyhyimpien polkujen algoritmilla menee yleensä alle 10 millisekuntia. Ratkaisualgoritmeista hitain on ketjualgoritmi, jolla menee saman labyrintin ratkaisemiseen yleensä 5 - 10 kertaa kauemmin kuin lyhyimpien polkujen algoritmilla. Testauksessa kävi myös ilmi, että ratkaisijoista Wall Followerin löytämät reitit ovat yleensä pisimpiä ja lyhyimpien polkujen algoritmin löytämät selvästi lyhyimpiä.

Tekemäni yksikkötestit on helppo toistaa. Ne voi ajaa terminaalista, jolloin niistä saa myös raportin. Raportin voi generoida lataamalla projektin GitHubista ja menemällä sitten ladattuun kansioon LabyrinthSolver-hakemiston sisälle. Raportin generoimiseksi suoritetaan hakemiston sisällä ollessa Linuxilla komento ’mvn test jacoco:report’. Raportti kertoo testien rivi- ja haaraumakattavuuden. Kattavuusraportti löytyy tiedostosta target/site/jacoco/index.html. Testien ajaminen ilman raportin generointia onnistuu samasta kansiosta komentorivikomennolla 'mvn test'.

Tekemäni manuaalisen testauksen voi toistaa graafisen käyttöliittymän kautta. Tämä tapahtuu käynnistämällä ohjelma, ja sitten klikkailemalla vuorotellen eri generointi- ja ratkaisumenetelmien nappeja isojen ja pienien labyrinttien kohdalla, jolloin algoritmit suoritetaan. Tässä kannattaa ottaa huomioon, että ensimmäisillä suorituskerroksilla (varsinkin ihan ensmmäisellä napinpainalluksella) algoritmit toimivat paljon hitaammin kuin normaalisti. Kun nappeja painelee pari kertaa, niin saa oikeasti vertailukelpoista testidataa. Isoa labyrinttia tutkiessa ja Kruskalia käytettäesse joutuu odottamaan, koska se on hidas.

### Varsinainen suorituskykytestaus

Kuudennella viikolla lisäsin projektiin erillisen Tester-luokan isompien suorituskykytestien ajamista varten. Suorituskykytestit tehdään Tester-luokassa generointialgoritmeille niin, että kullakin algoritmilla generoidaan kokojen 9x9, 51x51, 99x99, 501x501 ja 999x999 taulukot. Kutakin kokoa generoidaan sata kappaletta. Sitten tulostetaan kullekin algoritmille kunkin kokoisen taulukon generointiajan keskiarvo ja mediaani. Tässä on hyvä huomata, että esimerkiksi kokoa 999x999 oleva sokkelo kuvataan yksiulotteisena taulukkona, jossa on lähes miljoona alkiota. Niinpä tämänkokoiset taulukot alkavat olla jo aika isoja.

Ratkaisualgoritmit puolestaan testataan niin, että niille generoidaan rekursiivisella jakoalgoritmilla kokojen 9x9, 51x51, 99x99, 501x501 ja 999x999 taulukot, joita kutakin kukin algoritmi sitten ratkaisee 100 kertaa. Näistä ratkaisuajoista tulostetaan kullekin algoritmille kunkin kokoisen taulukon ratkaisuajan keskiarvo ja mediaani.

Algoritmien ratkaisuajoista on otettu sekä keskiarvo että mediaani, jotta saadaan parempi kuva algoritmien oikeista nopeuksista. Mediaanillä pyritään vähentämään esimerkiksi suoritusaikana tapahtuvan kääntämisen ja roskienkeräämisen vaikutusta. Keskiarvoon vertaamalla nähdään suunnilleen, jos saatu mediaani on jostain syystä ihan väärää kokoluokkaa.

Alla on testaamalla saamiani nopeustuloksia. Tulokset on saatu suhteellisen hitaalla koneella, jossa on Intel® Pentium(R) CPU N4200 @ 1.10GHz -ytimet ja 4 GB RAMia.

#### Ratkaisualgoritmit

Ratkaisualgoritmien mediaaniajat, kun sokkeloiden koot ovat 9 * 9,	51 * 51,	99 * 99,	501 * 501 ja	999 * 999 tässä järjestyksessä:

Wall follower:	29053 ns,	58149 ns,	269508 ns,	2040532 ns ja	9741090 ns

Shortest paths:	517829 ns,	607517 ns,	798100 ns,	11227555 ns ja	51536876 ns

Chain solver:	26500 ns,	222668 ns, 5153623 ns,	238416019 ns ja	2082421393 ns


Ratkaisualgoritmeista hitain on siis pienillä sokkeloilla lyhyimpien polkujen algoritmi. Tämä ei ole yllättävää, sillä algoritmille ei riitä pelkästään löytää ratkaisua, vaan se löytää nimenomaan lyhyimmät ratkaisut. Kuitenkin isompia sokkeloita käsitellessä ketjualgoritmi on selvästi hitain. Tämä oli osin yllättävää, sillä lyhyimpien polkujen algoritmien voisi olettaa olevan hitaampi, kun ketjualgoritmi optimoi reittiä huomattavasti vähemmän. Ketjualgoritmin hitaus kuitenkin varmaan johtuu siitä, että aina kun se kohtaa halkaisijalla edetessää seinän, niin se joutuu lähtemään kiertämään sitä kahdesta suunnasta, jolloin se voi pahimmillaan kiertää joka törmäyksellä koko sokkelon kahdesti. Seinänseuraamisalgoritmi on käytännössä aina nopein. Sillä on etuna, että algoritmi on hyvin kevyt ja yksinkertainen, koska sen tarvitsee aina vain seurata seinää. Tämä järjestys oli pääteltävissä myös algoritmien aikavaativuuksista. Ketjualgoritmin pahimman tilanteen aikavaativuus on O(n^3), kun n on sokkelon kyljen pituus. Tämä on kovin hidas verrattuna kahteen muuhun algoritmiin, joista Sidewinderin pahimman tapauksen aikavaativuus on O(n^2) ja lyhyimpien polkujen algoritmin O(n^2 + m). Siispä ainakin isoilla sokkeloilla algoritmien tehokkuusjärjestys on juuri se, mitä aikavaativuuksien perusteella olisi voinut ennustaa.

#### Generointialgoritmit

Generointialgoritmien mediaaniajat, kun sokkeloiden koot ovat 9 * 9,	51 * 51,	99 * 99,	501 * 501 ja	999 * 999 tässä järjestyksessä:

Sidewinder:	99930 ns,	1483285 ns,	4283031 ns,	115621703 ns ja	415608093 ns

Recursive division:	17259 ns,	87403 ns,	346221 ns,	5817108 ns ja	24447469 ns

Kruskal:	52642 ns,	448011 ns,	2392368 ns,	132322882 ns ja	900749868 ns


Rekursiivinen jakoalgoritmi on tulosten perusteella ylivoimaisesti nopein kaikilla käsitellyillä sokkeloiden koilla. Kruskal puolestaan on isoilla taulukoilla hitain, kun taas Sidewinder on pienillä taulukoilla sitä hitaampi. Tämän pystyi ehkä päättelemäänkin jo algoritmien aikavaativuuksista. Kruskalin aikavaativuus on O(n^2logn),kun n on sokkelon kyljen pituus. Kahden muun generointialgoritmin aikavaativuudet ovat O(n^2). Niinpä isoilla taulukoilla, kun n on isompi, niin myös logn kasvaa, joten Kruskalilla on ikään kuin isompi kerroin, kuin Sidewinderilla. Pienellä koolla n tämä ero ei tule samalla tavalla näkyviin, koska myös logn on silloin pieni. Kruskal sisältää myös monta osaa ja sisäkkäisiä metodikutsuja, millä saattaa olla tekemistä sen hitauden kanssa. Rekursiivisen jakoalgoritmin saattoi olettaakin olevan nopein, koska se jakaa aina sokkeloa kahteen osaan, jolloin käsiteltävä alue aina pienenee. Vaikka siis rekursiivisen jakoalgoritmin pahimman tilanteen aikavaativuus on samaa luokkaa kuin Sidewinderin, niin oikeasti se ei kuitenkaan aina käytä niin paljoa aikaa, ja on isoilla sokkeloilla Sidewinderia tehokkaampi. Sidewinderia hidastaa erityisesti isommilla sokkeloilla se, että ilmeisesti PairSet-luokka, jota se käyttää, on melko hidas.


#### Kuvaaja:
Huomaa, että kuvaajassa aika on logaritmisella asteikolla.
![Nopeuskuvaaja](https://github.com/fannif/labyrinttiratkoja/blob/master/dokumentaatio/algoritmiNopeusKuvaaja.png "Nopeuskuvaaja")

Suorituskykytestit saa toistettua ohjelman graafisesta käyttöliittymästä. Kun ohjelman käynnistää, voi alkuvalikosta valita vaihtoehdon, jolla ajetaan suorituskykytestit. Tämä tehdään alkuvalikon kolmannesta napista. Varsinaiset testien tulokset eivät kuitenkaan tulostu graafiseen käyttöliittymään, vaan sen sijaan terminaaliin tai konsoliin, josta ohjelmaa ajetaan.
