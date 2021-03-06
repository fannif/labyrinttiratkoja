## Toteutusdokumentti

Ohjelma koostuu käyttöliittymän sisältävästä Main-luokasta, sekä toiminnallisuuden sisältävästä domain-paketista. Domain-paketin sisälle on kahteen pakettiin toteutettu yhteensä kuusi erilaista isoa algoritmia sekä lisäksi niiden tarvitsemia tietorakenteita ja suorituskykytestausluokka. Algoritmit ovat labyrintin generointi- tai ratkaisualgoritmeja. Kumpiakin näistä on kolme kappaletta. Toteutettuja tietorakenteita ovat jono, joukko ja sokkelo. Lisäksi on toteutettu luokka MersenneTwister satunnaislukujen tuottamiseen ja suorituskykytestausluokka.

Tietorakenteista joukon operaatiot vievät aikaa, koska ne vaativat joukon läpikäymistä. Joukkoon lisätessä joudutaan pahimmassa tapauksessa kasvattamaan joukon kokoa, jolloin operaatio vie yhteensä luokkaa O(n) aikaa, missä n on joukon koko, sillä vanhan joukon alkiot joudutaan kopioimaan uuteen taulukkoon. Toisaalta, jos kokoa ei lisätessä tarvitse kasvattaa, niin operaation aikavaativuus on edelleen O(n), koska siinä suoritetaan contains-metodi, jolla käydään taulukko läpi ja varmistetaan, ettei samaa alkiota ole jo olemassa. Poisto-operaatio puolestaan on aikavaativuudeltaan yleensä luokkaa O(1), koska poiston yhteydessä tarvitsee vain vaihtaa viimeinen alkio poistettavan paikalle ja siirtää lopun indeksiä. Kuitenkin samalla saatetaan joutua myös pienentämään joukon kokoa, mikä vie O(n), missä n on joukon koko, koska joukon alkiot kopioidaan uuteen taulukkoon. Näiden lisäksi joukon tärkeisiin metodeihin kuuluu contains. Contains-metodi käy koko joukon läpi, joten sen aikavaativuus on O(n). Mikäli etsitty alkio on joukossa, se palauttaa alkion indeksin. Muulloin se palauttaa -1.

Jonon tärkeitä metodeita ovat enqueue ja dequeue. Enqueue ja dequeue vain hakevat arvon indeksin perusteella ja tekevät pari laskutoimitusta, joten niiden aikavaativuus on luokkaa O(1) eli vakio. Ne toimivat kehämäisellä rakenteella. Niinpä jonon maksimikokoa ei tarvitse koskaan muuttaa, mutta se tulee täyteen, jos sille aluksi annettu koko parametri on liian pieni. Tällöin sinne ei voida lisätä alkioita, ennen kuin sieltä poistetaan vähintään yksi alkio.

Tietorakenteista projektin kannalta oleellisin on sokkelo. Sen ainoat metodit, jotka eivät ole gettereitä tai settereitä, ovat initialize ja initializeWithWalls. Niitä ensimmäinen alustaa sokkelon, jossa on vain ulkoseinät, ja toinen sokkelon, jossa on kaikki mahdolliset seinät. Niissä molemmissa käydään läpi n^2 -kokoinen taulukko, joten niiden aikavaativuudet ovat O(n^2). Tässä n kuvaa labyrintin kyljen pituutta. Varsinainen taulukko on yksiulotteinen, joten sen koko on n^2.

Satunnaislukuja tuottavalla luokalla MersenneTwister on konstruktori ja kaksi tärkeää metodia. Konstruktorissa käydään kokoa 624 oleva taulukko läpi silmukassa, ja asetetaan sinne arvoja. Taulukon koko ei koskaan muutu, vaan on aina tämä sama 624. Jos nyt merkitään n = 624, niin konstruktorin aikavaativuus on siis luokkaa O(n). Luokan metodi newValues laskee taulukkoon uudet satunnaisluvut. Siinä käydään taulukko läpi kokonaisuudessaan kerran, kahteen silmukkaan jaettuna, eikä sisäkkäisiä silmukoita ole. Näin ollen metodin aikavaativuus on luokkaa O(n). Metodi nextInt palauttaa kutsujalle satunnaisluvun. Siinä on pelkkiä ehtolauseita ja bittitason laskuja. Toisessa ehtolauseessa tehdään joka 624. kerta metodin newValues kutsu. Koska sen aikavaativuus on O(n), ja muuten nextInt:in aikavaativuus on luokkaa O(1), niin pahimmassa tapauksessa metodin nextInt aikavaativuus on luokkaa O(n). Näin kuitenkin käy vain 624 kerran välein, ja muulloin metodin aikavaativuus on luokkaa O(1).

