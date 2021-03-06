## Määrittelydokumentti

Työn aiheena on labyrintinratkaisija, joka myös generoi labyrintteja. Ohjelmointikielenä on Java. Varsinainen koodi tulee englanniksi, mutta Javadoc ja kommentit suomeksi.

### Algoritmit

Työssäni minulla on suunnitelmana toteuttaa labyritin luomista varten Sidewinder-agoritmi, Kruskalin algoritmi ja rekursiivinen jakoalgoritmi. Olen ajatellut näitä kolmea algoritmia niiden erilasten odotettujen aikavaativuuksien ja tilavaativuuksien takia. 

Labyritmin ratkaisemista varten on myös aikomus toteuttaa kolme eri algoritmia. Haluan toteuttaa algoritmin, joka etsii lyhyimmän polun tai kaikki lyhyimmät polut jos niitä on useita ja seinänseuraamisalgoritmin, joka simuloi ihmistä, joka ratkaisee sokkelon pitämällä kättä kiinni seinässä ja jatkamalla eteenpäin niin, että jos jo käyty seinä tulee vastaan, niin vaihdetaan seinää käymättömään. Nopeimmat polut-algoritmissa etsitään kaikki lyhyimmät polut leveyshakuun perustuvalla menetelmällä. Seinänseuraamisalgoritmissa kuljetaan jotain seinää pitkin kohti ratkaisua. Kolmas ratkaisualgoritmi, jonka olen ajatellut toteuttaa, on ketjualgoritmi (Chain algorithm). Siinä sokkeloa käsitellään osasokkelo kerrallaan piirtämällä ensin lyhyt reitti sokkelon seinien läpi alusta maaliin. Sitten mennään sokkeloa läpi suunnilleen samaa reittiä, mutta niin, että kierretään läpimenneet seinät niiden läpi kulkemisen sijasta.

Kaikki kolme valitsemaani ratkaisualgoritmia toimivat erilailla, koska yksi simuloi ihmistä, toinen ratkaisee lyhyimmät reitit ja kolmas ratkaisee syhteellisen lyhyen reitin yrittämällä kulkea mahdollisimman keskellä labyrinttia.

Valitsemieni algoritmien toteuttamiseksi tarvitsen tietorakenteet ainakin sokkeloa, joukkoa ja jonoa varten. Niinpä toteutan ohjelmassani ne. Lisäksi toteutan algoritmin satunnaislukujen generoimiseksi.


### Aika- ja tilavaativuustavoitteet

Alla yleisesti labyrintin oletetaan, että jos sokkelo ajateltaisiin kaksiulotteisena taulukkona, niin n olisi rivin pituus. Tosin jos toisin erikseen mainitaan, niin n voi olla jotain muuta, esimerkiksi solmujen määrä. Taulukon rivit ja sarakkeet ovat yhtä pitkiä. Varsinainen labyrintin esittäminen tapahtuu yksiulotteisena taulukkona, jonka pituus on siis n x n.

Sidewinderin tilavaativuuden haluaisin olevan O(1), koska sen tarvitsee itseasiassa käsitellä vain yksittäisiä ruutuja kerrallaan. Ei siis pitäisi tarvita aputaulukkoja. Kruskalin algoritmin tilavaativuudeksi pyrin saamaan O(n^2), missä n on yhden rivin pituus, koska sen suorituksessa tarvitaan apuna labyrintin koosta riippuvia apurakenteita, toista taulukkoa. Rekursiivisen algoritmin tilavaativuudeksi haluaisin O(n), missä n on sokkelon kyljen pituus, koska siinä pitää ainakin hyödyntää stäckiä. 

