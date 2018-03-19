FROM java:8
ADD /target/treinamento.jar treinamento.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar treinamento.jar