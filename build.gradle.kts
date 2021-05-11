version = "1.0.1"

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    id("com.palantir.docker") version "0.26.0"
}

docker {
    name = "ghcr.io/cactuscrew/${project.name}:${project.version}"
    tag("latest", "ghcr.io/cactuscrew/${project.name}:latest")
    tag("versioned", name)
    files("build/libs/bitochok_bot-fat-${project.version}.jar")
    buildArgs(mapOf("VERSION" to "${project.version}"))
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

//dependecies versions
val ktTelegramBotVersion = "1.3.8"
val junitVersion = "5.7.1"


dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //JUnit 5
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.github.elbekD:kt-telegram-bot:$ktTelegramBotVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

val fatJar = task("fatJar", type = Jar::class) {
    base.archivesBaseName = "${project.name}-fat"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "${project.name}"
        attributes["Implementation-Version"] = "${project.version}"
        attributes["Main-Class"] = "bitochok_bot.AppKt"
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "fatJar" {
        dependsOn(test)
    }
    "build" {
        dependsOn(fatJar)
    }
    "docker" {
        dependsOn(build)
    }
    "dockerTag" {
        dependsOn(docker)
    }
    "dockerPush" {
        dependsOn(dockerTag)
    }
}