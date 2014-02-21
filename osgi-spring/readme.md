
## Instructions for the **osgi-spring** example
 
 1. First create the OSGI Bundle using SBT
  Before you build, you might want to change the location of in the file inbox (by default it is set to `file:/tmp/camel/in` in `spring-camel-context.xml`)
 
 ```bash
 me@my-linux > sbt
 ...
 > project osgi-spring
 [info] Set current project to osgi-spring 
 > osgiBundle
 [warn] bnd: Unused Private-Package instructions ,..
 [success] Total time: 1 s, completed 
 > exit
 ```
 
 2. Set the JMS username and password in Fuse for AMQ: add `smx=smx` to `etc/user.properties`

 ```bash
 me@my-linux: jboss-fuse-6.0.0$ echo -e "\nsmx=smx" >> etc/users.properties
 ```

 3. Start the Fuse Runtime, install JMS and install the demo OSGI bundle (`/home/me/course/camel-demos/osgi-spring/target/scala-2.10/osgi-spring_2.10-1.1.jar`)

 ```bash
 me@my-linux: ~/opt/jboss-fuse-6.0.0$ bin/fuse
 Please wait while JBoss Fuse is loading...
 100% [========================================================================]
 ...
 JBossFuse:karaf@root> features:install camel-jms
 JBossFuse:karaf@root> osgi:install "file:/.../scala-2.10/osgi-spring_2.10-1.1.jar"
 Bundle ID: 234

 ```

Drop a file in the dropbox and watch the log file `log:tail`  (or `tail data/log/fuse.log` outside Fuse)

### Some extra tips
If you want to change the project and after building "reload" it in Fuse use: `osgi:update 234` (replace 234 with the bundle id you received when installing the bundle)

If you would add Scala code make sure you install the Scala Bundle in Fuse:

```bash
 JBossFuse:karaf@root> osgi:install "mvn:org.scala-lang/scala-library/2.10.3"
 ```
