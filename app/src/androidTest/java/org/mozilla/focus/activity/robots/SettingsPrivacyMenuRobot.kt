/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

@file:Suppress("TooManyFunctions")
package org.mozilla.focus.activity.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.Assert.assertTrue
import org.mozilla.focus.R
import org.mozilla.focus.helpers.EspressoHelper.hasCousin
import org.mozilla.focus.helpers.TestHelper.appContext
import org.mozilla.focus.helpers.TestHelper.getStringResource
import org.mozilla.focus.helpers.TestHelper.mDevice
import org.mozilla.focus.helpers.TestHelper.packageName
import org.mozilla.focus.helpers.TestHelper.waitingTime
import org.mozilla.focus.helpers.TestHelper.waitingTimeShort

class SettingsPrivacyMenuRobot {

    fun verifyPrivacySettingsItems() {
        privacySettingsList.waitForExists(waitingTime)
        adTrackersBlockSwitch().check(matches(isDisplayed()))
        analyticTrackersBlockSwitch().check(matches(isDisplayed()))
        socialTrackersBlockSwitch().check(matches(isDisplayed()))
        otherContentTrackersBlockSwitch().check(matches(isDisplayed()))
        blockWebFontsSwitch().check(matches(isDisplayed()))
        blockJavaScriptSwitch().check(matches(isDisplayed()))
        cookiesAndSiteDataSection().check(matches(isDisplayed()))
        blockCookies().check(matches(isDisplayed()))
        blockCookiesDefaultOption().check(matches(isDisplayed()))
        sitePermissions().check(matches(isDisplayed()))
        useFingerprintSwitch().check(matches(isDisplayed()))
        stealthModeSwitch().check(matches(isDisplayed()))
        safeBrowsingSwitch().check(matches(isDisplayed()))
        sendDataSwitch().check(matches(isDisplayed()))
    }

    fun verifyCookiesAndSiteDataSection() {
        privacySettingsList.waitForExists(waitingTime)
        cookiesAndSiteDataSection().check(matches(isDisplayed()))
        blockCookies().check(matches(isDisplayed()))
        blockCookiesDefaultOption().check(matches(isDisplayed()))
        sitePermissions().check(matches(isDisplayed()))
    }

    fun verifyBlockCookiesPrompt() {
        assertTrue(blockCookiesPromptHeading.waitForExists(waitingTimeShort))
        assertTrue(blockCookiesYesPleaseOption.waitForExists(waitingTimeShort))
        assertTrue(block3rdPartyCookiesOnlyOption.waitForExists(waitingTimeShort))
        assertTrue(block3rdPartyTrackerCookiesOnlyOption.waitForExists(waitingTimeShort))
        assertTrue(blockCrossSiteCookiesOption.waitForExists(waitingTimeShort))
        assertTrue(noThanksOption.waitForExists(waitingTimeShort))
        assertTrue(cancelBlockCookiesPrompt.waitForExists(waitingTimeShort))
    }

    fun verifySitePermissionsSection() {
        privacySettingsList.waitForExists(waitingTime)
        autoplayOption().check(matches(isDisplayed()))
        autoplayDefaultOption().check(matches(isDisplayed()))
    }

    fun verifyAutoplaySection() {
        privacySettingsList.waitForExists(waitingTime)
        autoplayAllowAudioAndVideoOption().check(matches(isDisplayed()))
        autoplayBlockAudioOnlyOption().check(matches(isDisplayed()))
        recommendedDescription().check(matches(isDisplayed()))
        assertBlockAudioOnlyIsChecked()
        blockAudioAndVideoOption().check(matches(isDisplayed()))
    }

