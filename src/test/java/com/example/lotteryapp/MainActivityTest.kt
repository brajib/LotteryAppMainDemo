package com.example.lotteryapp

import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import org.junit.*
import org.robolectric.Robolectric
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        // Launch MainActivity safely
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testEmptyUsernameShowsError() {
        scenario.onActivity { activity ->
            val editText = activity.findViewById<EditText>(R.id.editText2)
            val button = activity.findViewById<Button>(R.id.generateBtn)

            // Leave username empty
            editText.setText("")
            button.performClick()

            // Check error is displayed
            Assert.assertEquals("Please enter your name", editText.error)
        }
    }

    @Test
    fun testValidUsernameStartsSecondActivity() {
        scenario.onActivity { activity ->
            val editText = activity.findViewById<EditText>(R.id.editText2)
            val button = activity.findViewById<Button>(R.id.generateBtn)

            // Enter a valid username
            editText.setText("Rajib")
            button.performClick()

            // Get the next started Intent
            val shadowActivity = Shadows.shadowOf(activity)
            val nextIntent = shadowActivity.nextStartedActivity

            Assert.assertNotNull(nextIntent)
            Assert.assertEquals(SecondActivity::class.java.name, nextIntent.component?.className)
            Assert.assertEquals("Rajib", nextIntent.getStringExtra("Username"))
        }
    }

    @Test
    fun testIsRunningUnitTestReturnsTrue() {
        scenario.onActivity { activity ->
            // isRunningUnitTest() should be internal, not private
            Assert.assertTrue(activity.isRunningUnitTest())
        }
    }
}
