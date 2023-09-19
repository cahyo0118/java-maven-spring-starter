FROM law0118/spring-starter-libraries:package as MAVEN_BUILD

COPY . .

RUN mvn package -Dmaven.test.skip