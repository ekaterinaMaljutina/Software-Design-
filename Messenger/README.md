# Как собрать
---
- ./gradlew build
- ./gradlew installDist

# Запуск
---
Сначало запускаем сервер : 
- ./build/install/Main/bin/server 1234 

Потом запускаем клиентов:
- ./build/install/Main/bin/client localhost 1234 kate