[![Build Status](https://travis-ci.org/aceroni75/gwtboot.svg?branch=master)](https://travis-ci.org/aceroni75/gwtboot)

A sample GWT application with a Spring Boot microservice for backend configured with JAX-RS https://dev.arcbees.com/gwtp/communication/resource-delegates.html)annotations and H2 on-file database. The client uses GWTP (https://dev.arcbees.com/gwtp/) for MVP and type-safe REST communication, and gwtbootstrap3 (https://github.com/gwtbootstrap3/gwtbootstrap3) for widgets.

The application is written completely in Java, and implemented as a multi-module Maven project, with _server_ being the Spring Boot back-end, _client_ the GWT front-end and _shared_ the REST API. The type-safe REST communication is implemented using the _ResourceDelegate_ mechanism from GWTP (https://dev.arcbees.com/gwtp/communication/resource-delegates.html) that automatically creates client REST proxies from JAX-RS annotated interfaces.

To start the application after clone type: 

_mvn clean install_

_mvn -pl server spring-boot:run_

the UI is accessible at _localhost_ on port _8080_
