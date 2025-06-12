# Docker-Container für Redis und Load Balancer #

<br>

Vor dem ersten Start der Docker-Container mit `docker-compose up` muss auf einem Rechner
einmal das Image mit dem *Load Balancer* erstellt werden, siehe Unterordner
[LoadBalancer](LoadBalancer/).

<br>

## Redis-CLI ##

<br>

Man kann eine Terminal-Verbindung zum Container mit Redis aufbauen und den folgenden Befehl eingeben:

```
redis-cli keys '*'
```

<br>

Es werden dann alle Keys ausgegeben, für die derzeit Daten in der Redis-Instanz gespeichert sind, z.B. wenn zwei HTTP-Sessions gespeichert sind:
```
1) "spring:session:sessions:a6619109-f56a-4e5b-a201-2531eebd39e0"
2) "spring:session:sessions:1a4283b2-0bab-401c-959b-791d344a70cb"
```

<br>

Das Redis-CLI kann auch im interaktiven Modus betrieben werden; es wird durch den Befehl `redis-cli` gestartet,
die Befehle sind dann ohne vorangestellten `redis-cli` einzugeben.

<br>

Befehl, um Werte für einen bestimmten Key auszugeben (es werden aber nur Binärdaten ausgegeben):

```
dump spring:session:sessions:a6619109-f56a-4e5b-a201-2531eebd39e0
```

<br>

Verwaltung eigener Key-Value-Paare:
```
set mein_key_1 Blafasel
get mein_key_1
del mein_key_1
```

<br>

Abfrage Persistierungs-Strategie von Redis-Instanz (RDB und/oder AOF, sie auch [hier](https://redis.io/docs/latest/operate/oss_and_stack/management/persistence/)

* Abfrage RDB (Redis Database, "Snapshotting"): `config get save`
* Abfrage AOF (Apend-Only File)               : `config get appendonly`

<br>

Beispielausgabe für RDB:
```
1) "save"
2) "3600 1 300 100 60 10000"
```

Erklärung:
```
3600 1   → Speichere die Datenbank, wenn sich mindestens 1 Key innerhalb von 3600 Sekunden (1 Stunde) geändert hat
300 100  → Speichere, wenn sich mindestens 100 Keys innerhalb von 300 Sekunden (5 Minuten) geändert haben
60 10000 → Speichere, wenn sich mindestens 10.000 Keys innerhalb von 60 Sekunden (1 Minute) geändert haben
```
Vorübergehend ausschalten (bis Server neu gestartet wird): `CONFIG SET save ""`

<br>

Beispielausgabe für AOF (wenn ausgeschaltet):
```
1) "appendonly"
2) "no"
```

<br>