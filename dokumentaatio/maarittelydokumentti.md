## Määrittelydokumentti


### Algoritmit

Työssäni minulla on suunnitelmana toteuttaa labyritin luomista varten ainakin Sidewinder-agoritmi, Kruskalin algoritmi ja rekursiivinen jakoalgoritmi. Olen ajatellut näitä kolmea algoritmia niiden erilasten odotettujen aikavaativuuksien ja tilavaativuuksien takia. Sidewinderin tilavaativuuden haluaisin olevan O(1), Kruskalin algoritmin O(n^2) ja rekursiivisen algoritmin O(n). Sidewinderin aikavaativuudeksi pyrin saamaan O(n^2), Kruskalin algoritmin aikavaativuudeksi O(n + mlog(n)) (tässä n on solmujen määrä puussa ja m kaarien), ja rekursiivisen luokkaa O(n).

Labyritmin ratkaisemista varten on myös aikomus toteuttaa ainakin kolme eri algoritmia. Haluan toteuttaa algoritmin, joka etsii lyhyimmän polun, algoritmin joka etsii kaikki lyhyimmät polut
ja Collision Solver -algoritmin, jonka pitäisi myös etsiä kaikki lyhimmät ratkaisut. Todennäköisesti tulen hyödyntämään näiden kaikkien toteutuksessa leveyshakua. Leveyshaun aikavaativuus on O(n + m). Lyhyimmän polun etsimisalgoritmi tulee muistuttamaan toteutukseltaa A*-algoritmia ilman painoja. Nopeimmat polut- ja Collision Solver-algoritmit tulevat olemaan samantyylisiä, mutta ensimmäisessä niistä etsitään kaikki lyhyimmät polut, ja jälkimmäisessä puolestaan säästetään muistia pitämällä vain sen hetkiset koordinaatit muistissa.

Valitsemieni algoritmien toteuttamiseksi tarvitsen tietorakenteet ainakin joukkoa ja verkkoa varten. Niinpä toteutan ohjelmassani ne.

### Ongelma

Käsiteltäväksi ongelmaksi olen valinnon labyrintin luonnin ja ratkaisun. Tavoitteena on siis koodata ohjelma, joka pystyy sekä luomaan että ratkaisemaan algoritmeja tehokkaasti. Algoritmin on tarkoitus labyrinttia ratkaistessaan etsiä mahdollisimman lyhyt reitti lähdöstä maaliin.

Koska ongelmaan kuuluu sekä labyrinttien luominen että ratkaiseminen, täytyy ohjelmassa käyttää kahdenlaisia algoritmeja. Toiset ovat labyrinttien luomiseen ja toiset ratkaisemiseen. Molempiin tapauksiin haluan kokeilla eritehoisia ja eri toimintaperiaatteisiin keskittyviä algoritmeja, jotta pääsen vertailemaan niiden käyttökelpoisuuksia ongelmaan.


### Syötteet



### Aika- ja tilavaativuudet



### Lähteet
