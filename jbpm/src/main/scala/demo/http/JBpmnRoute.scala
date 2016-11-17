    package demo.http

    import java.net.URL

    import org.apache.camel.Exchange
    import org.apache.camel.component.jbpm.JBPMConstants
    import org.apache.camel.scala.dsl.builder.RouteBuilder
    import org.kie.api.runtime.manager.RuntimeEngine
    import org.kie.api.runtime.process.ProcessInstance
    import org.kie.remote.client.api.RemoteRuntimeEngineFactory
    import org.kie.remote.jaxb.gen.FindVariableInstancesCommand
    import org.kie.services.client.serialization.jaxb.impl.audit.JaxbVariableInstanceLog

    import scala.collection.JavaConverters._

    /**
      * todo
      */
    class JBpmnRoute extends RouteBuilder {

        "direct:jbpmRoute" ==> {
          setHeader(JBPMConstants.PROCESS_ID, constant("hiring"))
          process((e: Exchange) => {
            e.getIn().setHeader(JBPMConstants.PARAMETERS, e.in[Map[String, AnyRef]].asJava)
          })

          to("jbpm:http://localhost:8080/jbpm-console?userName=admin&password=admin"
            + "&deploymentId=org.jbpm:HR:1.0&operation=startProcess")
          process((e: Exchange) => {
            val rte: RuntimeEngine = RemoteRuntimeEngineFactory.newRestBuilder()
              .addUrl(new URL("http://localhost:8080/jbpm-console/")).addUserName("admin").addPassword("admin")
              .addDeploymentId("org.jbpm:HR:1.0").build()

            val cmd: FindVariableInstancesCommand = new FindVariableInstancesCommand()
            cmd.setProcessInstanceId(e.in[ProcessInstance].getId)
            cmd.setVariableId("name")
            val result = rte.getKieSession.execute(cmd).asInstanceOf[java.util.List[AnyRef]].asScala
            e.in  = result.head.asInstanceOf[JaxbVariableInstanceLog].getValue
          })
          log("value of name ${body}")
        }

    }
