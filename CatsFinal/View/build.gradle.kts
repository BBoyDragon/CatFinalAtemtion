plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")

    compileOnly ("org.projectlombok:lombok:1.18.22")
    annotationProcessor ("org.projectlombok:lombok:1.18.22")

    implementation ("org.postgresql:postgresql:42.3.1")

    implementation(project(":Application"))
    implementation(project(":Repository"))
}

tasks.test {
    useJUnitPlatform()
}