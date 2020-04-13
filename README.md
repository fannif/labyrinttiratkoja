# Labyrinttiratkoja

## Tietorakenteet ja algoritmit harjoitustyö 2020

#### [Viikkoraportti 1](https://github.com/fannif/labyrinttiratkoja/tree/master/dokumentaatio/viikkoraportti1.md)

#### [Viikkoraportti 2](https://github.com/fannif/labyrinttiratkoja/blob/master/dokumentaatio/viikkoraportti2.md)

#### [Viikkoraportti 3](https://github.com/fannif/labyrinttiratkoja/blob/master/dokumentaatio/viikkoraportti3.md)

#### [Viikkoraportti 4](https://github.com/fannif/labyrinttiratkoja/blob/master/dokumentaatio/viikkoraportti4.md)

#### [Viikkoraportti 5](https://github.com/fannif/labyrinttiratkoja/blob/master/dokumentaatio/viikkoraportti5.md)

#### [Määrittelydokumentti](https://github.com/fannif/labyrinttiratkoja/tree/master/dokumentaatio/maarittelydokumentti.md)

#### [Toteutusdokumentti](https://github.com/fannif/labyrinttiratkoja/tree/master/dokumentaatio/toteutusdokumentti.md)

#### [Testausdokumentti](https://github.com/fannif/labyrinttiratkoja/tree/master/dokumentaatio/testausdokumentti.md)

### Jacoco
Voit generoida Jacoco-raportin lataamalla projektin GitHubista ja menemällä sitten lataamaasi kansioon LabyrinthSolver-hakemiston sisälle. Raportin generoimiseksi suoritä hakemiston sisällä ollessasi Linuxilla komento `mvn test jacoco:report`.
Raportti kertoo testien rivi- ja haaraumakattavuuden. Kattavuusraportti löytyy tiedostosta target/site/jacoco/index.html.

### Checkstyle
Voit generoida Checkstyle-raportin samasta hakemistossa ollessasi kuin Jacoco-raportin. Komento on `mvn jxr:jxr checkstyle:checkstyle`. Komento luo target-kansioon projektista Checkstyle-raportin (target/site/checkstyle.html), jonka voi avata selaimella, sekä tulostaa terminaaliin tyylivirheiden määrän.
