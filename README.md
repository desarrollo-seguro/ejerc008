
Con seguridad activada
mvn  spring-boot:run

Sin seguridad activada
mvn -Dspring-boot.run.arguments="--spring.profiles.active=no-security" spring-boot:run