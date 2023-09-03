# plugin-template

Template to create a Rushyverse plugin with everything configured

## How to use

### Build

The plugin JAR is generated in the [build/libs](build/libs) folder.

```shell
./gradlew shadowJar
```

The JAR can be moved to the plugins folder of the server to be loaded.

### Test

We're using [Kotest](https://kotest.io/) to write the tests.

The tests are located in the [src/test](src/test) folder.

```shell
./gradlew test
```

### Quality

We're using [Detekt](https://github.com/detekt/detekt) to check the code quality.

```shell
./gradlew detekt
```

## Contributing

Check the [guidelines](CONTRIBUTING.md).
