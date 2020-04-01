## Viikkoraportti 4

Tällä viikolla käytin projektiin aikaa 18 tuntia.

Tällä viikolla toteutin ohjelmaani kaksi viimeistä suunnittelemaani algoritmia. Lisäksi tein käyttöliittymään sekä vaadittuja muutoksia että muita paranteluja. Toteutin uusiin algoritmeihin tarvittuja tietorakenteita, ja tein kaikkiin uusiin luokkiin Javadocit. Lisäksi tein uudet testiluokat ja niihin yksikkötestejä, ja aloitin toteutus- ja testausdokumenttien kirjoittamisen.

Ohjelma edistyi tällä viikolla mielestäni melko hyvin. Minulla oli tavoitteena toteuttaa siihen tällä viikolla viimeiset suunnitelemani ratkaisu- ja generointialgoritmi. Onnistuin tekemään nämä molemmat. Ensin lähdin toteuttamaan ketjualgoritmia, joka on ratkaisualgoritmi. Se osoittui tähän mennessä koodaamistani algoritmeista kaikkein vaikeimmaksi ja työlääksi. Minulla meni sen korjailemiseen ja kokoamiseen todella paljon aikaa, sillä siinä oli paljon pieniä yksityiskohtia, joissa tein virheitä. Näitä virheitä oli myös hyvin vaikea löytää. Lopulta onnistuin sitten toteuttamaan algoritmin, mutta toteutus on melko hidas. Minua jäi mietittämään, että sen olisi voinut varmasti toteuttaa paljon paremminkin.

Generointialgoritmi, jonka toteuti toteutin tällä viikolla, on Kruskalin algoritmi. Tämä vaati onneksi paljon vähemmän työtä kuin ketjualgoritmi, mutta oli tässäkin haastavia kohtia. Eniten aikaa tässä vei varmaankin se, kun mietin, miten toteuttaa UnionFind -rakenne toteuttamalleni labyrinttirakenteelle, eli kaksiulotteiselle taulukolle. Sain sen kuitenkin tehtyä ja algoritmin toimimaan.

Kruskalin algoritmia varten loin myös Edge-luokan kaaria varten sekä rajapinnan, jota pari ja kaari käyttävät. Rajapinnan loin sitä varten, että sitten minun oli yksinkertaisempaa saada joukko hyväksymään myös kaaria parien lisäksi. Kaaren toteutus on hyvin samanlainen kuin parin, mutta kahden kokonaisluvun sijasta siihen tallennetaan kaksi paria, jotka kumpikin kuvaavat omaa koordinaattipariaan.

Kun loin uudet algoritmit, niin tein myös käyttöliittymään muutokset, jotta niitä pääsee käyttämään sitä kautta. Lisäsin siis pari nappia ja muokkasin muutenkin vähän asettelua. Lisäksi vaihdoin suoritusajan aikayksikköä. Aiemmin se oli vain millisekunneissa. Nyt se on sekä nano- että millisekunneissa, jotta algoritmien nopeuksia pääsee paremmin vertailemaan. Tämän jälkeen käytinkin jonkin verran aikaa siihen, että pyörittelin algoritmeja ja vertailin niiden nopeuksia.

Koodasin lisäksi uusille luokille testiluokkiin yksikkötestejä. Lisäsin niihin myös Javadocin. Lopuksi kirjoitin vielä lisää alustavaa dokumentaatiota. Aloitin nimittäin toteutus- ja testausdokumenttien kirjoittamisen. Toteutusdokumentti eteni melko hyvin.

Tämän viikon aikana olen oppinut ymmärtämään UnionFind-rakennetta paljon syvemmin kuin ennen. Osaan nyt soveltaa sitä ihan uudenlaisissa tilanteissa, kuten mietin, miten sen tässä tarkoituksessa toteuttaisin. Lisäksi opin uudenlaisista mahdollisista virheenpaikoista, kun koodasin ketjualgoritmia. Osa virheistä korjautui tekemällä ihan pieniä muutoksia, joiden en ollut aluksi osannut ajatella vaikuttavan mihikään.

Tällä viikolla vaikeuksia tuotti ehdottomasti eniten ketjualgoritmi. Käytin siihen hyvin paljon aikaa ja vaivaa, jotta sain sen toimimaan ilman virheitä. Silti lopputulos on hidas ja kankea. Koska tällä kurssilla algoritmien suoritusnopeus on tärkeää, niin minua mietityttää, onko toteutukseni ketjualgoritmista ollenkaan tarpeeksi hyvä tälle kurssille. Yritin miettiä, miten parantaisin sitä, mutta en pystynyt jäämään tällä viikolla vielä pohtimaan sitä kovin pitkäksi aikaa, koska olin jo käyttänyt kyseiseen algoritmiin niin kauan. Pohdiskelin tällä viikolla myös sitä, miten toteuttaisin itse Random-luokan, mutta en oikein vielä keksinyt mitään hyvää ideaa.

Seuraavalla viikolla minulla olisikin tavoitteena keksiä miten ja toteuttaa oma Random-luokka, jotta minun ei tarvitsisi enää hyödyntää Javan versiota. Lisäksi haluaisin koettaa parantaa ketjualgoritmia. Voisin myös luultavasti tutkia ja toteuttaa vielä jonkin uudenlaisen ratkaisualgoritmin, mikäli minulla jää aikaa. Jatkan myös dokumentaation kirjoittamista ja teen vertaisarvioinnit.
