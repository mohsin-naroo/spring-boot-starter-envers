# spring-boot-starter-envers

![Build Workflow](https://github.com/mohsin-naroo/spring-boot-starter-envers/actions/workflows/maven-build.yml/badge.svg)

Spring Boot starter to implement technical as well as functional audit using [Hibernate Envers](https://hibernate.org/orm/envers/).

## Requirements

- [Java 17](https://www.oracle.com/pk/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

## Features

- Persist parent entity as well as associated entities on save of parent entity with `cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true`
- Persist technical audit for parent entity as well as associated entities with annotation `@Audited`
- Exclude technical audit for associated entities for which audit not required with annotation `@NotAudited`
- Add action user name to technical audit by implementing `RevisionListener.newRevision`
- Add functional audit on save of entity technical audit by implementing `EntityTrackingRevisionListener.entityChanged`

## Running the application locally

- Execute the `JUnit` test `io.github.meritepk.webapp.news.NewsControllerTest` class from IDE

or

- Use [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like `mvn test`

Analyze log statements on working of entity records insertion, technical audit records insertions and functional audit records insertions.
