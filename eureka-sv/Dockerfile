FROM eclipse-temurin:17-jdk AS runtime

# Establece el directorio de trabajo
WORKDIR /app

# Exponer el puerto de la aplicación
EXPOSE 8761

# Usamos el ENTRYPOINT para ejecutar el JAR que se montará como volumen
ENTRYPOINT ["java", "-jar", "app.jar"]
