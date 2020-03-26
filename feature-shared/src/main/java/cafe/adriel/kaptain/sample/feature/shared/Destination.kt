package cafe.adriel.kaptain.sample.feature.shared

import cafe.adriel.kaptain.KaptainDestination

sealed class Destination : KaptainDestination {

    object FeatureA : Destination()
    data class FeatureB(val message: String) : Destination()
}