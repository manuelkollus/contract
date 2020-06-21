# Contract
[![JDK](https://img.shields.io/badge/java-SE8-blue.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![Latest](https://img.shields.io/badge/latest-v1.0-blue.svg)](https://github.com/manuelkollus/contract)

Generates preconditions in your Java code. This is solved by the library 'AspectJ', 
because the advantage of AOP (aspect-oriented programming) is that it offers versioning 
independence of the Java compiler. 

## Examples
The following source text shows how to write contracts.

```java
  @Contract(
    error = ErrorModel.STANDARD // show error message with description
    checks = {
      NotNullCheck.class
    }
  )
  public void onlyStringContractCheck(String abc, int number) { ... }

  @Contract(
    error = ErrorModel.RICHER, // show only error message
    checks = {
      UnsignedCheck.class
    }
  )
  public void onlyIntContractCheck(String abc, int number) { ... }
``` 

## Integration
The following text shows how common BuildTools can be used to add the contract modules
to your dependencies.

##### Maven
```xml
<repositories>
  <repository>
    <id>contract</id>
    <url>https://maven.pkg.github.com/manuelkollus/contract</url> 
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>io.github.manuelkollus</groupId>
    <artifactId>contract</artifactId>
    <version>1.0.1</version>
  </dependency>
</dependencies>
```

##### Gradle
```groovy
repositories {
  mavenCentral()
  maven {
    name = "contract"
    url = uri("https://maven.pkg.github.com/manuelkollus/contract")
  }
}

dependencies {
  compile 'io.github.manuelkollus:contract:1.0.1'
}
```
