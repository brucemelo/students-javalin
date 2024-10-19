plugins {
    id("java")
}

group = "com.brucemelo.app"

repositories {
    mavenCentral()
}

val javalinVersion = "6.3.0"
val lpmbokVersion = "1.18.34"
val postgresqlVersion = "42.7.3"
val hibernateVersion = "7.0.0.Beta1"
val junitVersion = "5.10.3"

dependencies {
    implementation("io.javalin:javalin-bundle:$javalinVersion")
    compileOnly("org.projectlombok:lombok:$lpmbokVersion")
    annotationProcessor("org.projectlombok:lombok:$lpmbokVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.hibernate.orm:hibernate-core:$hibernateVersion")
    annotationProcessor("org.hibernate.orm:hibernate-jpamodelgen:$hibernateVersion")
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}