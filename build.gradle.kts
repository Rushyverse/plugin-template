import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    embeddedKotlin("jvm")
    embeddedKotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    jacoco
}

detekt {
    // Allows having different behavior for CI.
    // When building a branch, we want to fail the build if detekt fails.
    // When building a PR, we want to ignore failures to report them in sonar.
    val envIgnoreFailures = System.getenv("DETEKT_IGNORE_FAILURES")?.toBooleanStrictOrNull() ?: false
    ignoreFailures = envIgnoreFailures

    config.from(file("config/detekt/detekt.yml"))
}

jacoco {
    reportsDirectory.set(file("${layout.buildDirectory.get()}/reports/jacoco"))
}

repositories {
    mavenCentral()
    mavenLocal()

    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    val paperVersion = "1.20.1-R0.1-SNAPSHOT"
    val rushyApiVersion = "2.1.0"
    val commandApiVersion = "9.1.0"
    val coroutineVersion = "1.7.3"
    val mockBukkitVersion = "3.33.0"
    val junitVersion = "5.10.0"
    val mockkVersion = "1.13.7"
    val kotestVersion = "5.7.1"
    val koinVersion = "3.4.3"

    compileOnly(kotlin("stdlib"))

    "io.papermc.paper:paper-api:$paperVersion".let {
        compileOnly(it)
        testImplementation(it)
    }

    "com.github.Rushyverse:api:$rushyApiVersion".let {
        compileOnly(it)
        testImplementation(it)
    }

    // CommandAPI framework
    compileOnly("dev.jorel:commandapi-bukkit-core:$commandApiVersion")
    compileOnly("dev.jorel:commandapi-bukkit-kotlin:$commandApiVersion")

    // Tests
    testImplementation(kotlin("test-junit5"))
    testImplementation("com.github.seeseemelk:MockBukkit-v1.20:$mockBukkitVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-json:$kotestVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("io.insert-koin:koin-test:$koinVersion") {
        exclude("org.jetbrains.kotlin", "kotlin-test-junit")
    }
    testImplementation("io.mockk:mockk:$mockkVersion")
}

val javaVersion get() = JavaVersion.VERSION_17
val javaVersionString get() = javaVersion.toString()
val javaVersionInt get() = javaVersionString.toInt()

kotlin {
    jvmToolchain(javaVersionInt)

    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = javaVersionString
    }

    withType<JavaCompile> {
        sourceCompatibility = javaVersionString
        targetCompatibility = javaVersionString
    }

    test {
        useJUnitPlatform()
    }

    shadowJar {
        archiveClassifier.set("")
    }
}
