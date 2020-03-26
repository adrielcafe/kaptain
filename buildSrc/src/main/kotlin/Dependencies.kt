@file:Suppress("Unused", "MayBeConstant", "MemberVisibilityCanBePrivate")

internal object Version {
    const val GRADLE_ANDROID = "3.6.1"
    const val GRADLE_KTLINT = "9.2.1"
    const val GRADLE_VERSIONS = "0.28.0"

    const val KOTLIN = "1.3.71"
    const val KOIN = "2.1.4"
    const val APP_COMPAT = "1.1.0"
    
    const val TEST_JUNIT = "4.13"
    const val TEST_ROBOLECTRIC = "4.3.1"
    const val TEST_STRIKT = "0.24.0"
}

object ProjectLib {
    const val ANDROID = "com.android.tools.build:gradle:${Version.GRADLE_ANDROID}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${Version.GRADLE_KTLINT}"
    const val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Version.GRADLE_VERSIONS}"
}

object ModuleLib {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.KOTLIN}"
    const val KOIN = "org.koin:koin-android:${Version.KOIN}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
}

object TestLib {
    const val JUNIT = "junit:junit:${Version.TEST_JUNIT}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Version.TEST_ROBOLECTRIC}"
    const val STRIKT = "io.strikt:strikt-core:${Version.TEST_STRIKT}"
}