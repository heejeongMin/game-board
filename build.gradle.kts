import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.9.10"
	kotlin("plugin.jpa") version "1.9.10"
	kotlin("kapt")  version "1.9.10"


}

group = "com.pancho"
version = "0.0.3-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {

	/**
	 * Spring
	 */
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")

	/**
	 * Kotlin
	 */
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	/**
	 * Database
	 */
	implementation("mysql:mysql-connector-java:8.0.30")
	implementation ("org.liquibase:liquibase-core")


	/**
	 * Logging
	 */
	implementation("io.github.microutils:kotlin-logging:2.1.23")
//	implementation("ch.qos.logback:logback-classic:1.2.6")

	/**
	 * others
	 */
	implementation("io.jsonwebtoken:jjwt:0.9.1")


	/**
	 * Test
	 */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


