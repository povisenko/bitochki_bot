//versions
val ktTelegramBotVersion = "1.3.8"
val junitVersion = "5.7.1"

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.20"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()

    maven("https://jitpack.io")
}

dependencies {

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //JUnit 5
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compile("com.github.elbekD:kt-telegram-bot:$ktTelegramBotVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    // Define the main class for the application.
    mainClass.set("bitochok_bot.AppKt")
}

//// config JVM target to 1.8 for kotlin compilation tasks
//tasks.withType<KotlinCompile>().configureEach {
//    kotlinOptions.jvmTarget = "1.8"
//}