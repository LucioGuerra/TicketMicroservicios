FROM eclipse-temurin:17-jdk AS runtime

# Establece el directorio de trabajo
WORKDIR /app

COPY target/traceability-sv-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponer el puerto de la aplicación
EXPOSE 8082

# Usamos el ENTRYPOINT para ejecutar el JAR que se montará como volumen
ENTRYPOINT ["java", "-jar", "app.jar"]
