# Pasos a seguir

## 1) Entrar en el directorio del proyecto (en mi caso):
cd /home/nahuel/eclipse-workspace/superhero

## 2) Generar el archivo .war con Maven
mvn clean package

## 3) Ubicarse dentro de la carpeta target
cd /home/nahuel/eclipse-workspace/superhero/target

## 4) Ejecutar el jar como una aplicación Java
java -jar superhero-0.0.1-SNAPSHOT.jar



### NOTA: El basepath de la aplicación es http://127.0.0.1:8080/api, teniendo un único resource (superheroes): http://127.0.0.1:8080/api/superheroes
## Para ver todos los endpoint's disponibles en el resource, se puede utilizar Swagger accediendo desde: http://127.0.0.1:8080/swagger-ui/index.html

