plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("javax.annotation:javax.annotation-api:1.3.2")


	implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
	// Spring Data JPA - for database interactions
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Spring Web - for REST API and web endpoints
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Spring Security - for authentication and authorization
	implementation("org.springframework.boot:spring-boot-starter-security")

	// JWT (JSON Web Token) dependencies
	implementation("io.jsonwebtoken:jjwt-api:0.11.5") // API interface for JWT
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")  // Implementation
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5") // Jackson support for JWT

	// Validation - for validating request and response bodies
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// OpenAPI - for API documentation (Swagger-like functionality)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	// Lombok - for reducing boilerplate code
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Spring DevTools - for faster development experience
	developmentOnly("org.springframework.boot:spring-boot-devtools") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging") // Excluding default logging
	}

	// MySQL connector for database connection
	runtimeOnly("mysql:mysql-connector-java:8.0.33")

	// H2 Database - for testing
	testImplementation("com.h2database:h2")

	// Testing - for unit and integration tests
	testImplementation("org.springframework.boot:spring-boot-starter-test") // includes JUnit, Mockito, etc.
	testImplementation("org.springframework.security:spring-security-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
