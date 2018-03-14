# spring-rest-template

Template for Spring RESTful API 

### Build

```bash
$ mvn package
```

### Run from source

```bash
$ mvn jetty:run
```

Starts jetty listening at: `http://localhost:9000`

# Configuration

Default configuration is taken from the `application.properties` supplied on the classpath. 

Values may be overridden or added by supplying a configuration file and passing `-Dconfig.file=/path/to/file`

Or specific values by specifing them by name `-Dsome.config.key=someValue`

# Debug

Maven has a debug option that starts a build or execution with java remote debugging.

```bash
$ mvnDebug jetty:run
```

By default the process pauses and waits for a debugger to connect remotely on dt_socket port 8000. Export java debug options if the defaults are unsuitable.

```bash
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
```

