### Music Store

A simple web application that allows to:
- list most popular artists by country
- list top tracks by artist

powered by Last.fm

### Technologies used:
- java (8)
- spring boot
- angularJS (1.5.8)
- ui-bootstrap

### Installation

Music Store requires [Java 8](https://www.java.com/en/) and Maven (3.2+).


To build and run automated tests execute

```
mvn clean install
```

To run the web-app locally with maven:

configure your LASTFM-SECRET-API-KEY in:

```
src/main/resources/config/application.properties

here you can configure you KEY
lastfm.secretKey=LASTFM-SECRET-API-KEY
```

then run:

```
mvn clean install spring-boot:run
```

or from command-line

```
java -jar target/music-app-0.0.1-SNAPSHOT.jar --lastfm.secretKey=LASTFM-SECRET-API-KEY
```

###Resources
- [AngularJS](https://angularjs.org/)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Bootstrap](http://getbootstrap.com/)
- [Last.fm](http://www.last.fm/api)