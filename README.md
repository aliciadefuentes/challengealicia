Alicia de Fuentes Arteaga
=========================

Coding Challenge - Cards API
---------------------------------

### Build the project

I used [maven](https://maven.apache.org/) for managing the dependencies.

To download all the dependencies and build the project just run the next command:

```bash
$ mvn install
``` 

### Runing the project locally

To run the project locally we will need to define a few environment variables:

* **ZILCH_CARDS_BASICAUTH**: The password required by the user (zilch)

With these variables set run the next command:

```bash
$ mvn spring-boot:run
```

There are a few urls that could be relevant to test:

* **http://localhost:8080/h2** - The H2 Console
* **http://localhost:8080/swagger-ui.html** - The swagger interface
