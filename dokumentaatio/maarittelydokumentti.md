## Määrittelydokumentti


### Algoritmit

Työssäni minulla on suunnitelmana toteuttaa labyritin luomista varten ainakin Sidewinder-agoritmi, Kruskalin algoritmi ja rekursiivinen jakoalgoritmi. Olen ajatellut näitä kolmea algoritmia niiden erilasten odotettujen aikavaativuuksien ja tilavaativuuksien takia. 

Labyritmin ratkaisemista varten on myös aikomus toteuttaa ainakin kolme eri algoritmia. Haluan toteuttaa algoritmin, joka etsii lyhyimmän polun, algoritmin joka etsii kaikki lyhyimmät polut
ja Collision Solver -algoritmin, jonka pitäisi myös etsiä kaikki lyhimmät ratkaisut. Todennäköisesti tulen hyödyntämään näiden kaikkien toteutuksessa leveyshakua. Lyhyimmän polun etsimisalgoritmi tulee muistuttamaan toteutukseltaa A*-algoritmia ilman painoja. Nopeimmat polut- ja Collision Solver-algoritmit tulevat olemaan samantyylisiä, mutta ensimmäisessä niistä etsitään kaikki lyhyimmät polut, ja jälkimmäisessä puolestaan säästetään muistia pitämällä vain sen hetkiset koordinaatit muistissa.

Valitsemieni algoritmien toteuttamiseksi tarvitsen tietorakenteet ainakin joukkoa ja verkkoa varten. Niinpä toteutan ohjelmassani ne.


### Aika- ja tilavaativuustavoitteet

Sidewinderin tilavaativuuden haluaisin olevan O(1), koska sen tarvitsee itseasiassa käsitellä vain yksittäisiä ruutuja kerrallaan. Ei siis pitäisi tarvita aputaulukkoja. Kruskalin algoritmin tilavaativuudeksi pyrin saamaan O(n^2), koska sen suorituksessa tarvitaan apuna labyrintin koosta riippuvia apurakenteita. Rekursiivisen algoritmin tilavaativuudeksi haluaisin O(n), koska siinä pitää ainakin hyödyntää stäckiä. 

Sidewinderin aikavaativuudeksi pyrin saamaan O(n^2), koska siinä joudutaan menemään labyrintin ruutu kerrallaan. Kruskalin algoritmin aikavaativuudeksi pyrin saamaan O(n + mlog(n)) (tässä siis n on solmujen määrä puussa ja m kaarten), koska se voidaan toteuttaa Union Find -rakenteen avulla. Sen operaatiot vievät O(log(n)) aikaa, ja jos kaaria on m, niin niiden läpikäymiseen menee aikaa mlog(n). Lisäksi käydään kaikki solmut läpi. Rekursiivisen jakoalgoritmin aikavaativuuden haluaisin olevan luokkaa O(n), koska siinä labyrinttia jaetaan aina pienempiin paloihin, ja pahimmillaan jakoja tehdään käytännössä joka ruudulle.

Kuten yllä mainittu, ratkaisualgoritmien, joita aion toteuttaa, pohjana tulee todennäköisesti olemaan leveyshaku. Leveyshaun aikavaativuus on O(n + m), joten pyrin pääsemään algoritmeillani mahdollisimman lähelle kyseistä aikavaativuutta.


### Ongelma

Käsiteltäväksi ongelmaksi olen valinnon labyrintin luonnin ja ratkaisun. Tavoitteena on siis koodata ohjelma, joka pystyy sekä luomaan että ratkaisemaan algoritmeja tehokkaasti. Algoritmin on tarkoitus labyrinttia ratkaistessaan etsiä mahdollisimman lyhyt reitti lähdöstä maaliin.

Koska ongelmaan kuuluu sekä labyrinttien luominen että ratkaiseminen, täytyy ohjelmassa käyttää kahdenlaisia algoritmeja. Toiset ovat labyrinttien luomiseen ja toiset ratkaisemiseen. Molempiin tapauksiin haluan kokeilla eritehoisia ja eri toimintaperiaatteisiin keskittyviä algoritmeja, jotta pääsen vertailemaan niiden käyttökelpoisuuksia ongelmaan.


### Syötteet

Ohjelma ei tarvitse käyttäjältä paljoa syötteitä, koska se generoi ja ratkaisee labyrintit itse. Kuitenkin käyttäjä saa valita, mitä generointialgoritmia käytetään. Sovellukseen voi myös tulla mahdollisuus käyttäjälle valita labyrintin koko annetuista vaihtoehdoista. Syötteiden antaminen tapahtuu graafisen käyttöliittymän kautta.



### Lähteet

Lähteenä käytin mahdollisten algoritmivaihtoehtojen etsimisessä astrolog.org -sivun labyrinttikuvauksia. Lisäksi tutkin osaa algoritmeista tarkemmin saadakseni paremman käsityksen mahdollisista tila- ja aikavaativuuksista, joita saattaisin voida saavuttaa.

Sivut: 
https://www.astrolog.org/labyrnth/algrithm.htm
http://people.cs.ksu.edu/~ashley78/wiki.ashleycoleman.me/index.php/Main_Page.html
