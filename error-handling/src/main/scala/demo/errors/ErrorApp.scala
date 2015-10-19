package demo.errors

import org.apache.camel.spring.{Main => SpringMain}

/**
 * todo
 */
object ErrorApp extends App {
  val m = new SpringMain
  m.enableHangupSupport()
  m.run()

}
