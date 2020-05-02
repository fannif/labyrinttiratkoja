## Käyttöohje

### Ohjelman lataaminen ja käynnistys

Ohjelmasta on tehty GitHub Release, johon on linkki README-tiedostossa. Releasessa on ohjelmasta generoitu jar-tiedosto. Lataa ensin Releasesta jar-tiedosto tietokoneellesi, jolloin tiedosto löytyy valitsemastasi latauskansiosta. Mene tähän kansioon terminaalissa. Pystyt terminaalista ajamaan ohjelman, kun olet jar-tiedoston sisältävässä kansiossa, komennolla `java -jar LabyrinthSolver.jar`. Tällöin ohjema käynnistyy.

Vaihtoehtoisesti ohjelman voi ladata GitHubista projektina. Tämä voidaan tehdä joko lataamalla ZIP-tiedosto tai kloonaamalla repositorio. Tällöin ohjelman voi ajaa menemällä terminaalissa ladattuun projektikansioon labyrinttiratkoja-master. Sen sisällä tulee mennä alikansioon LabyrinthSolver. Tässä alikansiossa ollessa ohjelman voi ajaa komennolla `mvn compile exec:java -Dexec.mainClass=labyrinthsolver.main.Main`.

### Ohjelman käyttäminen

Ohjelman käynnistyttyä avautuu aloitusvalikko. Aloitusvalikosta voit valita, haluatko generoida pieniä labyrintteja vai isompia labyrintteja, vai haluatko ajaa suorituskykytestit. 

Valitsemalla pienet labyrintit (Generate a 51-by-51 maze), siirryt seuraavaan valikkoon, josta voit valita, millä algoritmilla haluat generoida pienen labyrintin. Klikkaa haluamaasi vaihtoehtoa, niin labyrintti generoidaan, ja se tulee näkyviin käyttöliittymään. Samalla kun labyrintti tulee näkyviin, tulevat käyttöliittymään myös generointiin käytetty aika, napit aloitusvalikkoon palaamiseen, sekä eri ratkaisu- ja generointialgoritmien ajamiseen. Ylimästä eli palaamisnapista klikkaamalla pääset aloitusvalikkoon. Jos puolestaan haluat, että ohjelma ratkaisee generoidun labyrintin, niin klikkaa jotain ratkaisunapeista (esim. Solve using Wall follower). Tällöin labyrintti ratkaistaan valitsemallasi algoritmilla, ja labyrintille tulee näkyviin ratkaisupolku ja ratkaisuun käytetty aika. Jos puolestaan ennemmin haluaisit generoida uuden labyrintin, niin klikkaa sen generointialgoritmin nappia, jota haluat käyttää.

Jos puolestaan halutaan generoida suurempia labyrintteja, niin voidaan valita aloitusvalikosta nappi Generate a 213-by-213 maze. Tämän jälkeen tulee näkyviin valikko, jossa on kolme nappia, joista klikkaamalla voidaan valita, mitä generointialgoritmia halutaan käyttää. Kun generointialgoritmi on valittu, siirrytään seuraavaan näkymään. Tästä voidaan palata alkuvalikkoon valitsemalla Return to start menu. Vaihtoehtoisesti voit ratkaista generoidun labyrintin valitsemallasi algoritmilla napeista "Solve using..." se, joka vastaa toivottua algoritmia. Voit generoida uuden labyrintin valitsemalla sen "Generate a new..." -napin, joka vastaa haluamaasi generointialgoritmia. Kun algoritmi generoidaan, siitä näytetään sen generointiin kulunut aika, ja ratkaisun jälkeen näytetään myös ratkaisuun kulunut aika.

Aloitusvalikon kolmannesta, eli alimmasta, napista klikkaamalla puolestaam voidaan ajaa suorituskykytestit. Tähän menee arviolta noin yhdeksän minuuttia, eikä ohjelmaa voi sillä välin käyttää mihinkään muuhun. Suorituskykytestien tulokset tulostuvat ohjelman oman käyttöliittymän sijasta terminaaliin, josta niitä voi sitten tarkastella.

### Syötteet

Ohjelma ei siis ota käyttäjältä mitään vapaamuotoisia syötteitä. Ainoat syötteet, joita sille voi antaa, annetaan käyttöliittymän nappien painalluksina, joten virheellisiä syötteitä ei voi olla. Nappien kautta syötteenä voidaan näkymästä riippuen antaa tarkasteltavan labyrintin koko (suuri tai pieni), ja algoritmi, jota halutaan käyttää sokkelon ratkaisuun tai generointiin. Lisäksi syötteenä voidaan antaa nappien kautta sellaiset komennut, kuin palaa alkuvalikkoon tai aja suorituskykytestit.

### Jar-tiedoston generointi itse

Jos haluat generoida ohjelmasta itse jar-tiedoston, niin lataa projekti koneellesi tämän käyttöohjeen alussa kuvatulla tavalla. Sitten mene terminaalissa projektin alikansioon LabyrinthSolver, ja suoritä siellä komento `mvn package`. Löydät generoimasi jar-tiedoston LabyrinthSolver-kansion alikansiosta target nimellä LabyrinthSolver-1.0-SNAPSHOT.jar. Voit ajaa tämän jar-tiedoston target-kansiossa ollessasi komennolla java -jar LabyrinthSolver-1.0-SNAPSHOT.jar.
