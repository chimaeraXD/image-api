import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
	java
	idea
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	id("org.springframework.boot") version "2.7.8"
	id("io.spring.dependency-management") version "1.1.5"
	id("org.openapi.generator") version ("7.6.0")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenLocal()
	mavenCentral()
	maven {
		url = uri("https://repo.spring.io/release")
	}
	maven {
		url = uri("https://repository.jboss.org/maven2")
	}
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.8")
	}
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework:spring-web")
	//implementation("org.springframework:spring-jdbc")
	//implementation("org.springframework.data:spring-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	implementation("org.springframework.cloud:spring-cloud-starter")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	//implementation("jakarta.validation:validation-api:3.0.2")
	implementation("org.hibernate:hibernate-validator:8.0.1.Final")
	implementation("com.mysql:mysql-connector-j")

	implementation("io.github.openfeign:feign-jackson")

	implementation("org.springframework.boot:spring-boot-starter-json")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("io.github.openfeign:feign-httpclient:13.2.1")
	// Swagger
	implementation("io.swagger.core.v3:swagger-annotations:2.2.22")
	implementation("org.springdoc:springdoc-openapi:2.5.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks {

	register("generateServerApi", GenerateTask::class) {
		inputSpec.value("$rootDir/src/apispec/openapi.yml")
		outputDir.value("$rootDir/src/main")
		generatorName.value("spring")
		library.value("spring-boot")
		invokerPackage.value("com.example.image-api.server")
		apiPackage.value("com.example.image-api.server.api")
		modelPackage.value("com.example.image-api.server.model")
		configOptions.value(
			mapOf(
				"dateLibrary" to "java8",
				"interfaceOnly" to "true",
				"openApiNullable" to "false", // works around bug in 5.2.1 that causes missing import statement in generated code
				"sourceFolder" to "/java",
				"serializableModel" to "true",
				"hideGenerationTimestamp" to "true",
				"useTags" to "true"
			)
		)
	}

	register("generateImagaaClient", GenerateTask::class) {
		inputSpec.value("$rootDir/src/apispec/imagaa.yaml")
		outputDir.value("$rootDir/src/main")
		generatorName.value("spring")
		library.value("spring-cloud")
		invokerPackage.value("com.example.image-api.client.imagaa")
		packageName.value("com.example.image-api.client.imagaa")
		apiPackage.value("com.example.image-api.client.imagaa.api")
		modelPackage.value("com.example.image-api.client.imagaa.model")
		configOptions.value(
			mapOf(
				"dateLibrary" to "java8",
				"interfaceOnly" to "true",
				"openApiNullable" to "false", // works around bug ni 5.2.1 that causes missing import statement in generated code
				"sourceFolder" to "/java",
				"configPackage" to "com.example.image-api.client.imagaa.config"
			)
		)
	}
}
