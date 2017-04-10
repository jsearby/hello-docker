# HELLO Docker

This project defines a very simple docker application for tutorial purpose.

It defines a small HTTP server providing access to two pages
- http://127.0.0.1:8080/time displaying a small horloge 
- http://127.0.0.1:8080/stop stoping the running executable

This server provides a very basic logging in 
- console
- /log/mylogfile.log


## Compilation & Prerequisites

To compile this project, you need to have JDK >= 1.7 and a proper Docker cli installed.
> mvn clean package

###Troubleshooting

#### Failed to execute goal com.spotify:docker-maven-plugin
It means you are missing a proper Docker installation.
- Either you don't have installed Docker at all (check with a "docker -v" command)
- Either your docker engine is not running (then restart it)

For more info about how to install docker engine, you should look at 
https://docs.docker.com/toolbox/overview/

#### I'm working with minikube, still my docker is not running
You probably have forgotten to re-setup your environment parameter for your docker cli 
> minikube start
> FOR /f "tokens=*" %i IN ('minikube docker-env') DO %i  

#### I'm working with docker-toolbox, still my docker is not running
You probably have forgotten to re-setup your environment parameter for your docker cli 
> docker-machine start dev 
> FOR /f "tokens=*" %i IN ('docker-machine env --shell cmd dev') DO %i

## Classpath and dependency notes

The project depends on following external projects

* [Docker maven plugin](https://github.com/spotify/docker-maven-plugin/blob/master/README.md)
* [Apache Maven Assembly Plugin](http://maven.apache.org/plugins/maven-assembly-plugin/)
* [grizzly-http-server](https://grizzly.java.net/)
* [Open JDK](wikipedia.org/wiki/OpenJDK)
* [Alpine Linux] (https://www.alpinelinux.org/about)