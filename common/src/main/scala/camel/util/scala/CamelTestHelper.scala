package camel.util.scala

import org.apache.camel.Exchange
import org.apache.camel.scala.RichExchange
import org.apache.camel.scala.dsl.builder.{RouteBuilder => SRouteBuilder, ScalaRouteBuilder}

/**
 * todo
 */
trait CamelTestHelper {

  implicit def exchangeWrapper(exchange: Exchange) = new RichExchange(exchange)

  implicit def scalaToJavaBuilder(scalaBuilder: ScalaRouteBuilder) = scalaBuilder.builder
  
  implicit def deprecatedScalaToJavaBuilder(scalaBuilder: SRouteBuilder) = scalaBuilder.builder

}
