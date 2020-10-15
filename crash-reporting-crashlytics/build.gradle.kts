/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    plugin(Deps.Plugins.androidLibrary)
    plugin(Deps.Plugins.kotlinMultiplatform)
    plugin(Deps.Plugins.kotlinAndroidExtensions)
    plugin(Deps.Plugins.mobileMultiplatform)
    plugin(Deps.Plugins.mavenPublish)
}

group = "dev.icerock.moko"
version = Deps.mokoCrashReportingVersion

dependencies {
    commonMainImplementation(Deps.Libs.MultiPlatform.serialization)

    commonMainApi(project(":crash-reporting-core"))

    androidMainImplementation(Deps.Libs.Android.appCompat)

    androidMainImplementation(Deps.Libs.Android.firebaseCore)
    androidMainImplementation(Deps.Libs.Android.firebaseCrashlytics)
}

publishing {
    repositories.maven("https://api.bintray.com/maven/icerockdev/moko/moko-crash-reporting/;publish=1") {
        name = "bintray"

        credentials {
            username = System.getProperty("BINTRAY_USER")
            password = System.getProperty("BINTRAY_KEY")
        }
    }
}

cocoaPods {
    podsProject = file("../sample/ios-app/Pods/Pods.xcodeproj")

    pod("FirebaseCrashlytics", onlyLink = false)
}