[![JitPack](https://img.shields.io/jitpack/v/github/adrielcafe/kaptain.svg?style=for-the-badge)](https://jitpack.io/#adrielcafe/kaptain)
[![Github Actions](https://img.shields.io/github/workflow/status/adrielcafe/kaptain/main/master?style=for-the-badge)](https://github.com/adrielcafe/kaptain/actions)
[![Android API](https://img.shields.io/badge/api-14%2B-yellowgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=14)
[![Kotlin](https://img.shields.io/github/languages/top/adrielcafe/kaptain.svg?style=for-the-badge&color=orange)](https://kotlinlang.org/)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![License MIT](https://img.shields.io/github/license/adrielcafe/kaptain.svg?style=for-the-badge&color=yellow)](https://opensource.org/licenses/MIT)

# Kaptain
Kaptain is a small, dependencyless and easy to use Android library that helps you to navigate between activities spread over several modules.

## Usage

Given the following project structure:
```
/app
  MyApplication.kt
/feature-a
  FeatureAActivity.kt
/feature-b
  FeatureBActivity.kt
/feature-shared
  Destination.kt
```

* `app` module imports all modules below
* `feature-a` and `feature-b` imports only `feature-shared`
* `feature-shared` imports nothing

### 1. Define destinations
First, you must list all possible destinations (Activities) of your app. Create a `sealed class` that implements the `KaptainDestination` interface.

Optionally, you can add arguments to your destination using a `data class`.

```kotlin
sealed class Destination : KaptainDestination {

    object FeatureA : Destination()
    data class FeatureB(val message: String) : Destination()
}
```

### 2. Create a Kaptain instance
Next, create a new Kaptain instance and associate your destinations with the corresponding Activities. 

```kotlin
class MyApplication : Application() {
    
    val kaptain = Kaptain {
        add<Destination.FeatureA, FeatureAActivity>()
        add<Destination.FeatureB, FeatureBActivity>()
    }
}
```

Ideally, you should inject this instance as a **singleton** using a DI library. Check the [sample app](https://github.com/adrielcafe/kaptain/blob/master/sample/src/main/java/cafe/adriel/kaptain/sample/App.kt) for an example using [Koin](https://github.com/InsertKoinIO/koin/).

### 3. Navigate between activities
Now you can navigate to any Activity, from any module:

```kotlin
class FeatureAActivity : AppCompatActivity() {

    fun goToFeatureB() {
        kaptain.navigate(
            activity = this,
            destination = Destination.FeatureB(message = "Ahoy!"),
            requestCode = 0x123 // Optional
        )
    }
}
```

### 4. Retrieve a destination content
After arrive at your destination, you can retrieve it's content:

```kotlin
class FeatureBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_b)

        val importantMessage = kaptain.fromIntent<Destination.FeatureB>(this)?.message
    }
}
```

## Dynamic feature modules
Kaptain works great with [dynamic features](https://developer.android.com/guide/app-bundle/dynamic-delivery)!

### 1. Add destinations on demand
You can add/remove destinations at any time:

```kotlin
kaptain.add<Destination.DynamicFeatureA, DynamicFeatureAActivity>()
kaptain.remove<Destination.DynamicFeatureB>()
```

### 2. Make sure the destination exists before sailing
```kotlin
if (kaptain.has<Destination.DynamicFeatureA>) {
    kaptain.navigate(this, Destination.DynamicFeatureA)
}
```

## Import to your project
1. Add the JitPack repository in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Next, add the library to your module:
```gradle
dependencies {
    implementation "com.github.adrielcafe.kaptain:kaptain:$currentVersion"
}
```
Current version: [![JitPack](https://img.shields.io/jitpack/v/github/adrielcafe/kaptain.svg?style=flat-square)](https://jitpack.io/#adrielcafe/kaptain)