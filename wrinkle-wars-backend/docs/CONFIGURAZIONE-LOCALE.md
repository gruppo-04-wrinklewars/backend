# Configurazione locale del backend

Configurazione importata dalla V8.2.0 nelle task BE-04 e BE-05. I servizi e le entità del gioco saranno pubblicati progressivamente nelle task successive.

## Profili

- Default: PostgreSQL locale su `localhost:5432`, database `wrinklewars`. Creare il database prima dell'avvio. Le impostazioni `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` e `DDL_AUTO` sono quelle già previste nella V8.2.0; non sono state aggiunte nuove variabili applicative.
- `docker`: indirizzo PostgreSQL `db:5432` sulla rete interna di Compose. Il Dockerfile e il Compose completo verranno importati nella BE-44. Le credenziali sono valori dimostrativi per l'ambiente locale isolato, non per servizi esposti pubblicamente.
- `https`: abilita il flag Secure del cookie di sessione. Richiede HTTPS configurato; non crea un certificato TLS e non va abilitato per il normale HTTP locale.
- `test`: disponibile solo nei sorgenti di test; usa H2 in memoria e non modifica il database di gioco. Per verificare la connessione PostgreSQL, sovrascrivere URL, username, password e dialect con proprietà JVM e usare un database temporaneo dedicato.

## Verifica

Eseguire `mvn -B clean test` per il test di avvio, connessione H2 e serializzazione JSON. Per una verifica PostgreSQL dedicata usare il test `WrinkleWarsBackendApplicationTests` con `-Dspring.datasource.url`, `-Dspring.datasource.username`, `-Dspring.datasource.password` e `-Dspring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect`. Non usare il database del gioco.

La configurazione principale mantiene `ddl-auto=update` come nella V8.2.0; nessuna migrazione Flyway è stata aggiunta. Le impostazioni di sessione, errori API, fuso orario JDBC e dimensione massima dei file riprendono quelle della versione originale.
