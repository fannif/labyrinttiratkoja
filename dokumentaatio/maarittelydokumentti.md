## Määrittelydokumentti

Työn aiheena on labyrintinratkaisija, joka myös generoi labyrintteja. Ohjelmointikielenä on Java.

### Algoritmit

Työssäni minulla on suunnitelmana toteuttaa labyritin luomista varten ainakin Sidewinder-agoritmi, Kruskalin algoritmi ja rekursiivinen jakoalgoritmi. Olen ajatellut näitä kolmea algoritmia niiden erilasten odotettujen aikavaativuuksien ja tilavaativuuksien takia. 

Labyritmin ratkaisemista varten on myös aikomus toteuttaa ainakin kolme eri algoritmia. Haluan toteuttaa algoritmin, joka etsii lyhyimmän polun, algoritmin joka etsii kaikki lyhyimmät polut
ja seinänseuraamisalgoritmin, joka simuloi ihmistä, joka ratkaisee sokkelon pitämällä kättä kiinni seinässä ja jatkamalla eteenpäin niin, että jos jo käyty seinä tulee vastaan, niin vaihdetaan seinää käymättömään. Todennäköisesti tulen hyödyntämään näistä ainakin kahden ensimmäisen toteutuksessa leveyshakua. Lyhyimmän polun etsimisalgoritmi tulee muistuttamaan toteutukseltaa A*-algoritmia ilman painoja. Nopeimmat polut-algoritmissa etsitään kaikki lyhyimmät polut leveyshakuun perustuvalla menetelmällä. Seinänseuraamisalgoritmissa kuljetaan jotain seinää pitkin kohti ratkaisua.

Valitsemieni algoritmien toteuttamiseksi tarvitsen tietorakenteet ainakin joukkoa ja jonoa varten. Niinpä toteutan ohjelmassani ne.


### Aika- ja tilavaativuustavoitteet

Alla yleisesti layrintin oletetaan olevan kaksiulotteisessa taulukossa. Taulukon rivit ja sarakkeet ovat yhtä pitkiä.

Sidewinderin tilavaativuuden haluaisin olevan O(1), koska sen tarvitsee itseasiassa käsitellä vain yksittäisiä ruutuja kerrallaan. Ei siis pitäisi tarvita aputaulukkoja. Kruskalin algoritmin tilavaativuudeksi pyrin saamaan O(n^2), missä n on yhden rivin pituus, koska sen suorituksessa tarvitaan apuna labyrintin koosta riippuvia apurakenteita, toista taulukkoa. Rekursiivisen algoritmin tilavaativuudeksi haluaisin O(n), missä n on rivin pituus, koska siinä pitää ainakin hyödyntää stäckiä. 

Sidewinderin aikavaativuudeksi pyrin saamaan O(n^2), koska siinä joudutaan menemään labyrintin ruutu kerrallaan. Siinä tulee siis olemaan looppi loopin sisällä. Tässä n kuvaa riippuvuutta rivin pituudesta. Kruskalin algoritmin aikavaativuudeksi pyrin saamaan O(n + mlog(n)) (tässä siis n on solmujen määrä puussa ja m kaarten), koska se voidaan toteuttaa Union Find -rakenteen avulla. Siinä siis labyrintin taulukkomuodon perusteella tehdään sitä kuvaava puurakenne. Union findin operaatiot vievät O(log(n)) aikaa, ja jos kaaria on m, niin niiden läpikäymiseen menee aikaa mlog(n). Lisäksi käydään kaikki solmut läpi. Rekursiivisen jakoalgoritmin aikavaativuuden haluaisin olevan luokkaa O(n^2), koska siinä labyrinttia jaetaan aina pienempiin paloihin, ja pahimmillaan jakoja tehdään käytännössä joka ruudulle.

Kuten yllä mainittu, ratkaisualgoritmeista, joita aion toteuttaa, ainakin lyhyimmän polun ja nopeimmat polut etsivien algoritmien pohjana tulee todennäköisesti olemaan leveyshaku. Leveyshaun aikavaativuus on O(n + m), joten pyrin pääsemään algoritmeillani mahdollisimman lähelle kyseistä aikavaativuutta. Tässä n kuvaa solmujen ja m kaarien määrää, kun taulukkomuotoinen labyrintti on ensin muutettu verkoksi. Seinänseuraamisalgoritmissa joudutaan pahimmillaan käymään kaikki labyrintin ruudut läpi, joten sen aikavaativuus on O(n^2), missä n on labyrinttia kuvaavan taulukon rivin pituus.

Seinänseuraamisalgoritmi tarvitsee todennäköisesti aputaulukon, joten sen tilavaativuus on O(n^2), missä n on alkuperäisen taulukon rivin pituus. Myös kaksi muuta algoritmia voivat joutua käyttämään aputaulukoita, jolloin niiden tilavaativuus on tämä sama.


### Ongelma

Käsiteltäväksi ongelmaksi olen valinnut labyrintin luonnin ja ratkaisun. Tavoitteena on siis koodata ohjelma, joka pystyy sekä luomaan että ratkaisemaan algoritmeja tehokkaasti. Algoritmin on tarkoitus labyrinttia ratkaistessaan etsiä mahdollisimman lyhyt reitti lähdöstä maaliin.

Koska ongelmaan kuuluu sekä labyrinttien luominen että ratkaiseminen, täytyy ohjelmassa käyttää kahdenlaisia algoritmeja. Toiset ovat labyrinttien luomiseen ja toiset ratkaisemiseen. Molempiin tapauksiin haluan kokeilla eritehoisia ja eri toimintaperiaatteisiin keskittyviä algoritmeja, jotta pääsen vertailemaan niiden käyttökelpoisuuksia ongelmaan.


### Syötteet

Ohjelma ei tarvitse käyttäjältä paljoa syötteitä, koska se generoi ja ratkaisee labyrintit itse. Kuitenkin käyttäjä saa valita, mitä generointialgoritmia käytetään. Sovellukseen voi myös tulla mahdollisuus käyttäjälle valita labyrintin koko annetuista vaihtoehdoista. Syötteiden antaminen tapahtuu graafisen käyttöliittymän kautta.



### Lähteet

Lähteenä käytin mahdollisten algoritmivaihtoehtojen etsimisessä astrolog.org -sivun labyrinttikuvauksia. Lisäksi tutkin osaa algoritmeista tarkemmin saadakseni paremman käsityksen mahdollisista tila- ja aikavaativuuksista, joita saattaisin voida saavuttaa.

Sivut: 
https://www.astrolog.org/labyrnth/algrithm.htm
http://people.cs.ksu.edu/~ashley78/wiki.ashleycoleman.me/index.php/Main_Page.html
