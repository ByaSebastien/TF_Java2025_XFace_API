FROM maven:3.9.11-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .

#Toujours installer les dépendances avant de copier le code source pour optimiser le build de l image grace au cache docker
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# L'entry point est la commande executé au lancement du container (pas a la construction de l'image)
ENTRYPOINT ["java","-jar","app.jar"]
#ENTRYPOINT "java -jar app.jar"