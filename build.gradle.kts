plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.10.1")
}

javafx {
    version = "25.0.2"
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.media")
}

application {
    mainClass.set("it.unicam.cs.mdpg.rpg129029.app.GameApp")
}


tasks.test {
    useJUnitPlatform()
}