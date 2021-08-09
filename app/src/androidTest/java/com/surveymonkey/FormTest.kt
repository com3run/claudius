package com.surveymonkey


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.surveymonkey.data.persistence.demo.Form1
import com.surveymonkey.data.persistence.demo.Form2
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4::class)
class BottomNavViewTest : BaseRobot() {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun start() {
        register()

        fillForm()

        sleep(55000)
    }

    private fun register() {
        SessionManager.loggedIn = false

        val name = "Kamran Mammadov"
        val username = "com3run"

        doOnView(withId(R.id.registerBtn), click())
        doOnView(withId(R.id.nameEdt), typeText(name))
        doOnView(withId(R.id.usernameEdt), typeText(username))
        doOnView(withId(R.id.registerBtn), click())

        if (onView(withId(R.id.positiveBtn)).isDisplayed()) {
            doOnView(withId(R.id.positiveBtn), click())

            sleep(300)
            pressBack()
            sleep(500)

            login()
        }
    }

    private fun login() {
        val username = "com3run"

        doOnView(withId(R.id.usernameEdt), typeText(username))
        doOnView(withId(R.id.loginBtn), click())
    }


    private fun fillForm() {

        sleep(500)

        val form = when {
            onView(withText(Form1().form().name)).isDisplayed() -> Form1()
            onView(withText(Form2().form().name)).isDisplayed() -> Form2()
            else -> null
        }

        if (form is Form1) {
            doOnView(withId(R.id.np), swipeLeft())
            scrollTo(2)
            checkVariant(form.question2Variant2(0).name)
            scrollTo(3)
            checkVariant(form.question3Variant1(0).name)
            checkVariant(form.question3Variant3(0).name)
            checkVariant(form.question3Variant4(0).name)
            scrollTo(4)
            checkVariant(form.question4Variant3(0).name)
            scrollTo(5)
            checkVariant(form.question5Variant1(0).name)
            scrollTo(6)
            checkVariant(form.question6Variant4(0).name)
            scrollTo(7)
            checkVariant(form.question7Variant1(0).name)
            scrollTo(8)
            checkVariant(form.question8Variant5(0).name)
            scrollTo(9)
            checkVariant(form.question9Variant1(0).name)
            scrollTo(10)
            doOnView(withId(R.id.noteEdt), typeText("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. "))
        } else if (form is Form2) {
            doOnView(withId(R.id.np), swipeLeft())
            scrollTo(2)
            checkVariant(form.question2Variant2(0).name)
            scrollTo(3)
            checkVariant(form.question3Variant1(0).name)
            checkVariant(form.question3Variant3(0).name)
            checkVariant(form.question3Variant4(0).name)
            scrollTo(4)
            checkVariant(form.question4Variant3(0).name)
            scrollTo(5)
            checkVariant(form.question5Variant1(0).name)
            scrollTo(6)
            checkVariant(form.question6Variant4(0).name)
            scrollTo(7)
            checkVariant(form.question7Variant1(0).name)
            scrollTo(8)
            checkVariant(form.question8Variant5(0).name)
            scrollTo(9)
            checkVariant(form.question9Variant1(0).name)
            scrollTo(10)
            doOnView(withId(R.id.noteEdt), typeText("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. "))
        }

        form?.let {
            doOnView(withId(R.id.recyclerView), swipeUp())
            doOnView(allOf(withId(R.id.saveBtn), isDisplayed()), click())
        }

        sleep(600)
        doOnView(withId(R.id.savedItem), click())


        doOnView(
            withId(R.id.recyclerView),
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }

    private fun checkVariant(text: String?) {
        doOnView(allOf(withText(text), isDisplayed()), click())
        sleep(400)
    }

    private fun scrollTo(position:Int){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, scrollTo()))
    }
}