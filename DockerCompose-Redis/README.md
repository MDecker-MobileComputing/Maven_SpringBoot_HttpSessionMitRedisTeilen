# Docker-Container für Redis und Load Balancer #

<br>

Vor dem ersten Start der Docker-Container mit `docker-compose up` muss auf einem Rechner
einmal das Image mit dem *Load Balancer* erstellt werden, siehe Unterordner
[LoadBalancer](LoadBalancer/)

<br>

## Redis-CLI ##

<br>

Man kann eine Terminal-Verbindung zum Container mit Redis aufbauen und den folgenden Befehl
eingeben:

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

Befehl, um Werte für einen bestimmten Key auszugeben (es werden aber nur Binärdaten ausgegeben):

```
redis-cli dump spring:session:sessions:a6619109-f56a-4e5b-a201-2531eebd39e0
```

<br>

Key löschen:

```
redis-cli del spring:session:sessions:a6619109-f56a-4e5b-a201-2531eebd39e0
```

<br>