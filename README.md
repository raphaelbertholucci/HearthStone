# HearthStone
An app that shows all information about cards from the game HearthStone!

<p align="center">
<img src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/home.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/home.webp" width="250" height="480" />
<img src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/home_loading.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/home_loading.webp" width="250" height="480" />
<img src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/home_error.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/home_error.webp" width="250" height="480" />
</p>

## Getting Started
Before you try to run this project you should go to [HearthStone API](https://rapidapi.com/omgvamp/api/hearthstone) to create your account and generate you own api key.
  * Place your API_KEY in build.gradle.kts inside the data module.

## Multi Module Project Structure
The multi module project structure its being used to de-couple the main app of its features.
Also make it easier to separate feature parts of the application from the design-system and to reuse any necessary request without duplicate the code or relying on uncessary content inside on class.

## Used Technologies

The project is organized using MVVM and Clean Architecture.

### Why MVVM?
This architecture is recommended by Google, belongs to the Jetpack Components and provides a clean separation of concerns between user interface and domain logic. It's divided in three components:
- **Model**: they are the contracts that contains business logic and they can be manipulated
- **View**: they are indeed the views, like Activities or Fragments, they will show to the user some data received from the ViewModel. They need to have less business logic as possible.
- **ViewModel**: responsible for store and manage the data related to the view respecting their lifecycle.

### Why use Clean Architecture? :broom:
The Clean Architecture is a way to manage our code, that contains some advantages, like: **Scalability, Modularization, Testability, Independency of Frameworks, UI and Databases**.

We can divide in three main layers:

- **Presentation**: contains all UI classes, like Fragments, Activities, Presentation Models and ViewModels (that will interact with the domain layer through UseCases) and the views will communicate with ViewModel through LiveData.
- **Domain**: responsible for orchestrate our business rules, that will be requested by the Data Layer and the Presentation Layer and doesn't have dependecy with other layers, also contains: UseCases (interactors), Repository Interfaces and Domain models.
- **Data**: contains API Interface, Repository Implementation, Database classes and SharedPreferences(or DataStore). The repository implementation will decide which way are we gonna obtain the data, like, from API Call, Database or SharedPreferences.

### <b>Koin</b> for dependency injection :syringe:
A lightweight and pragmatic dependency injection framework written purely in Kotlin. Easy to setup and easy to use.

### <b>Coroutines</b> with <b>Flow</b> for async tasks :airplane:
Coroutines are light, they prevent some memory leaks and they have integration with Jetpack.
Flow also had some advantages, like multiplatform support, nullability support, suspending execution and they also prevent some memory leaks, on account of being collected in the ViewModel scope.

### <b>Junit</b> with <b>MockK</b> for unit tests :writing_hand:
To guarantee code quality, tests were implemented using JUnit with MockK for unit tests. There is an example in GetCardsByNameTest.kt and CardsRepositoryImplTest.kt.

<p align="center">
<img src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/details.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/details.webp" width="250" height="480" />
<img src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/details_loading.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/details_loading.webp" width="250" height="480" />
<img src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/details_error.webp" alt="" data-canonical-src="https://github.com/raphaelbertholucci/HearthStone/blob/main/readme-pictures/details_error.webp" width="250" height="480" />
</p>

### Other libraries used:
  * :lotus_position: <b>Retrofit</b> for REST API calls
  * :computer: <b>CI Integration </b> with <b>GitHub Actions</b>
  * :camera_flash: <b>Coil</b> for load image URLs
  * :thought_balloon: <b>Shimmer</b> from Facebook to handle loading UI
  * :tada: The <b>Splash Screen</b> library to handle the splash on Android 12
  * :arrow_down: <b>Dependency Updates</b> to keep all dependencies up to date!
  * :monocle_face:	<b>Detekt</b> to keep our code clean and well structured
  * :saxophone: <b>Espresso</b> for instrumentation tests - <b>CardFragmentTest</b> class
  * :boat: <b>Navigation</b> of Jetpack Components to navigate between Fragments

## License
This project is licensed under the MIT License - see the LICENSE file for details
