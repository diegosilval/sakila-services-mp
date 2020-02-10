FROM airhacks/glassfish
COPY ./target/sakila-services.war ${DEPLOYMENT_DIR}
