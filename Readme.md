# Google Contacts CSV to FritzBox Converter

## Wozu ist das?
Neulich musste ich leider einmal mein FritzBox Telefonbuch löschen, weil ich meine Google Passwörter
geändert hatte. Leider ließ sich meine Google Kontakte Liste nicht mehr importieren,
weil Google irgendwas geändert hat und die Fritzbox nicht mehr im Freigabe Prozess akzeptiert wurde.
Da ich meine Kontakte auf meinen Fritzphones verwende musste eine Lösung her.
Diese liegt in dem vorliegenden Projekt vor - es ist allerdings manuell.

## Vorgehen
### Google Kontakte exprtieren
Zunächst muss man seine Kontakte aus dem eigenen Google Konto als CSV exportieren. 
Den dazu benötigten Menupunkt findet man unten links ("Exportieren").
In dem erscheinenden Dialog wählt man die Voreinstellung ("Google CSV") und klickt auf "Exportieren".
Jenachdem wie man seinen Browser eingestellt hat, kommt dann noch ein Dialog für den Speicherort.

### Verwendung des Tools
Momentan habe ich das Projekt noch nicht fertig eingerichtet, daher gibt es noch keine fertigen Jar Dateien.

#### Benötigte Dinge
Um das Projekt zu bauen benötigt man
- Einen git client
- Die Maven build Umgebung

### Auschecken und Bauen des Projektes
Man geht in der Konsole in den Ordner wo man das Projekt auschecken möchte und gibt folgendes ein:

    git clone https://github.com/sknull/googlecontactsconverter
    
danach geht man mit cd in den entstandenen Ordner:
    
    cd googlecontactsconverter
    
jetzt muss das Projekt gebaut werden mit

    mvn clean package -DskipTests

### Tool anwenden    
danach befindet sich im Unterordner Target die fertige Jar-Datei, diese verwendet man so:

    java -jar target/google-contacts-1.0.0-SNAPSHOT.jar "Telefonbuch-Name" "Pfad-zur-Quelldatei" "Pfad-zur-gewünschten-Zieldatei" "gruppe1,gruppe2,..."
    
Der erste String ist der Name wie er später in der Fritzbox angezeigt wird, der letzte Parameter
ist die Liste von Gruppen, die aus der Google Kontakte List übernommen werden soll
(dieser kann ausgelassen werden - dann werden alle Einträge übernommen).

### Fertiges Telefonbuch in die Fritzbox importieren
Dazu geht man im Fritzbox Menu unter Telefonie/Telefonbuch 
und legt mit dem Button oben rechts ein neues Telefonbuch an. Der Name ist egal,
weil er beim Import sowieso überschrieben wird. Nicht vergessen die geünschten Telefone zuzuordnen.
Danach klickt man auf "Wiederherstellen" unten rechts und wählt die eben erzeugte Telefonbuxh Datei aus.
Nach Klick auf "Telefonbuch Wiederherstellen" unten rechts sollte man eine Meldung sehen, 
das der Import erfolgreich war und kann direkt zum neuen Telefonbuch gehen, um die Einträge
zu kontrollieren.

Das wars dann auch schon.