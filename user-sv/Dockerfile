FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Exponer el puerto de la aplicación
EXPOSE 8088

# Usamos el ENTRYPOINT para ejecutar el JAR que se montará como volumen
ENTRYPOINT ["java", "-jar", "app.jar"]