    fun verifyBlockAdTrackersEnabled(enabled: Boolean) {
        if (enabled) {
            adTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isChecked()
                            )
                        )
                    )
                )
        } else {
            adTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isNotChecked()
                            )
                        )
                    )
                )
        }
    }

    fun verifyBlockAnalyticTrackersEnabled(enabled: Boolean) {
        if (enabled) {
            analyticTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isChecked()
                            )
                        )
                    )
                )
        } else {
            analyticTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isNotChecked()
                            )
                        )
                    )
                )
        }
    }

    fun verifyBlockSocialTrackersEnabled(enabled: Boolean) {
        if (enabled) {
            socialTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isChecked()
                            )
                        )
                    )
                )
        } else {
            socialTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isNotChecked()
                            )
                        )
                    )
                )
        }
    }

    fun verifyBlockOtherTrackersEnabled(enabled: Boolean) {
        if (enabled) {
            otherContentTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isChecked()
                            )
                        )
                    )
                )
        } else {
            otherContentTrackersBlockSwitch()
                .check(
                    matches(
                        hasCousin(
                            allOf(
                                withId(R.id.switchWidget),
                                isNotChecked()
                            )
                        )
                    )
                )
        }
    }

    fun clickAdTrackersBlockSwitch() = adTrackersBlockSwitch().perform(click())

    fun clickAnalyticsTrackersBlockSwitch() = analyticTrackersBlockSwitch().perform(click())

    fun clickSocialTrackersBlockSwitch() = socialTrackersBlockSwitch().perform(click())

    fun clickOtherContentTrackersBlockSwitch() = otherContentTrackersBlockSwitch().perform(click())

    fun clickBlockCookies() = blockCookies().perform(click())

    fun clickCancelBlockCookiesPrompt() {
        cancelBlockCookiesPrompt.click()
        mDevice.waitForIdle(waitingTimeShort)
    }

    fun clickSitePermissionsButton() = sitePermissions().perform(click())

    fun clickAutoPlayOption() = autoplayOption().perform(click())

    fun clickYesPleaseOption() = blockCookiesYesPleaseOption.click()

    fun switchSafeBrowsingToggle(): ViewInteraction = safeBrowsingSwitch().perform(click())

    fun verifyExceptionsListDisabled() {
        exceptionsList()
            .check(matches(Matchers.not(isEnabled())))
    }

    fun openExceptionsList() {
        exceptionsList()
            .check(matches(isEnabled()))
            .perform(click())
    }

    fun verifyExceptionURL(url: String) {
        onView(withId(R.id.domainView)).check(matches(withText(containsString(url))))
    }

    fun removeException() {
        openActionBarOverflowOrOptionsMenu(appContext)
        onView(withText("Remove"))
            .perform(click())
        onView(withId(R.id.checkbox))
            .perform(click())
        onView(withId(R.id.remove))
            .perform(click())
    }

    fun removeAllExceptions() {
        onView(withId(R.id.removeAllExceptions))
            .perform(click())
    }

    class Transition {
        fun goBackToSettings(interact: SettingsRobot.() -> Unit): SettingsRobot.Transition {
            mDevice.pressBack()

            SettingsRobot().interact()
            return SettingsRobot.Transition()
        }
    }
}

private val privacySettingsList =
    UiScrollable(UiSelector().resourceId("$packageName:id/recycler_view"))

private fun adTrackersBlockSwitch(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Block ad trackers")
    return onView(withText("Block ad trackers"))
}

private fun analyticTrackersBlockSwitch(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Block analytic trackers")
    return onView(withText("Block analytic trackers"))
}

private fun socialTrackersBlockSwitch(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Block social trackers")
    return onView(withText("Block social trackers"))
}

private fun otherContentTrackersBlockSwitch(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Block other content trackers")
    return onView(withText("Block other content trackers"))
}

private fun blockWebFontsSwitch(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Block web fonts")
    return onView(withText("Block web fonts"))
}

private fun blockJavaScriptSwitch(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Block JavaScript")
    return onView(withText("Block JavaScript"))
}

private fun cookiesAndSiteDataSection(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Cookies and Site Data")
    return onView(withText(R.string.preference_category_cookies))
}

private fun blockCookies(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Cookies and Site Data")
    return onView(withText(R.string.preference_privacy_category_cookies))
}

