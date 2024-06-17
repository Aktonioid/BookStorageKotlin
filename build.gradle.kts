plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		var languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.3.0")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.apache.poi:poi-ooxml:5.2.5")
	implementation("org.apache.poi:poi:5.2.5")

	implementation("org.hibernate.orm:hibernate-core:6.4.0.Final")
//	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
//	implementation("jakarta.validation:jakarta.validation-api:3.1.0")
//	implementation("org.glassfish.expressly:expressly:5.0.0")
//	implementation("org.hibernate.validator:hibernate-validator-annotation-processor:8.0.1.Final")


	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.hibernate.orm:hibernate-hikaricp:6.4.0.Final")

	implementation("com.zaxxer:HikariCP:5.1.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")


	// swagger
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.22")


	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
