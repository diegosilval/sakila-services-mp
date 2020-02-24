# Build
mvn clean package && docker build -t com.vasslatam/sakila-services .

# Build sin pruebas Arquillian
mvn clean package -DskipTests


# RUN
docker rm -f sakila-services || true && docker run -d -p 8080:8080 -p 4848:4848 --name sakila-services com.vasslatam/sakila-services 

# RUN Local Payara Micro
mvn clean package -DskipTests  payara-micro:start