private fun blockCookiesDefaultOption(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Cookies and Site Data")
    return onView(withText(R.string.preference_privacy_should_block_cookies_cross_site_option))
}

private fun sitePermissions(): ViewInteraction {
    privacySettingsList
        .scrollTextIntoView("Cookies and Site Data")
    return onView(withText(R.string.preference_site_permissions))
}

private fun useFingerprintSwitch(): ViewInteraction {
    val useFingerprintSwitchSummary = getStringResource(R.string.preference_security_biometric_summary)
    privacySettingsList.scrollTextIntoView(useFingerprintSwitchSummary)
    return onView(withText(useFingerprintSwitchSummary))
}

private fun stealthModeSwitch(): ViewInteraction {
    val stealthModeSwitchSummary = getStringResource(R.string.preference_privacy_stealth_summary)
    privacySettingsList.scrollTextIntoView(stealthModeSwitchSummary)
    return onView(withText(stealthModeSwitchSummary))
}

private fun safeBrowsingSwitch(): ViewInteraction {
    val safeBrowsingSwitchText = getStringResource(R.string.preference_safe_browsing_summary)
    privacySettingsList.scrollTextIntoView("Data Choices")
    return onView(withText(safeBrowsingSwitchText))
}

private fun sendDataSwitch(): ViewInteraction {
    val sendDataSwitchSummary = getStringResource(R.string.preference_mozilla_telemetry_summary2)
    privacySettingsList.scrollTextIntoView(sendDataSwitchSummary)
    return onView(withText(sendDataSwitchSummary))
}

private fun exceptionsList(): ViewInteraction {
    val exceptionsTitle = getStringResource(R.string.preference_exceptions)
    privacySettingsList.scrollTextIntoView(exceptionsTitle)
    return onView(withText(exceptionsTitle))
}

private fun autoplayOption() = onView(withText(R.string.preference_autoplay))

private fun autoplayDefaultOption() = onView(withText(R.string.preference_block_autoplay_audio_only))

private fun autoplayAllowAudioAndVideoOption() = onView(withText(R.string.preference_allow_audio_video_autoplay))

private fun autoplayBlockAudioOnlyOption() = onView(withText(R.string.preference_block_autoplay_audio_only))

private fun recommendedDescription() = onView(withText(R.string.preference_block_autoplay_audio_only_summary))

private fun blockAudioAndVideoOption() = onView(withText(R.string.preference_block_autoplay_audio_video))

private fun assertBlockAudioOnlyIsChecked() =
    onView(
        allOf(
            withId(R.id.radio_button),
            hasSibling(withText(R.string.preference_block_autoplay_audio_only))
        )
    ).check(matches(isChecked()))

private val blockCookiesPromptHeading =
    mDevice.findObject(
        UiSelector()
            .resourceId("$packageName:id/alertTitle")
            .textContains(getStringResource(R.string.preference_block_cookies_title))
    )

private val blockCookiesYesPleaseOption =
    mDevice.findObject(
        UiSelector()
            .textContains(getStringResource(R.string.preference_privacy_should_block_cookies_yes_option2))
    )

private val block3rdPartyCookiesOnlyOption =
    mDevice.findObject(
        UiSelector()
            .textContains(getStringResource(R.string.preference_privacy_should_block_cookies_yes_option2))
    )

private val block3rdPartyTrackerCookiesOnlyOption =
    mDevice.findObject(
        UiSelector()
            .textContains(getStringResource(R.string.preference_privacy_should_block_cookies_third_party_tracker_cookies_option))
    )

private val blockCrossSiteCookiesOption =
    mDevice.findObject(
        UiSelector()
            .textContains(getStringResource(R.string.preference_privacy_should_block_cookies_cross_site_option))
    )

private val noThanksOption =
    mDevice.findObject(
        UiSelector()
            .textContains(getStringResource(R.string.preference_privacy_should_block_cookies_no_option2))
    )

private val cancelBlockCookiesPrompt =
    mDevice.findObject(
        UiSelector()
            .textContains(getStringResource(R.string.action_cancel))
    )
