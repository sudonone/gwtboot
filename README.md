[![Build Status](https://travis-ci.org/aceroni75/gwtboot.svg?branch=master)](https://travis-ci.org/aceroni75/gwtboot)

A sample GWT application with a Spring Boot microservice for backend configured with JAX-RS annotations and H2 on-file database. The client uses GWTP (https://dev.arcbees.com/gwtp/) for MVP and type-safe REST communication, and gwtbootstrap3 (https://github.com/gwtbootstrap3/gwtbootstrap3) for widgets.

To start the application after clone type: 

mvn clean install
mvn -pl server spring-boot:run

the UI is accessible from http://localhost:8080
