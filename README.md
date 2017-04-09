# HELLO Docker

This project defines a very simple docker application for tutorial purpose.

It defines a small HTTP server providing access to two page
- /time displaying a small horloge 
- /stop stoping the running executable

## Compilation & Prerequisite

To compile this project, you need to have JDK1.7 and a proper Docker cli installed.
(You may quickly check that > docker -v is returning your a proper display)

In case you don't have a docker client installed, you should look at 
https://docs.docker.com/toolbox/overview/


## Classpath and dependency notes

The project depends on following external projects

* [Docker maven plugin](https://github.com/spotify/docker-maven-plugin/blob/master/README.md)
* [Apache Maven Assembly Plugin](http://maven.apache.org/plugins/maven-assembly-plugin/)
* [grizzly-http-server](https://grizzly.java.net/)
* [Open JDK](wikipedia.org/wiki/OpenJDK)
* [Alpine Linux] (https://www.alpinelinux.org/about)