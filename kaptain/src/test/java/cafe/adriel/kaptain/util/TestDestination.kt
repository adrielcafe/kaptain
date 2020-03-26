package cafe.adriel.kaptain.util

import cafe.adriel.kaptain.KaptainDestination

sealed class TestDestination : KaptainDestination {

    data class SomePlace(val message: String) : TestDestination()
    object UnknownPlace : TestDestination()
}
