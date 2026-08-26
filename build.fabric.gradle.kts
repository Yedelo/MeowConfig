@file:OptIn(StonecutterExperimentalAPI::class)

import dev.kikugie.stonecutter.StonecutterExperimentalAPI
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.invoke
import kotlin.reflect.KProperty
import me.modmuss50.mpp.ReleaseType

plugins {
	id("dev.kikugie.loom-back-compat")
	id("me.modmuss50.mod-publish-plugin")
}

repositories {
	google()
	maven("https://maven.terraformersmc.com/releases/")
	maven("https://maven.isxander.dev/releases")
	maven("https://repo.polyfrost.org/releases")
	maven("https://repo.polyfrost.org/snapshots")
	maven("https://api.modrinth.com/maven") {
		content { includeGroup("maven.modrinth") }
	}
}

// in stonecutter.gradle.kts
class CommonProperty<T> {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T = (rootProject.extra[sc.current.project] as Map<String, Any?>)[property.name] as T
}
val modName: String by project
val modId: String by project
val modDescription: String by project
val modIcon: String by project
val versionType: String by project
val license: String by project
val modrinthId: String by project

val javaVersion by CommonProperty<JavaVersion>()
val oneconfigVersion by CommonProperty<String>()
val rangedVersion by CommonProperty<Boolean>()
val maxMc by CommonProperty<String?>()
val finalFileName by CommonProperty<String>()
val modrinthReadme by CommonProperty<String>()

dependencies {
	minecraft("com.mojang:minecraft:${sc.current.version}")
	loomx.applyMojangMappings()
	modImplementation("net.fabricmc:fabric-loader:${property("versions.fabricLoader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("versions.fabricApi")}")

	implementation("org.polyfrost.oneconfig:${sc.current.version}-fabric:$oneconfigVersion")
	modApi("com.terraformersmc:modmenu:${property("versions.modMenu")}")

}

loom {
	runConfigs.all {
		runDir = "../../run"
	}
}

tasks {
	processResources {
		fun MutableMap<String, String>.register(key: String, value: String) {
			inputs.property(key, value)
			set(key, value)
		}

		fun target(version: String) = ">=$version"
		val props = buildMap {
			register("modName", modName)
			register("modId", modId)
			register("modDescription", modDescription)
			register("modIcon", modIcon)
			register("version", version.toString())
			register("license", license)
			register("java", target(javaVersion.majorVersion))
			register("fabricLoader", target(sc.properties["versions.fabricLoader"]))
			val minecraftDependency =
				if (rangedVersion) ">=${sc.current.version} <=${maxMc}" else sc.current.version
			register("minecraft", minecraftDependency)
			register("oneconfig", target(oneconfigVersion))
		}
		filesMatching(listOf("fabric.mod.json")) { expand(props) }

        outputs.upToDateWhen { false }
	}

	register<Copy>("buildAndCollect") {
		group = "build"

		from(loomx.modJar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs"))
		dependsOn("build")
	}
	loomx.modJar {
		archiveFileName.set(finalFileName)
	}
}

publishMods {
	file.set(loomx.modJar.map { it.archiveFile.get() })
	changelog.set(rootProject.file("CHANGELOG.md").readText())
	type.set(ReleaseType.of(versionType))
	modLoaders.add("fabric")

	modrinth {
		displayName.set("${project.version.toString()} for Fabric ${sc.current.version}")
		accessToken = System.getenv("MODRINTH_TOKEN")
		projectId.set(modrinthId)
		environment = CLIENT_ONLY
		projectDescription = modrinthReadme
		if (rangedVersion) {
			minecraftVersionRange {
				start = sc.current.version
				end = maxMc
			}
		}
		else {
			minecraftVersions.add(sc.current.version)
		}

		requires("fabric-api")
		requires("oneconfig")
		optional("modmenu")
	}
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
}