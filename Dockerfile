#De la imagen que iniciamos
FROM openjdk:11-jre-slim

#Directorio de trabajo
WORKDIR /app

#Copiamos el .jar en el directorio de trabajo
COPY target/*.jar /app

#Exponemos el puerto 9091
EXPOSE 9091

#Comando que se ejecutará una vez ejecutemos el contendor
CMD ["java","-jar","customer-info-0.0.1.jar"]