@file:Suppress("Unused", "MayBeConstant", "MemberVisibilityCanBePrivate")

internal object Version {
    const val GRADLE_ANDROID = "7.0.0"
    const val GRADLE_KTLINT = "10.1.0"
    const val GRADLE_VERSIONS = "0.39.0"

    const val KOTLIN = "1.5.21"
    const val KOIN = "3.1.2"
    const val APP_COMPAT = "1.3.1"

    const val TEST_JUNIT = "4.13"
    const val TEST_ROBOLECTRIC = "4.3.1"
    const val TEST_STRIKT = "0.31.0"
}

object ProjectLib {
    const val ANDROID = "com.android.tools.build:gradle:${Version.GRADLE_ANDROID}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${Version.GRADLE_KTLINT}"
    const val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Version.GRADLE_VERSIONS}"
}

object ModuleLib {
    const val KOIN = "io.insert-koin:koin-android:${Version.KOIN}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
}

object TestLib {
    const val JUNIT = "junit:junit:${Version.TEST_JUNIT}"
    const val ROBOLECTRIC = "org.robolectric:robolectric:${Version.TEST_ROBOLECTRIC}"
    const val STRIKT = "io.strikt:strikt-core:${Version.TEST_STRIKT}"
}