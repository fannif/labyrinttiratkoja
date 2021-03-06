## Viikkoraportti 2

Käytin tämän viikon aikana projektiin aikaa 15 tuntia ja 10 minuuttia.

Tällä viikolla olen edistänyt projektia melko paljon. Ensin vaihdoin projektin tyypin Gradle-projektista Maveniin, koska minulla oli vaikeuksia saada JavaFX toimimaan Gradlen kanssa. Jouduin muutenkin konfiguroimaan projetia ja Javaa, ennen kuin sain graafisen käyttöliittymän näkymään. Tämän lisäksi alkuviikosta päivitin määrittelydokumenttia. Tarkensin sitä aika- ja tilavaativuutten suhteen, ja tein paremmin selväksi, mitä n-merkintä eri kohdissa tarkoittaa. Päätin myös vaihtaa yhden algoritmin, jollaisen suunnittelin koodavaani, erääseen toiseen algoritmiin. Minulla meni vähän aikaa uuden algoritmin valintaan ja tutkimiseen. Uudeksi algoritmiksi tuli Wall Follower -algoritmi, joka tavallaan simuloi labyrintissa olevaa ihmistä, joka pitää aina kättä kiinni sokkelon vasemmassa seinässä.

Näiden muutosten jälkeen koodasin alustavan pohjan ohjelman graafiselle käyttöliittymälle, koska ajattelin, että labyrinttialgoritmeja olisi helpompaa alkaa toteuttaa, kun niiden tulokset on heti mahdollista nähdä käyttöliittymässä. Lisäsin siis tärkeimmät nappulat sekä labyrintin näyttävän näkymän. Tein myös luokan Maze sokkeloa varten.

Kun luonnos käyttöliittymästä oli ohjelmoitu, aloin toteuttaa Sidewinder -algoritmia labyrinttien tuottamiseen, koska ensin oli koodattava generoiva algoritmi, ennen kuin pääsee hyvin toteuttamaan ratkaisualgoritmia. Tässä meni aika kauan, mutta sain sen lopulta valmiiksi. Lisäsin käyttöliittymään nappulaan toiminnallisuuden, jolla uuden generoidun labyrintin sai näkyville, ja lisäsin labyrinttiin sinisellä ja vihreällä lähtö- ja maaliruudut. Tämän jälkeen kirjoitin luomilleni luokille Javadocit.

Nyt kun koodia oli jo vähän kirjoitettuna, niin aloin myös tarkkailla Checkstyleä. Korjailin ohjelmaa siten, että saisin sen paremmin noudattamaan määriteltyjä Checkstyle-sääntöjä.

Seuraavaksi päätin toteuttaa yhden ratkaisualgoritmin, ja päädyin koodaamaan Wall Follower -algoritmin. Tein siinä aluksi paljon virheitä, joten siihen kului jonkin verran aikaa. Samalla kun testailin algoritmin toimivuutta, lisäsin graafiseen käyttöliittymään toiminnallisuuden, jolla ratkaisualgoritmin pystyy ajamaan. Toteutin sen myös niin, että se näyttää algoritmin läpikäymän reitin labyrintissa purppuralla, jotta voin sitten myöhemmin, kun olen toteuttanut enemmän ratkaisualgoritmeja, vertailla erilaisten algoritmien reittejä. 

Lopuksi kirjoitin vielä testejä luomiini luokkiin. Varmistin ohjaajalta, ettei käyttöliittymää tarvitse sisältää Jacocon testikattavuuteen, joten en tehnyt käyttöliittymään testejä. Muihin luokkiin tein testejä sen verran, että sain niiden yhteiseksi rivikattavuudeksi 95% ja haarautumakattavuudeksi 87%.

Ohjelma on siis tällä viikolla edistynyt melko hyvin. Olen saanut toteutettua kaksi algortmia, graafisen käyttöliittymän pohjan ja Maze-olion sekä testejä. Jacoco ja Checkstyle ovat nyt toimivassa kunnossa.

Opin tällä viikolla hyvin paljon. Heti alkuviikosta opin, miten Javaa konfiguroidaan, ja mitä pitää tehdä, jos JavaFX ei toimi. Siinä samalla tuli opittua paljon uutta pom-tiedoston konfiguroinneista. Uskoisin myös, että uuden algoritmin etsimisestä ja algoritmien kirjoittamisesta on ollut hyötyä algoritmien hahmottamistaidolleni. Sain myös paremman käsityksen siitä, mihin sokkeloiden algoritminen ratkaisu ja tuottaminen voi perustua. Opin uutta graafisen käyttöliittymän luonnista, koska se ei ole minulle ennestään niin tuttua. Niinpä minun piti tutkia asioita, ennen kuin onnistuin luomaan labyrintille graafisen kuvauksen.

Vaikeaa tällä viikolla oli useakin asia. Ensin minulla meni paljon aikaa siihen, kun yritin saada JavaFX:n toimimaan. Onneksi se sitten lopulta kuitenkin onnistui. Minulla meni myös kauan saada Sidewinder -algoritmi toimimaan, koska halusin toteuttaa sen n*n -taulukkomuodossa annetuille labyrinteille. Minun oli vaikea hahmottaa, kummassa suunnassa x- ja y-koordinaatit menivät. Minulla meni kauan keksiä, että en voi aloittaa oletuksesta, että joka ruutu on seinä, vaan minun piti vaihtaa oletukseen, että ennen algoritmin suoritusta seinät muodostavat ikään kuin ruudukon.

Wall Finder -algoritmin toteuttaminenkin oli melko haastavaa, mutta ei onneksi yhtä vaikeaa kuin Sidewinderin. Aluksi keksimäni Wall Finder -toteutus ei osoittautunut toimivaksi, koska olin algorimissa vahingossa ottanut huomioon jo kuljetun reitin, minkä takia siinä ei aina edetty vasemmalle, vaikka olisi pitänyt. Onneksi lopulta sain sen ratkaistua.

Kysymyksiä heräsi tällä viikolla lähinnä Javadociin ja Checkstyleen liittyen. Minulle jäi epäselväksi, kuinka suuri testikattavuus on hyvä ja kuinka paljon Checkstyle-virheitä ohjelmassa saa olla. Pitääkö Checkstylen tarkastaa myös Javadocit? Onko ok, jos ohjelma on englanniksi, mutta kaikki dokumentaatio (Javadoc mukaan lukien) on englanniksi?

Ensi viikolla aion toteuttaa yhden generoimisalgoritmin lisää. Haluaisin ehtiä toteuttamaan myös yhden uuden ratkaisualgoritmin. Lisäksi haluaisin toteuttaa toiminnallisuuden, jossa saan generointien ja ratkaisujen viemiä aikoja näkyviin graafiseen käyttöliittymään. Tällöin voisin jo ensi viikolla päästä vertailemaan parin algoritmin kulkemia ratkaisureittejä, ja käyttämiä aikoja.