Ratkaisualgoritmeista ketjualgoritmin aikavaativuus on luokkaa O(n^3), missä n on ratkaistavan sokkelon rivin pituus. Algoritmissa joudutaan nimittäin käymään läpi silmukka, jossa tutkitaan rekursiolla pahimmillaan jokaisen taulukon läpäisijän koordinaattiparin kohdalla taulukko läpi. Niinpä läpäisijälle, jonka koko riippuu rivin pituudesta, käydään pahimmillaan kaikille kohdille koko kaksiulotteinen taulukko läpi. Tästä saadaan O(n^3).

WallFollower -ratkaisualgoritmi simuloi ihmistä, joka ratkaisee labyrintin kulkemalla sitä läpi vasen käsi kiinni seinässä. Sen aikavaativuus on luokkaa O(n^2) kahdesta syystä. Alussa siinä on nimittäin silmukka, joka kopio ratkaistavan sokkelon pohjan talteen uuteen taulukkoon. Koska kyseessä on n-kertaa-n -taulukko, vaikkakin se on kuvattu yksiulotteisena, niin tähän menee O(n^2). Vaikka tätä operaatiota ei olisi, niin aikavaativuus olisi kuitenkin sama. Nimittäin vaikka algorimtissa ei olekaan sisäkkäisiä silmukoita, niin pahimmillaan joudutaan kulkemaan lähes koko labyrintti läpi, ennen kuin maali tulee vastaan vasen käsi seinässä.

Lyhyimmät polut laskeva ratkaisualgorimti hyödyntää leveyshakua. Leveyshaun aikavaativuus on O(n^2 + m), missä n on rivin pituus ja m kaarien määrä. Tämä tulee siitä, että verkon solmujen määrä on kaksiulottaisessa taulukossa luokkaa n^2, koska jokainen taulukon alkio voisi periaatteessa olla solmu. Leveyshaku käy läpi pahimmillaan jokaisen solmun kaikki kaaret. Tässä n on jälleen kaksiulotteisen labyyrintin kyljen pituus, jolloin itse taulukko on yksiulotteinen kokoa n^2.

Generointialgoritmeista Kruskalin algoritmi muodostaa labyrintin aloittamalla sokkelosta, jossa on kaikki seinät. Sitten se poistaa seiniä yksitellen niin, että se yhdistää aina kaksi toisistaan aiemmin erillään ollutta kuljettavaa aluetta toisiinsa. Kruskalin algoritmi käy solmuja läpi ja yhdistää niitä UnionFind-rakennetta hyödyntäen. Pahimmillaan joudutaan käymään kaikki solmut läpi. Tähän menee n-kertaa-n -taulukoa kuvaavassa yksiulotteisessa taulukossa aikaa O(n^2). Sitten kullekin niistä vielä suoritetaan Union Find-operaatioita, jotka vievät aikaa logn, sillä ne perustuvat puurakenteeseen. Näin ollen aikavaativuus on luokkaa O(n^2*logn).

Rekursiivinen jakoalgoritmi luo labyrintin lähtien liikkeelle siitä, ettei labyrintissa ole sisällä yhtään seiniä. Se jakaa aina sokkelon kahteen osaan seinällä, johon se tekee yhden kulukväyläkolon. Sitten molemmat uudet alueet jaetaan samalla tavalla kahteen, ja sitten taas tehdään sama saaduille pienemmille alueille. Tätä tehdään niin kauan, kunnes saadut alueet ovat tarpeeksi pieniä. Jakoja tehdään siis pystysuunnassa luokkaa O(n) kappaletta, missä n on labyrintin kyljen pituus. Kukin seinän piirtäminen vie luokkaa O(n) aikaa. Niinpä yhteensä pystysuorien seinien luominen vie maksimissaan O(n^2). Sama pätee vaakasuunnalle. Siispä sekin vie O(n^2) aikaa. Yhteensä aikaa menee näihin siis O(2n^2), joten algoritmin aikavaativuus on O(n^2).

