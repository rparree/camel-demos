
## Instructions for the **osgi-spring** example on Karaf
 
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

 3. Start the Fuse Runtime, install JMS and install the demo OSGI bundle (`/home/me/course/camel-demos/osgi-spring/target/scala-2.10/osgi-spring_2.10-1.1.jar`)

 ```bash
 ~/opt/jboss-fuse-6.0.0$ bin/fuse
 Please wait while JBoss Fuse is loading...
 100% [========================================================================]
 ...
 JBossFuse:karaf@root> features:install camel-jms
 JBossFuse:karaf@root> osgi:install -s "file:/.../scala-2.10/osgi-spring_2.10-1.1.jar"
 Bundle ID: 234

 ```

Drop a file in the dropbox and watch the log file `log:tail`  (or `tail data/log/fuse.log` outside Fuse)

### Some extra tips
If you want to change the project and after building "reload" it in Fuse use: `osgi:update 234` (replace 234 with the bundle id you received when installing the bundle)

If you would add Scala code make sure you install the Scala Bundle in Fuse:

```bash
 JBossFuse:karaf@root> osgi:install "mvn:org.scala-lang/scala-library/2.10.3"
 ```

## Creating and deploying a fabric profile

 1. First let's create the fabric container
  ```bash
  JBossFuse:karaf@root> fabric:create 
  Using specified zookeeper password:smx
  ```

 2.  Then create a new profile (parent is `camel-jms` as this demo uses JMS and AMQ)
  
  ```bash
  JBossFuse:karaf@root> fabric:profile-create --parents camel-jms fabric-demo
  ```

 3. Then add the OSGI bundle to the profile (referencing the bundle created using SBT)

 ```bash
 JBossFuse:karaf@root> fabric:profile-edit --bundles "file:/.../osgi-spring_2.10-1.1.jar" fabric-demo
 ```

  4. Then deploy the profile to the container (using root container here)

  ```bash
  JBossFuse:karaf@root> fabric:container-add-profile root fabric-demo
  ```

### Tip: Install the "Fabric Management Console

Install the FMC and an admin user

```bash
JBossFuse:karaf@root> fabric:container-add-profile  root fmc
JBossFuse:karaf@root> jaas:realms 
Index Realm                Module Class                                                                    
    1 karaf                org.apache.karaf.jaas.modules.properties.PropertiesLoginModule                  
    2 karaf                org.apache.karaf.jaas.modules.publickey.PublickeyLoginModule                    
    3 karaf                org.fusesource.fabric.jaas.ZookeeperLoginModule        
JBossFuse:karaf@root> jaas:manage --index 3
JBossFuse:karaf@root> jaas:useradd jenny masterkey
JBossFuse:karaf@root> jaas:roleadd jenny admin
JBossFuse:karaf@root> jaas:update
```

Then go to [http://localhost:8181/](http://localhost:8181/) and login with jenny/masterkey


