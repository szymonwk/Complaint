Aplikacja:

- Testy integracyjne - test containers
- Testy jednostkowe
- Spring Actuator
- Java 21
- Baza Postgres (docker compose)

Uruchamianie:

Aby uruchomić aplikację potrzebujmy docker, następnie w folderze docker-compose należy wystartować bazę.
Dodatkowo należy uruchomić spring boot z profilem „dev”

TODO:

- konterneryzacja aplikacji (w zależności co jest standardem w organizacji użyłbym docker-jib, lub standardowo
- Dodanie większej ilości testów
- Dodanie liquibase dla struktur encji
- Paginacja dla listowania

W zależności od wymagań lub oczekiwanego ruchu:

- Aby zapewnić HA (high availability - jeżeli w ogóle byłoby przewidywane i wymagane) - deployment należałoby wykonać np. Na instance group, do klastra Kubernetes, app engine, etc., końcowa opcja powinna zależeć od konkretnych wymagań
- Rozważyłbym użycie cache (w pobieraniu danych, jednak jeśli nie byłoby
- Należałoby ustawić odpowiednie wartości dla JVM (heap i stack), w przypadku krótko żyjącej aplikacji można by również zastosować wersję 32 bit JVM, mogłaby okazać się szybsza. Tuning JVM rozważałbym na etapie testów perf.
- Obecnie przy mapowaniu encji w metodzie GET jest użyty paralel stream, jednak ma on sens w przypadku większej ilości danych, dla niewielkiej ilości danych narzut związany z tworzeniem wątków może być większy niż czas wykonania mapowania
- Jeżeli byłby to niezależny mikroserwis, pozbawiony relacji np. Do account czy innych encji można by rozważyć użycie bazy danych nosql