Sidewinderin aikavaativuudeksi pyrin saamaan O(n^2), koska siinä joudutaan menemään labyrintin ruutu kerrallaan. Siinä tulee siis olemaan looppi loopin sisällä. Tässä n kuvaa riippuvuutta rivin pituudesta. Kruskalin algoritmin aikavaativuudeksi pyrin saamaan O(n + mlog(n)) (tässä siis n on solmujen määrä labyrintissa ja m kaarten), koska se voidaan toteuttaa Union Find -rakenteen avulla. Siinä siis labyrinttia käsitellään kuin puurakennetta. Union findin operaatiot vievät O(log(n)) aikaa, ja jos kaaria on m, niin niiden läpikäymiseen menee aikaa mlog(n). Lisäksi käydään kaikki solmut (n kpl) läpi. Rekursiivisen jakoalgoritmin aikavaativuuden haluaisin olevan luokkaa O(n^2), koska siinä labyrinttia jaetaan aina pienempiin paloihin, ja pahimmillaan jakoja tehdään käytännössä joka ruudulle.

Kuten yllä mainittu, ratkaisualgoritmeista, joita aion toteuttaa, nopeimmat polut etsivän algoritmin pohjana tulee todennäköisesti olemaan leveyshaku. Leveyshaun aikavaativuus on O(n + m), joten pyrin pääsemään algoritmillani mahdollisimman lähelle kyseistä aikavaativuutta. Tässä n kuvaa solmujen ja m kaarien määrää, kun taulukkomuotoinen labyrinttia käsitellään verkkona. Seinänseuraamisalgoritmissa joudutaan pahimmillaan käymään kaikki labyrintin ruudut läpi, joten sen aikavaativuus on O(n^2), missä n on labyrintin kyljen pituus.

Seinänseuraamisalgoritmi tarvitsee todennäköisesti aputaulukon, joten sen tilavaativuus on O(n^2), missä n on sokkelon kyljen pituus. Myös kaksi muuta algoritmia voivat joutua käyttämään aputaulukoita, jolloin niiden tilavaativuus on tämä sama. Ketjualgoritmi joutuu pahimmassa tapauksessa käymään läpi koko sokkelotaulukon, jolloin sen aikavaativuus on O(n^2), missä n on sokkelon kyljen pituus.


### Ongelma

Käsiteltäväksi ongelmaksi olen valinnut labyrintin luonnin ja ratkaisun. Tavoitteena on siis koodata ohjelma, joka pystyy sekä luomaan että ratkaisemaan algoritmeja tehokkaasti. Algoritmin on tarkoitus labyrinttia ratkaistessaan etsiä erilaisia reittejä ja myös mahdollisimman lyhyt reitti lähdöstä maaliin.

Koska ongelmaan kuuluu sekä labyrinttien luominen että ratkaiseminen, täytyy ohjelmassa käyttää kahdenlaisia algoritmeja. Toiset ovat labyrinttien luomiseen ja toiset ratkaisemiseen. Molempiin tapauksiin haluan kokeilla eritehoisia ja eri toimintaperiaatteisiin keskittyviä algoritmeja, jotta pääsen vertailemaan niiden käyttökelpoisuuksia ongelmaan.


### Syötteet

Ohjelma ei tarvitse käyttäjältä paljoa syötteitä, koska se generoi ja ratkaisee labyrintit itse. Kuitenkin käyttäjä saa valita, mitä generointi- tai ratkaisualgoritmia käytetään. Sovelluksessa on myös mahdollisuus käyttäjälle valita labyrintin koko annetuista vaihtoehdoista. Syötteiden antaminen tapahtuu graafisen käyttöliittymän kautta nappeja klikkaamalla.



### Lähteet

Lähteenä käytin mahdollisten algoritmivaihtoehtojen etsimisessä astrolog.org -sivun labyrinttikuvauksia. Lisäksi tutkin osaa algoritmeista tarkemmin saadakseni paremman käsityksen mahdollisista tila- ja aikavaativuuksista, joita saattaisin voida saavuttaa.

Sivut: 
https://www.astrolog.org/labyrnth/algrithm.htm
http://people.cs.ksu.edu/~ashley78/wiki.ashleycoleman.me/index.php/Main_Page.html
https://github.com/theJollySin/mazelib/blob/master/docs/MAZE_SOLVE_ALGOS.md
