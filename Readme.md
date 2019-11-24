# Google Contacts CSV to FritzBox Converter

## Inhalt
1. [Wozu ist das?](#1)
1. [Vorgehen](#2)
    1. [Google Kontakte exportieren](#21)
    1. [Variante 1 - Fertige Binärdatei verwenden](#22)
        1. [Java Runtime installieren](#221)
        1. [Tool herunterladen](#222)
        1. [Tool anwenden](#223)
        1. [Fertiges Telefonbuch in die Fritzbox importieren](#224)
    1. [Variante 2 - Das Tool selber bauen mit Maven (für Profis)](#23)
        1. [Benötigte Dinge](#231)
        1. [Auschecken und Bauen des Projektes](#232)
        1. [Tool anwenden](#233)
        1. [Fertiges Telefonbuch in die Fritzbox importieren](#234)

## Wozu ist das?
<a name="1"></a>
Neulich musste ich leider einmal mein FritzBox Telefonbuch löschen, weil ich meine Google Passwörter
geändert hatte. Leider ließ sich meine Google Kontakte Liste nicht mehr importieren,
weil Google irgendwas geändert hat und die Fritzbox nicht mehr im Freigabe Prozess akzeptiert wurde.
Da ich meine Kontakte auf meinen Fritzphones verwende musste eine Lösung her.
Diese liegt in dem vorliegenden Projekt vor - es ist allerdings manuell.

## Vorgehen
<a name="2"></a>
### Google Kontakte exportieren
<a name="21"></a>
Zunächst muss man seine Kontakte aus dem eigenen Google Konto als CSV exportieren. 
Den dazu benötigten Menupunkt findet man unten links ("Exportieren").
In dem erscheinenden Dialog wählt man die Voreinstellung ("Google CSV") und klickt auf "Exportieren".
Jenachdem wie man seinen Browser eingestellt hat, kommt dann noch ein Dialog für den Speicherort.

### Variante 1 Fertige Binärdatei verwenden
<a name="22"></a>
#### Java Runtime installieren
<a name="221"></a>
Das Tool benötigt eine installierte Java Runtime, diese muß bei Oracle heruntergeladen und installiert werden.

#### Tool herunterladen
<a name="222"></a>
Die aktuelle Version findet man [hier](https://github.com/sknull/googlecontactsconverter/releases)
Benötigt wird die googlecontactsconverter.jar. Diese lädt man in einen beliebigen Ordner herunter.

#### Tool anwenden    
<a name="223"></a>
Man öffnet eine Kommandozeile (cmd) und geht mit cd in den Ordner, in dem man das Tool
heruntergeladen hat.
Dort gibt man dann folgendes ein:

    java -jar target/google-contacts-1.0.0-SNAPSHOT.jar "Telefonbuch-Name" "Pfad-zur-Quelldatei" "Pfad-zur-gewünschten-Zieldatei" "gruppe1,gruppe2,..."
   
Der erste String ist der Name wie er später in der Fritzbox angezeigt wird, der letzte Parameter
ist die Liste von Gruppen, die aus der Google Kontakte List übernommen werden soll
(dieser kann ausgelassen werden - dann werden alle Einträge übernommen).

#### Fertiges Telefonbuch in die Fritzbox importieren
<a name="224"></a>
Dazu geht man im Fritzbox Menu unter Telefonie/Telefonbuch 
und legt mit dem Button oben rechts ein neues Telefonbuch an. Der Name ist egal,
weil er beim Import sowieso überschrieben wird. Nicht vergessen die geünschten Telefone zuzuordnen.
Danach klickt man auf "Wiederherstellen" unten rechts und wählt die eben erzeugte Telefonbuxh Datei aus.
Nach Klick auf "Telefonbuch Wiederherstellen" unten rechts sollte man eine Meldung sehen, 
das der Import erfolgreich war und kann direkt zum neuen Telefonbuch gehen, um die Einträge
zu kontrollieren.

Das wars dann auch schon.

### Variante 2 Das Tool selber bauen mit Maven (für Profis)
<a name="23"></a>
#### Benötigte Dinge
<a name="231"></a>
Um das Projekt zu bauen benötigt man
- Einen git client
- Die Maven build Umgebung

#### Auschecken und Bauen des Projektes
<a name="232"></a>
Man geht in der Konsole in den Ordner wo man das Projekt auschecken möchte und gibt folgendes ein:

    git clone https://github.com/sknull/googlecontactsconverter
    
danach geht man mit cd in den entstandenen Ordner:
    
    cd googlecontactsconverter
    
jetzt muss das Projekt gebaut werden mit

    mvn clean package -DskipTests

#### Tool anwenden    
<a name="233"></a>
danach befindet sich im Unterordner Target die fertige Jar-Datei, diese verwendet man so:

    java -jar target/google-contacts-1.0.0-SNAPSHOT.jar "Telefonbuch-Name" "Pfad-zur-Quelldatei" "Pfad-zur-gewünschten-Zieldatei" "gruppe1,gruppe2,..."
    
Der erste String ist der Name wie er später in der Fritzbox angezeigt wird, der letzte Parameter
ist die Liste von Gruppen, die aus der Google Kontakte List übernommen werden soll
(dieser kann ausgelassen werden - dann werden alle Einträge übernommen).

#### Fertiges Telefonbuch in die Fritzbox importieren
<a name="234"></a>
Dazu geht man im Fritzbox Menu unter Telefonie/Telefonbuch 
und legt mit dem Button oben rechts ein neues Telefonbuch an. Der Name ist egal,
weil er beim Import sowieso überschrieben wird. Nicht vergessen die geünschten Telefone zuzuordnen.
Danach klickt man auf "Wiederherstellen" unten rechts und wählt die eben erzeugte Telefonbuxh Datei aus.
Nach Klick auf "Telefonbuch Wiederherstellen" unten rechts sollte man eine Meldung sehen, 
das der Import erfolgreich war und kann direkt zum neuen Telefonbuch gehen, um die Einträge
zu kontrollieren.

Das wars dann auch schon.