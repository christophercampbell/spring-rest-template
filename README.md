# spring-rest-template
Template for Spring RESTful API 

# Run from source

```bash
$ mvn jetty:run
```

Listens on `http://localhost:9000`

# Configuration

Default configuration is taken from the `application.properties` supplied on the classpath. 

Values may be overridden or added by supplying a configuration file and passing `-Dconfig.file=/path/to/file`

# Debug

```bash
$ mvnDebug jetty:run
```

Waits for debugger to connect remotely to dt_socket on port 8000

# Customize debug options

```bash
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
```

