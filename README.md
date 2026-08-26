#  Immobilienverwaltung

Eine webbasierte Anwendung zur Verwaltung von Immobilien, Mietern und Mietverträgen.

Das Projekt besteht aus einer Server-Anwendung und einem separaten JSF-Client. Die Kommunikation zwischen Client und Server erfolgt über eine REST-API.

##  Funktionen

* Mieter anlegen, anzeigen, bearbeiten und löschen
* Immobilien verwalten und verfügbare Immobilien anzeigen
* Mietverträge erstellen und verwalten
* Mietverträge kündigen
* Benutzeranmeldung und Rollenprüfung

##  Technologien

### Backend

* Java
* Jakarta EE
* JAX-RS
* JPA
* REST API

### Frontend

* Jakarta Faces (JSF)
* XHTML / Facelets

### Datenbank & Server

* Apache Derby
* SQL
* GlassFish

##  Architektur

Die Anwendung ist mehrschichtig aufgebaut:

`JSF-Client → REST-API → Backend → JPA → Datenbank`

Der Client kommuniziert über HTTP-Anfragen mit der REST-API der Server-Anwendung. Das Backend verarbeitet die Anfragen und greift über JPA auf die relationale Datenbank zu.

##  Projektstruktur

Das Repository enthält zwei Anwendungen:

* **immobilienverwaltung** – Server-Anwendung mit REST-Schnittstellen, Geschäftslogik, JPA-Entities und Datenbankzugriff
* **immobilienverwaltungClient** – Client-Anwendung mit JSF-Oberflächen und Kommunikation mit der REST-API

##  Screenshots

Screenshots der Anwendung werden hier ergänzt.

##  Hintergrund

Das Projekt entstand im Rahmen meines Wirtschaftsinformatik-Studiums und diente der praktischen Umsetzung einer mehrschichtigen Webanwendung mit Java und Jakarta EE.
