de# Fabric Demo

## Setup the fabric

1. Create a new fabric

```shell
fabric:create --new-user fadmin \
        --new-user-password masterkey \
        --generate-zookeeper-password \
        --resolver localip \
        --wait-for-provisioning
```

2. create two child containers

```shell
fabric:container-create-child root node 2
```

## Create the profiles

1. The client (plus deploy to node1)

```shell
fabric:profile-create --parents feature-camel fabric-demo-client
fabric:profile-edit --bundles mvn:com.edc4it/fabric-demo-client/0.1.0-SNAPSHOT fabric-demo-client
fabric:profile-edit --features camel-jetty,fabric-camel fabric-demo-client

fabric:container-add-profile node1 fabric-demo-client
```

1. The base for the http services

```shell
fabric:profile-create --parents feature-camel fabric-demo-jetty-base
fabric:profile-edit --bundles mvn:com.edc4it/fabric-jetty/0.1.0-SNAPSHOT fabric-demo-jetty-base
fabric:profile-edit --features camel-jetty,fabric-zookeeper fabric-demo-jetty-base
```

2. http service A

```shell
fabric:profile-create --parents fabric-demo-jetty-base fabric-demo-jetty-a
fabric:profile-edit -p fabric.demo.jetty/port=9092 fabric-demo-jetty-a 
fabric:profile-edit -p fabric.demo.jetty/suffix=home.com fabric-demo-jetty-a

fabric:container-add-profile node2 fabric-demo-jetty-a
```
2. http service B

```shell
fabric:profile-create --parents fabric-demo-jetty-base fabric-demo-jetty-b
fabric:profile-edit -p fabric.demo.jetty/port=9093 fabric-demo-jetty-b
fabric:profile-edit -p fabric.demo.jetty/suffix=work.com fabric-demo-jetty-b

fabric:container-add-profile node1 fabric-demo-jetty-b
```

## Test

Tail the child containers' log files 

```shell
tail -F instances/node*/data/log/karaf.log | grep test
```

Craete some files and watch the log

```shell
echo -n test > /tmp/camel-box/test.txt
```

