## Viikkoraportti 5

Käytin tähän aikaa tällä viikolla noin 12 tuntia.

Tällä viikolla tein muutemia muutoksia ohjelman aiempaan rakenteeseen, ja toteutin myös pari uutta ohjelman toimintaa tukevaa ominaisuutta. Lisäsin myös testejä ja päivitin dokumentaatiota. Paransin myös suorituskykytestausmahdollisuuksia käyttöliittymässä. Tein lisäksi ensimmäisen vertaisarvioinnin.

Ohjelma edistyi tällä viikolla melko hyvin, mutta tosin eri tavalla kuin aiemmilla viikoilla. Tällä viikolla nimittäin keskityin enemmän jo olemassa olevan toiminnallisuuden muokkaamiseen kuin uuden luomiseen. 

Ensimmäisenä lähdin tällä viikolla toteuttamaan Random-luokkaa, jotta minun ei enää tarvitsisi käyttää Javan toteutusta satunnaislukujen generointiin. MersenneTwister vaikutti tosi hyvältä vaihtoehdolta, koska sen idea ei ollut liian monimutkainen, muttei myöskään turhan yksinkertainen. Minulla meni MersenneTwisterin toteutukseen aikaa, koska tutkin ensin algoritmia, sen ideaa ja erilaisia variaatiomahdollisuuksia. Sitten kehittelin siitä keräämieni tietojen pohjalta omaan tarkoitukseeni sopivan version. Se ei ihan aluksi toiminut oikein muutamien huolimattomuusvirheiden takia. Onneksi sain sen sitten kuitenkin toimimaan, niin pystyin korvaamaan Javan Random-luokan. Tein oman MersenneTwister-versioni sellaiseksi, että sillä on Javan Randomia toiminnaltaan muistuttava nextInt-metodi, joka palauttaa annettua lukua pienemmän kokonaisluvun.

Kun toteutin MersenneTwisterin, tein sille myös testiluokan. Lisäsin sinne testit oleellisimmille ominaisuuksille. Tein muutekin pari uutta testiä. Jacocon raportin mukaan testikattavuus on reilut 95%.

Selkeytin myös ohjelman pakettirakennetta. Jaoin domain-paketin niin, että siellä on erikseen paketti algorithms ratkaisu- ja generointialgoritmeille ja paketti utils muille toiminnallisille luokille, joita sokkeloalgoritmeissa käytetään.

Poistin ohjelmasta utils-luokat Pair ja Edge. Tarkoituksena oli vähentää viitteitä omiin olioihin. Parilla ja kaarella ei nimittäin ollut mitään sellaisia ominaisuuksia, mitä ei olisi saanut yksinkertaisesti vain muuttamalla ne taulukoiksi. Niinpä nyt ohjelmassa Pair-olioita korvaavat int[2]-taulukot ja Edge-olioita int[4]-taulukot. Tässä muutoksessa meni suhteellisen paljon aikaa, koska korjauksia piti tehdä koodin jokaiseen kohtaan, jossa oli käytetty kyseisiä olioita. Erityisesti Pair-oliota olin käyttänyt paljon eri luokissa ja testeissä. Jotkin ominaisuudet vaativat myös muokkausta, ennen kuin ne sai sopeutettua muokkaukseen. Tämä kuitenkin onnistui sulavammin, kuin mitä olin ajatellut. Muutos vaikuttaisi nopeuttaneen ainakin Kruskalin toimintaa jonkin verran.

Koodasin ohjelman käyttöliittymään myös mahdollisuuden valita, käsitelläänkö isoja vai pieniä labyrinttejä. Tein tämän siksi, koska tähän mennessä käyttämäni labyrintit ovat olleet melko pieniä. Ongelmana on ollut, että vaikka alkoritmit itsessään ovat toimineet ihan nopeasti, niin saadun labyrintin lisääminen ja näyttäminen kättöliittymässä on hidasta, joten suuremmilla sokkeloilla tästä aiheutui liian suuri viive. Niinpä päätin laittaa käyttöliittymään mahdollisuuden kahdenkokoisiin labyrintteihin. Jos käsitellään isoja labyrintteja, niin niistä ei näytetä kuvaa ollenkaan, jolloin käyttöliittymä on huomattavasti nopeampi, kuin edes pientä labyrinttikuvaa luodessa. Isoista labyrinteista näytetään vain algoritmiin kulunut aika ja ratkaisureitin pituus. Pienemmistä labyrinteista generoidaan vanhaan tyyliin myös kuva. Tein nopeustestausta käyttöliittymän kautta, ja vertailin algoritmeja.

Tällä viikolla meni myös aikaa siihen, kun yritin tutkia, miten tehostaisin algoritmejani. Erityisesti Kruskal on todella paljon muita algoritmeja hitaampi, mikä näkyy selkeästi ison labyrintin tapauksessa. Kruskalilla menee siinä generoimiseen noin 20 sekuntia, kun taas toisten generointialgoritmien tapauksissa puhutaan kymmenistä millisekunneista.

Lopuksi olen vielä päivittänyt ohjelman dokumentaatiota vastaamaan paremmin nykyistä tilannetta. Huomasin myös, että enqueue- ja dequeue-metodini toimivatkin nopeudella O(1), vaikka olin katsonut niiden toimivan ajassa O(n).

Tällä viikolla opin generoimaan itse satunnaismuuttujia. Aiemmin olin ajatellut, että satunnaislukujen generointi olisi tosi vaikeaa, enkä pystyisi toteuttamaan siitä omaa versiota. Nyt kuitenkin kun pääsi oikeasti tutkimaan asiaa ja tuota MersenneTwisteriä, niin huomasi, että se ei olekaan yhtä monimutkaista kuin olin ajatellut. Opin myös, että ohjelmani hitaudesta suuri osa johtui käyttöliittymän hitaudesta, ja siinä mielessä tekstikäyttöliittymä olisi voinut olla hyvä.

Tällä viikolla ongelmaksi nousi, että versioni Kruskalin algoritmista on todella hidas. Yritin keksiä keinoja nopeuttaa sitä niin, että kyseessä kuitenkin on vielä Kruskal, mutta en nyt ainakaan vielä keksinyt mitään. Toinen ongelma on, etten oikein tiedä, mitä minun kannattaisi ensi viikolla tehdä. Tässä vaiheessa ei enää haluaisi lähteä toteuttamaan mitään isoja uusia algoritmeja, ettei mitään hajoa. Mutta toisaalta haluaisin tehdä enemmänkin kuin vain hioa olemassaolevia luokkia.

Iso juttu, joka vähän huolettaa, on miten Zoom-demo onnistuu. Koneeni on hidas, joten toivottavasti se jaksaa pyörittää yhtä aikaa Zoomia ja ohjelmaani.

Ensi viikolla voisin ainakin kokeilla muuttaa sokkelopohjat int[n][n]-taulukoista yksiulotteisiksi int[nxn]-taulukoiksi, ja katsoa, nopeuttaisiko tämä ohjelmaa yhtään. Lisäksi jatkan varmaan tutkimista, jos keksisin mitään keinoa nopeuttaa erityisesti Kruskalia, mutta myös muita algoritmeja. Kirjoitan myös käyttöohjeet ja teen seuraavan vertaisarvioinnin.
