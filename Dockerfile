#FROM maven:alpine
FROM frekele/maven

RUN mkdir /repository
ENV SECRET_SEED ""

WORKDIR /app

# Copy all source files
COPY pom.xml /app/pom.xml

COPY peer-store-sqlite/pom.xml /app/peer-store-sqlite/pom.xml
COPY peer-core/pom.xml /app/peer-core/pom.xml
COPY peer-eon/pom.xml /app/peer-eon/pom.xml
COPY peer-eon-app/pom.xml /app/peer-eon-app/pom.xml

# Cache depedences
RUN mvn -Dmaven.repo.local=/repository dependency:go-offline package tomcat7:help clean --fail-never

COPY peer-eon-app/src /app/peer-eon-app/src
COPY peer-store-sqlite/src /app/peer-store-sqlite/src
COPY peer-core/src /app/peer-core/src
COPY peer-eon/src /app/peer-eon/src

# Compile files
RUN mvn -o -Dmaven.repo.local=/repository package install -DskipTests

# Run tests
RUN mvn -Dmaven.repo.local=/repository -DfailIfNoTests=false test
# Run integration tests
RUN mvn -Dmaven.repo.local=/repository -Dtest="**/*TestIT.java" -DfailIfNoTests=false test

EXPOSE 9443

ENTRYPOINT mvn -o -Dmaven.repo.local=/repository tomcat7:run -DSECRET_SEED=$SECRET_SEED

# For debug
# ENTRYPOINT /bin/bash