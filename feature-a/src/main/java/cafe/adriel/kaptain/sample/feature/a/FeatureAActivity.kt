package cafe.adriel.kaptain.sample.feature.a

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cafe.adriel.kaptain.Kaptain
import cafe.adriel.kaptain.sample.feature.shared.Destination
import kotlinx.android.synthetic.main.activity_feature_a.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class FeatureAActivity : AppCompatActivity(), KoinComponent {

    private companion object {

        const val GREETING = "Ahoy!"
    }

    private val kaptain by inject<Kaptain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_a)

        vGoToFeatureB.setOnClickListener {
            kaptain.sail(
                activity = this,
                destination = Destination.FeatureB(message = GREETING)
            )
        }
    }
}