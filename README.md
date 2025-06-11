# Demo für Spring Session mit Redis #

<br>

Diese Repo enthält eine einfache Spring-Boot-Anwendung, die nur
[zwei Seiten mit statischem Content](src/main/resources/static/inhalt) ausliefert.
Es werden zwei Instanzen dieser Spring-Boot-Anwendung gestartet, nämlich auf den Ports `8080` und `8081`.
Ein vorgeschalteter *Load Balancer* (nginx) verteilt die auf Port `8000` eingehenden HTTP-Anfragen zwischen
den beiden Spring-Boot-Instanzen.

Der von der Spring-Boot-Anwendung ausgelieferte statische Content ist mit *Spring Web Security* geschützt,
so dass der Nutzer nur darauf zugreifen kann, wenn er im vorgeschalteten HTML-Formular Nutzername und
Passwort eingibt (siehe Methode `nutzerLaden()` in der
Klasse [Sicherheitskonfiguration](src/main/java/de/eldecker/spring/redisdemo/Sicherheitskonfiguration.java)
für Nutzername und Passwort).
Wenn der Nutzer sich erfolgreich authentifziert hat, dann wird dies in der HTTP-Sitzung gespeichert, für welche
im Browser das Cookie `JSESSIONID` angelegt wird.

Damit der Nutzer sich nicht neu authentifizieren muss, wenn einer seiner Folge-Anfrage auf die andere
der beiden Instanzen der Spring-Boot-Anwendung weitergeleitet wird, wird diese Sitzungsinformation über
die gemeinsame Redis-Instanz geteilt.

<br>

**Siehe auch:**

* https://docs.spring.io/spring-session/reference/guides/boot-redis.html
* https://www.baeldung.com/spring-session

<br>

----

## Ausführen der Demo ##

<br>

* Docker-Image mit *Load Balancer* bauen, siehe [dieser Ordner](DockerCompose-Redis/LoadBalancer)

* Container mit Redis und *Load Balancer* starten, siehe [dieser Ordner](DockerCompose-Redis/)

* Die beiden Instanzen der Spring-Boot-Anwendung mit `maven_start1.bat` und `maven_start2.bat` starten.

* Im Browser Anwendung über *Load Balancer* aufrufen: http://localhost:8000

<br>

----

## License ##

<br>

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

<br>