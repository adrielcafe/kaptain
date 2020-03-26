package cafe.adriel.kaptain

import android.content.Intent
import android.os.Build
import cafe.adriel.kaptain.util.TestActivity
import cafe.adriel.kaptain.util.TestDestination
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class KaptainTest {

    private val kaptain = Kaptain {
        add<TestDestination.SomePlace, TestActivity>()
    }

    private val startActivity = Robolectric.buildActivity(TestActivity::class.java).get()

    @Test
    fun `sail should start a new activity`() {
        kaptain.sail(startActivity, TestDestination.SomePlace("Ahoy!"))

        val intent = shadowOf(startActivity).nextStartedActivity
        val className = shadowOf(intent).intentClass.canonicalName

        expectThat(className) isEqualTo TestActivity::class.qualifiedName
    }

    @Test
    fun `sail should start a new activity for result`() {
        kaptain.sail(startActivity, TestDestination.SomePlace("Ahoy!"), 0x123)

        val intentForResult = shadowOf(startActivity).nextStartedActivityForResult
        val className = shadowOf(intentForResult.intent).intentClass.canonicalName
        val requestCode = intentForResult.requestCode

        expect {
            that(className) isEqualTo TestActivity::class.qualifiedName
            that(requestCode) isEqualTo 0x123
        }
    }

    @Test
    fun `sail should throw an exception if destination wasn't found`() {
        expectThrows<KaptainException> {
            kaptain.sail(startActivity, TestDestination.UnknownPlace)
        }
    }

    @Test
    fun `logbook should return the correct destination`() {
        val destinationIntent = Intent(startActivity, TestActivity::class.java)
            .putExtra(YachtKaptain.EXTRA_DESTINATION, TestDestination.SomePlace("Ahoy!"))
        val destinationActivity = Robolectric
            .buildActivity(TestActivity::class.java, destinationIntent)
            .get()

        val destination = kaptain.logbook<TestDestination.SomePlace>(destinationActivity)

        expectThat(destination) {
            isNotNull()
            get { this?.message } isEqualTo "Ahoy!"
        }
    }

    @Test
    fun `logbook should return null if destination wasn't found`() {
        val destinationActivity = Robolectric.buildActivity(TestActivity::class.java).get()

        val destination = kaptain.logbook<TestDestination.UnknownPlace>(destinationActivity)

        expectThat(destination).isNull()
    }
}