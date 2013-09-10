# tmongo


tmongo is a small java app to stream tweets based on certain predefined keywords and store them in a [MongoDB](http://www.mongodb.org/ "MongoDB") collection.


## How do I use it?

You need 
- Java
- Maven
- MongoDB

You can clone the source from and compile using maven: 
    http://github.com/xetorthio/jedis/downloads

```shell
git clone http://
```
Edit App.java and change the credentials to match that of yours.

```shell
mvn clean dependency:copy-dependencies package
java -cp target/tmongo-1.0-SNAPSHOT.jar com.nighthawkapps.tmongo.App 
```