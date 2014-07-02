Analogweb Framework Scala DSL
===============================================

Analogweb is a tiny HTTP orientied framework.
This DSL operated on Scala 2.10.x or 2.11.x

## Example

```scala
import org.analogweb.core.httpserver.HttpServers
import org.analogweb.scala.Analogweb
import java.net.URI

object Run {
  def main(args: Array[String]): Unit = {
    HttpServers.create(URI.create("http://localhost:8080")).start()
  }
}

class Hello extends Analogweb {
  def hello = get("/hello") { request => 
    s"Hello, ${request.query("name")} !"
  }
}
```
