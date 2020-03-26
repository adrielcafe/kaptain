package cafe.adriel.kaptain

import android.app.Activity

interface Kaptain {

    @Throws(KaptainException::class)
    fun sail(activity: Activity, destination: KaptainDestination, requestCode: Int? = null)

    companion object {

        operator fun invoke(init: Kaptain.() -> Unit = {}): Kaptain =
            YachtKaptain().apply(init)
    }
}

inline fun <reified D : KaptainDestination> Kaptain.logbook(activity: Activity): D? =
    (this as YachtKaptain).logbook(activity) as? D

inline fun <reified D : KaptainDestination, reified A : Activity> Kaptain.add() =
    (this as YachtKaptain).add(D::class, A::class)

inline fun <reified D : KaptainDestination> Kaptain.remove() =
    (this as YachtKaptain).remove(D::class)

inline fun <reified D : KaptainDestination> Kaptain.has(): Boolean =
    (this as YachtKaptain).has(D::class)