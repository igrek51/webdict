FROM node:9 AS frontend-builder
RUN npm install -g @angular/cli@6

COPY src/frontend/src /build/src
COPY src/frontend/angular.json \
    src/frontend/package.json \
    src/frontend/package-lock.json \
    src/frontend/proxy.conf.json \
    src/frontend/tsconfig.json \
    src/frontend/tslint.json \
    /build/
WORKDIR /build
RUN npm install
RUN ng build --prod --output-path=static

FROM maven:3.6.3-jdk-8 AS backend-builder
COPY pom.xml /build/
COPY src /build/src
COPY --from=frontend-builder /build/static /build/src/main/resources/static
WORKDIR /build
RUN mvn package -DskipFrontendBuild

FROM openjdk:8-jdk-alpine
COPY --from=backend-builder /build/target/*.jar /webdict.jar
ENTRYPOINT ["java", "-jar", "/webdict.jar"]
EXPOSE 8081
