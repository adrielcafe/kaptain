package cafe.adriel.kaptain

import android.app.Activity
import android.content.Intent
import java.io.Serializable
import kotlin.reflect.KClass

class YachtKaptain internal constructor() : Kaptain {

    internal companion object {

        const val EXTRA_DESTINATION = "kaptain.destination"
    }

    private val destinations = mutableMapOf<KClass<out KaptainDestination>, KClass<out Activity>>()

    override fun sail(activity: Activity, destination: KaptainDestination, requestCode: Int?) {
        val destinationClass = destinations[destination::class]?.java
            ?: throw KaptainException("Destination not found -> ${destination::class.qualifiedName}")

        val destinationIntent = Intent(activity, destinationClass)
            .putExtra(EXTRA_DESTINATION, destination)

        requestCode
            ?.let { activity.startActivityForResult(destinationIntent, requestCode) }
            ?: activity.startActivity(destinationIntent)
    }

    fun logbook(activity: Activity): Serializable? =
        activity.intent.getSerializableExtra(EXTRA_DESTINATION)

    fun add(destination: KClass<out KaptainDestination>, activity: KClass<out Activity>) {
        destinations += destination to activity
    }

    fun remove(destination: KClass<out KaptainDestination>) {
        destinations -= destination
    }
}