Sidewinder-generointialgoritmi puolestaan lähtee liikkeelle sokkelopohjasta, jossa on kaikki mahdolliset seinät. Se valitsee aina satunnaisesti, poistaako seinän oikealta vai ei. Jos se ei poista seinää oikealta, niin silloin se kaivertaa jostain kohtaa jo kulkemaansa riviä polun ylöspäin. Näin syntyy sokkelo, joka ikäänkuin etenee oikealle päin. Koskaan ei synny umpikujia, jotka osoittaisivat alaspäin. Tämä on ehkä yksinkertaisin toteuttamani generointialgoritmi. Siinä käydään labyrinttiä yksinkertaisesti läpi rivi kerrallaan. Kaikkien rivien kaikki sarakkeet käydään kerran läpi. Siispä tämän aikavaativuus on luokkaa O(n^2), missä n on labyrintin kyljen pituus.

Tekemässäni käytännönvertailussa on käynyt ilmi, että generointialgoritmien nopeusjärjestys nopeimmasta hitainpaan on useimmiten, että rekursiivinen jakoalgoritmi on nopein, Sidewinder on isoilla sokkeloilla toiseksi nopein ja Kruskal hitain. Kuitenkin esimerkiksi 51x51-sokkeloa tarkastellessa niiden kaikkien nopeudet ovat hyvin lähellä toisiaan.

Ratkaisualgoritmeista nopein on yleensä WallFollower, toiseksi nopein lyhyimmät polut etsivä algoritmi ja hitain ketjualgoritmi. WallFollowerin ja lyhyimpien polkujen algoritmin nopeudet ovat esimerkiksi 51*51-kokoista sokkeloa tarkestellessa usein lähellä toisiaan. Joskus WallFollower on kuitenkin esimerkiksi kaksi kertaa nopeampi. Ketjualgoritmi puolestaan on usein vielä lyhyimmät polut -algoritmia puolta hitaampi.


### Puutteet ja parannusehdotukset

Työssä on toteutuksessa asioita, joita voisi parantaa esimerkiksi tehokkuuden, käytännöllisyyden tai laajuuden näkökulmasta. Esimerkiksi nykyisessä muodossaan algoritmini toimivat vain sokkeloilla, joiden koko on jaollinen kolmella mutta ei kahdella. Tämä johtuu siitä, miten toteutin sokkelot sellaisina taulukoina, joissa joka toinen alkio voi olla seinää ja joka toinen ei. Jouduin sitten rakentamaan algoritminikin niin, että toimivat nimenomaan tällaisessa muodossa annetulle sokkelolle. Lisäksi voidaan ratkaista vain sokkeloita, jotka ovat yhtä pitkiä kuin leveitä. Siispä ohjelmaa voisi parantaa ainakin niin, että laajennettaisiin sitä, millaisia labyrintteja voidaan ratkaista.

Sokkelot olisi ehkä kannattanut muutenkin esittää jossain muussa muodossa. Pitkä taulukko on läpikäynnin kannalta hidas, ja siksi monille algoritmeille esimerkiksi ihan oikea verkkorakenne olisi voinut olla parempi. Algoritmit ovat nyt osittain tämän takia ja muutenkin vähän hitaanpuoleisia. Olisin siis voinut valita mukaan myös jotain vähän nopeampiakin algoritmeja. Nämä olivat valittu erilaisuuksiensa takia. Erityisesti ketjualgoritmi toimii työssäni huomattavasti kaikkia muita algoritmeja hitaammin. Jos olisi enemmän aikaa, niin pitäisi siis kehitellä paljon tehokkaampia versioita algoritmeista. Erityisesti ketjualgoritmia olisi voinut ehtiessä tutkia ja kehitellä vielä enemmän.
