plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val baseUrl = "\"https://omgvamp-hearthstone-v1.p.rapidapi.com/\""
        val apiKey = "\"86a98ec84cmsha605728fc09616dp150cfdjsn1ea898b74d38\""
        val apiHost = "\"omgvamp-hearthstone-v1.p.rapidapi.com\""
        all {
            buildConfigField("String", "BASE_URL", baseUrl)
            buildConfigField("String", "API_KEY", apiKey)
            buildConfigField("String", "API_HOST", apiHost)
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp)
    implementation(NetworkDependencies.gson)
    implementation(NetworkDependencies.gson_converter)
    implementation(SupportDependencies.paging)

    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)

    implementation(DatabaseDependencies.room)
    implementation(DatabaseDependencies.room_ktx)
    kapt(DatabaseDependencies.room_compiler)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}