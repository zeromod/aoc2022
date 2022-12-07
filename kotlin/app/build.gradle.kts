plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
}
application {
    mainClass.set("aoc2022.AppKt")
}
