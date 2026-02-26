import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
	java
	id("org.springframework.boot") version "4.0.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.asciidoctor.jvm.convert") version "4.0.5"
    id("org.openapi.generator") version "7.12.0"
    id("maven-publish")
}

group = "com.bank"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    //--------------------- Spring Boots -----------------------
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-liquibase")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server:1.3.0")
    //---------------------- JWT Support -----------------------
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")
	//------------------------ Lombok --------------------------
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
    //-------------------------- DB ----------------------------
    runtimeOnly("org.postgresql:postgresql")
    //----------------------- MapStruct ------------------------
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    //--------------------- Documentation ----------------------
    implementation("io.swagger.core.v3:swagger-annotations:2.2.19")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    //------------------------- Tests --------------------------
	testImplementation("org.springframework.boot:spring-boot-restdocs")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-redis-test")
	testImplementation("org.springframework.boot:spring-boot-starter-liquibase-test")
	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testImplementation("org.testcontainers:testcontainers-junit-jupiter")
	testImplementation("org.testcontainers:testcontainers-postgresql")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Используем layout.buildDirectory вместо deprecated buildDir
val generatedUserDir = layout.buildDirectory.dir("generated/user")
val generatedCardDir = layout.buildDirectory.dir("generated/card")

// Конфигурация для генерации Auth API
val generateUserApi by tasks.registering(GenerateTask::class) {
    generatorName.set("spring")
    inputSpec.set("$rootDir/src/main/resources/swagger/user_v1.yaml")
    outputDir.set(generatedCardDir.map { it.asFile.absolutePath })
    apiPackage.set("com.bank.manager.user.api")
    invokerPackage.set("com.bank.manager.user.invoker")
    modelPackage.set("com.bank.manager.user.api.model")

    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "useSpringBoot3" to "true",
            "useJakartaEe" to "true",
            "interfaceOnly" to "true",
            "useBeanValidation" to "true",
            "performBeanValidation" to "true",
            "skipDefaultInterface" to "true",
            "useTags" to "true",
            "library" to "spring-boot",
            "delegatePattern" to "false",
            "hideGenerationTimestamp" to "true",
            "openApiNullable" to "false",
            "useLombok" to "true",
			"useResponseEntity" to "false"
        )
    )

    importMappings.set(
        mapOf(
            "LocalDateTime" to "java.time.LocalDateTime",
            "MaskedCardNumber" to "java.lang.String"
        )
    )

    typeMappings.set(
        mapOf(
            "date-time" to "java.time.LocalDateTime",
            "decimal" to "java.math.BigDecimal"
        )
    )
}

// Конфигурация для генерации Card API
val generateCardApi by tasks.registering(GenerateTask::class) {
    generatorName.set("spring")
    inputSpec.set("$rootDir/src/main/resources/swagger/card_v1.yaml")
    outputDir.set(generatedCardDir.map { it.asFile.absolutePath })
    apiPackage.set("com.bank.manager.card.api")
    invokerPackage.set("com.bank.manager.card.invoker")
    modelPackage.set("com.bank.manager.card.api.model")

    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "useSpringBoot3" to "true",
            "useJakartaEe" to "true",
            "interfaceOnly" to "true",
            "useBeanValidation" to "true",
            "performBeanValidation" to "true",
            "skipDefaultInterface" to "true",
            "useTags" to "true",
            "library" to "spring-boot",
            "delegatePattern" to "false",
            "hideGenerationTimestamp" to "true",
            "openApiNullable" to "false",
            "useLombok" to "true",
			"useResponseEntity" to "false"
        )
    )

    importMappings.set(
        mapOf(
            "LocalDateTime" to "java.time.LocalDateTime",
            "MaskedCardNumber" to "java.lang.String"
        )
    )

    typeMappings.set(
        mapOf(
            "date-time" to "java.time.LocalDateTime",
            "decimal" to "java.math.BigDecimal"
        )
    )
}

// Добавляем оба сгенерированных исходника в компиляцию
sourceSets {
    main {
        java {
            srcDir(generatedUserDir.map { it.asFile.absolutePath + "/src/main/java" })
            srcDir(generatedCardDir.map { it.asFile.absolutePath + "/src/main/java" })
        }
    }
}

tasks.clean {
    delete(generatedUserDir, generatedCardDir)
}

tasks.compileJava {
    dependsOn(generateUserApi, generateCardApi)
}

// Отключаем стандартную задачу плагина, если не нужна
tasks.named("openApiGenerate") {
    enabled = false
}

// Задачи для отдельной генерации
tasks.register("generateAllApis") {
    dependsOn(generateUserApi, generateCardApi)
    description = "Generate all APIs from OpenAPI specifications"
}

tasks.register("generateUserApiOnly") {
    dependsOn(generateUserApi)
    description = "Generate only Auth API"
}

tasks.register("generateCardApiOnly") {
    dependsOn(generateCardApi)
    description = "Generate only Card API"
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// tasks.test {
// 	outputs.dir(project.extra["snippetsDir"]!!)
// }

// tasks.asciidoctor {
// 	inputs.dir(project.extra["snippetsDir"]!!)
// 	dependsOn(tasks.test)
// }
