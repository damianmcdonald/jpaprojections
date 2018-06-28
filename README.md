JPA Projections Example
===================

Overview
-----------

The JPA Projections example project is intended to demonstrate the following:
* Use of a Hibernate Interceptor to detect entity changes in the Persistence Context
* Use of a Hibernate Interceptor to react to entity changes in the database
* Use of Observer design pattern to watch and react to entity persistence changes
* Use of the Command design pattern to provide a generic approach to syncing different entity types to an external source
* Use of DTO Projections in order to obtain a non Persistence Context managed instance of an entity

Scenario
-----------

The JPA Projections example project assumes the following scenario:
* It is an application that manages the lifecycle of Football Teams (CRUD operations)
* The Football Team entities are very large entity graphs (you need to use your imagination on this one!)
* When a Football Team has its data changed and it is persisted to the database, this application needs to react to that event by *syncing* a subset of the Football Team entity to an external system

Constraints
-----------

The JPA Projections example project assumes the following constraints:
* Entities should adhere to the [Single Responsibility Principle](https://en.wikipedia.org/wiki/Single_responsibility_principle). The entities should not contain any business logic or decorator interfaces to track whether they need to be synced
* The entities should avoid eagerly loading any of their associated collections
* When syncing data to the external system, DTO Projections should be used rather then the entities themselves in order to create efficient and performant data retrieval queries and to remove the need for a managed Persistence Context

Walkthrough
-----------
The logical data model used in the JPA Projections example project appears below:

![Logical Data Model](images/logical-data-model.png)

The SQL that Hibernate uses to create the database struture is shown below:

```SQL
create sequence hibernate_sequence start with 1 increment by 1;
create table PLAYER (ID integer not null, FIRSTNAME varchar(255), POSITION varchar(255), RATING integer, SURNAME varchar(255), TEAM_ID integer, primary key (ID));
create table SALARY (ID integer not null, CHARITABLE_CONTRIBUTION integer, GROSS integer, MEDICAL_CONTRIBUTION integer, NET integer, PLAYER_ID integer, primary key (ID));
create table TEAM (ID integer not null, LOCATION varchar(255), NAME varchar(255), primary key (ID));
create table TEAM_COLOUR (ID integer not null, HTMLCODE varchar(255), NAME varchar(255), TEAM_ID integer, primary key (ID));
alter table PLAYER add constraint FK_TEAM_ID foreign key (TEAM_ID) references TEAM;
alter table SALARY add constraint FK_PLAYER_ID foreign key (PLAYER_ID) references PLAYER;
alter table TEAM_COLOUR add constraint FK_TEAM_ID foreign key (TEAM_ID) references TEAM;
```

Chnages to the entities are *intercepted* via [EntityChangeInterceptor](https://github.com/damianmcdonald/jpaprojections/blob/master/src/main/java/com/github/damianmcdonald/jpaprojections/interceptor/EntityChangeInterceptor.java) which implemenets [EmptyInterceptor](https://docs.jboss.org/hibernate/orm/5.2/javadocs/org/hibernate/EmptyInterceptor.html).

It intercepts:

* [onFlushDirty](https://docs.jboss.org/hibernate/orm/5.2/javadocs/org/hibernate/EmptyInterceptor.html#onFlushDirty-java.lang.Object-java.io.Serializable-java.lang.Object:A-java.lang.Object:A-java.lang.String:A-org.hibernate.type.Type:A-) which is invoked when entities are marked as changed
* [postFlush](https://docs.jboss.org/hibernate/orm/5.2/javadocs/org/hibernate/EmptyInterceptor.html#postFlush-java.util.Iterator-) which is invoked after the entity has been persisted to the database

The [Observer design pattern](https://en.wikipedia.org/wiki/Observer_pattern) is used to watch and react to changes to the entities.

* [SyncTrack](https://github.com/damianmcdonald/jpaprojections/blob/master/src/main/java/com/github/damianmcdonald/jpaprojections/sync/SyncTrack.java) is the [Observable](https://docs.oracle.com/javase/8/docs/api/java/util/Observable.html)
* [SyncAction](https://github.com/damianmcdonald/jpaprojections/blob/master/src/main/java/com/github/damianmcdonald/jpaprojections/sync/SyncAction.java) is the [Observer](https://docs.oracle.com/javase/8/docs/api/java/util/Observer.html)

The [Command design pattern](https://en.wikipedia.org/wiki/Command_pattern) is used to provide a common syncing interface with entity specific implementations

* [SyncCommand](https://github.com/damianmcdonald/jpaprojections/blob/master/src/main/java/com/github/damianmcdonald/jpaprojections/sync/SyncCommand.java) is an abstract class that defines generic command behaviour
* [TeamSyncCommand](https://github.com/damianmcdonald/jpaprojections/blob/master/src/main/java/com/github/damianmcdonald/jpaprojections/sync/TeamSyncCommand.java) is an concrete command implementation that defines specific syncing behaviour for a ``Team`` entity.

In the [TeamDao](https://github.com/damianmcdonald/jpaprojections/blob/master/src/main/java/com/github/damianmcdonald/jpaprojections/dao/TeamDao.java) we don't want to retrieve a Persistence Context managed entity. Instead, we use a [DTO Projections](https://vladmihalcea.com/the-best-way-to-map-a-projection-query-to-a-dto-with-jpa-and-hibernate/) technique in order to obtain a subset of the `Team` entity which does not load unnecessary data and does not incur Persistence Context management overhead.

```java
public TeamDto getTeamProjection( int id ) {

    final List<PlayerDto> playersDto = super.entityManager
            .createQuery("select new com.github.damianmcdonald.jpaprojections.dto.PlayerDto(" +
                    "p.firstName, p.surname, p.position, p.rating, s.net, s.charitableContribution" +
                    ") from Player p join p.salary s where p.team.id = :id and p.rating > 92", PlayerDto.class)
            .setParameter("id", id)
            .getResultList();

    final TeamDto teamDto = super.entityManager
            .createQuery("select new com.github.damianmcdonald.jpaprojections.dto.TeamDto(t.name, t.location) from Team t where t.id = :id", TeamDto.class)
            .setParameter("id", id)
            .getSingleResult();

    teamDto.setPlayers(playersDto);

    return teamDto;
}
```

Getting started
-----------------

The library was built using the following toolchain:

* http://www.oracle.com/technetwork/java/javase/downloads/index.html[Java Oracle JDK 1.8]
* https://maven.apache.org/download.cgi[Maven 3.2.3]

Your mileage may vary with versions different than the ones specified above.

Follow these steps to get started:

1) Git-clone this repository.

```
git clone git://github.com/damianmcdonald/jpaprojections.git my-project

```

2) Change directory into your clone:

```
cd my-project
```

3) Use Maven to compile everything:

```
mvn clean package
```

4) Run the [SimulateApp](https://github.com/damianmcdonald/jpaprojections/blob/master/src/test/java/com/github/damianmcdonald/jpaprojections/SimulateApp.java) test to verify behaviour:

```
mvn -Dtest=SimulateApp test
```