FROM  lenacloud/lena-cluster:1.3.4.7-EN10-ubuntu22.04-jdk17-openjdk

ENV LENA_HOME /usr/local/lena
RUN mkdir -p $LENA_HOME
RUN mkdir -p $LENA_HOME/temp

COPY target/ROOT-1.0.0.war $LENA_HOME/ROOT.war

RUN chmod u+x $LENA_HOME/*.sh

WORKDIR $LENA_HOME
CMD ["./docker-entrypoint.sh"]