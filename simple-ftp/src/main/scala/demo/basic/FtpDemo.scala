package demo.basic

import org.apache.camel.impl.DefaultCamelContext

import java.nio.file.{FileSystems, Files}
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.component.file.remote.FtpComponent
import org.apache.camel.processor.idempotent.FileIdempotentRepository
import java.io.File


/**
 * todo  
 */
object FtpDemo extends  App {

  val context = new DefaultCamelContext()

  context.addRoutes(new org.apache.camel.scala.dsl.builder.RouteBuilder{
    "ftp://ftp.bls.gov/pub/special.requests/cpi/?include=cpiri\\d{4}.txt".
      idempotentConsumer (header("CamelFileName")).repository(FileIdempotentRepository.fileIdempotentRepository(new File("repo.dat"))).
      log ("received file ${file:name}")
  })

  context.start()
  Thread.sleep(50000)
  context.stop()

}
