FROM java:8
COPY ./target/*.jar /app/cart-1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/cart-1-SNAPSHOT.jar"]