plugins {
    id("java")
}

group = "com.brucemelo"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin-bundle:6.2.0")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.hibernate.orm:hibernate-core:7.0.0.Beta1")
    annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen:7.0.0.Beta1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}