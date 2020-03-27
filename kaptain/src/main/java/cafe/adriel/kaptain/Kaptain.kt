package cafe.adriel.kaptain

import android.app.Activity
import android.content.Intent
import kotlin.reflect.KClass

class Kaptain(init: Kaptain.() -> Unit) {

    @PublishedApi
    internal companion object {

        const val EXTRA_DESTINATION = "kaptain.destination"
    }

    @PublishedApi
    internal val destinations = mutableMapOf<KClass<out KaptainDestination>, KClass<out Activity>>()

    init {
        this.init()
    }

    @Throws(KaptainException::class)
    fun sail(activity: Activity, destination: KaptainDestination, requestCode: Int? = null) {
        val destinationClass = destinations[destination::class]?.java
            ?: throw KaptainException("Destination not found -> ${destination::class.qualifiedName}")

        val destinationIntent = Intent(activity, destinationClass)
            .putExtra(EXTRA_DESTINATION, destination)

        requestCode
            ?.let { activity.startActivityForResult(destinationIntent, requestCode) }
            ?: activity.startActivity(destinationIntent)
    }

    inline fun <reified D : KaptainDestination> logbook(activity: Activity): D? =
        activity.intent.getSerializableExtra(EXTRA_DESTINATION) as? D

    inline fun <reified D : KaptainDestination, reified A : Activity> add() {
        destinations += D::class to A::class
    }

    inline fun <reified D : KaptainDestination> remove() {
        destinations -= D::class
    }

    inline fun <reified D : KaptainDestination> has(): Boolean =
        destinations.containsKey(D::class)
}