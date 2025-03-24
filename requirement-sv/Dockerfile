# Etapa 1: Compilación
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

# Creamos una carpeta para todo el proyecto
WORKDIR /build

# Copiamos el POM padre desde el nivel superior
COPY . .

# Instalamos el POM padre sin compilar nada (solo el descriptor)
RUN mvn install -N

RUN mvn clean install -pl Bibliotecas/request-interceptor,Bibliotecas/file-service-library -am -DskipTests

# Ahora copiamos el código fuente del microservicio
WORKDIR /build/requirement-sv

# Compilamos el microservicio
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

EXPOSE 8083

# Copiamos el .jar generado en la etapa anterior
COPY --from=build /build/requirement-sv/target/requirement-sv-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
