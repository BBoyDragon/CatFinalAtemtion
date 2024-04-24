plugins {
    id("java")
    id("org.springframework.boot") version "3.1.6"

}
apply(plugin = "io.spring.dependency-management")


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.mockito:mockito-core:3.11.2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")

    compileOnly ("org.projectlombok:lombok:1.18.22")
    annotationProcessor ("org.projectlombok:lombok:1.18.22")

    implementation ("org.postgresql:postgresql:42.3.1")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.security:spring-security-test")
}

tasks.test {
    useJUnitPlatform()
}