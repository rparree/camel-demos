# Fabric Demo

## Setup

### nexus

The demos publish to a nexus repository:

```
docker run -d -p 8081:8081 --name nexus --volumes-from nexus-data sonatype/nexus
```

With one time:

```
docker run -d --name nexus-data sonatype/nexus echo "data-only container for Nexus"
docker stop nexus-data
```

### Fuse

See the docker image here https://github.com/rparree/jboss-fuse-docker

```
docker run -Pd -p 8101:8101 -p 61616:61616 \
  --name fuse --link nexus \
    rparree/jboss-fuse-full-admin
```

Publish the projects (`fabric-jetty` and `fabric-demo-client`) into nexus

## Setup the fabric

1. Create a new fabric

```shell
fabric:create
```

2. create two child containers

```shell
container-create-child --profile jboss-fuse-full <root> node 2
fabric:container-create-child root node 2
```

## Create the profiles

1. The client (plus deploy to node1)

```shell
fabric:profile-create  fabric-demo-client
fabric:profile-edit --bundles mvn:com.edc4it/fabric-demo-client/1.0.0-SNAPSHOT fabric-demo-client
fabric:profile-edit --features camel-jetty fabric-demo-client
fabric:profile-edit --features fabric-camel fabric-demo-client
profile-edit --pid io.fabric8.agent/org.ops4j.pax.url.mvn.repositories="http://${nexus.addr}:${nexus.port}/repository/snapshots@id=nexus.snapshot.repo@snapshots" --append fabric-demo-client

fabric:container-add-profile node1 fabric-demo-client
```

1. The base for the http services

```shell
fabric:profile-create fabric-demo-jettybase
fabric:profile-edit --bundles mvn:com.edc4it/fabric-jetty/1.0.0-SNAPSHOT fabric-demo-jettybase
fabric:profile-edit --features fabric-zookeeper fabric-demo-jettybase
fabric:profile-edit --features camel-jetty fabric-demo-jettybase
profile-edit --pid io.fabric8.agent/org.ops4j.pax.url.mvn.repositories="http://${nexus.addr}:${nexus.port}/repository/snapshots@id=nexus.snapshot.repo@snapshots" --append fabric-demo-jettybase

```

2. http service A

```shell
fabric:profile-create --parents fabric-demo-jettybase fabric-demo-jettya
fabric:profile-edit -p fabric.demo.jetty/port=9092 fabric-demo-jettya
fabric:profile-edit -p fabric.demo.jetty/suffix=home.com fabric-demo-jettya

fabric:container-add-profile node2 fabric-demo-jettya
```
2. http service B

```shell
fabric:profile-create --parents fabric-demo-jettybase fabric-demo-jettyb
fabric:profile-edit -p fabric.demo.jetty/port=9093 fabric-demo-jettyb
fabric:profile-edit -p fabric.demo.jetty/suffix=work.com fabric-demo-jettyb

fabric:container-add-profile node1 fabric-demo-jettyb
```

## Test

Tail the child containers' log files

```shell
tail -F instances/node*/data/log/fuse.log | grep test
```

Craete some files and watch the log

```shell
echo -n test > /tmp/camel-box/test.txt
```
