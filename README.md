# ct-test
## Architecture decisions
In this project, I took 2 major architecture decisions: The use [Clean architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) principles (by Uncle Bob) and [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)

The app was split into 3 modules: Domain, Data and App. To achieve independence between them each layer has its own models and converters.

The **domain layer** contains all the business rules, use cases and application models. This module is a pure Kotlin and was the layer I usually apply thread switching. ie. The use cases receive their requests in the main thread, switch to a background thread and return their results back to the main thread. This way I do not have to be extra careful to avoid running network request or making DB queries in the main thread. To perform background work I used Kotlin Coroutines.

The **data layer** is responsible for providing data to the domain layer. In this also a pure Kotlin layer and the dependency inversion pattern is widely used here to adding framework code into it. A code decision taken in this layer was to use a memory cache for the car list request, as it does not change, so we download data once and reuse it for all the time the app is running. This decision would not work on a real time web based system without adding a cache expiration strategy.

The **app layer** is where all the Android framework dependencies are implemented. This is also responsible for implementing the interfaces that somehow need Android to work, such as DB, SharedPreferences, Network, etc.
In this project the app layer has two responsibilities: Implement the MVP pattern and dependency injection.

## Libraries
It was used Retrofit and OkHttp to make the network requests;
Koin for dependency injection;
Picasso for image downloading;
It was also added some libraries to help the development and debug: Stetho and LeakCanary;
For tests purposes, it was used Mockito, JUnit and MockWebServer;

## Testing
To develop this app it was used mainly TDD. All the layers were unit tested. The UI tests were not implemented, the views were tested by their attached presenter. This decision was taken mainly due to time.

To run the test suite: `./gradlew test`

## Improvements / technical debts
Add instrumentation tests: We can develop these tests using Espresso.

Add another layer for interaction between presenter and use-cases. When the presenter starts to grow too much we can use this layer to keep it simpler.

Clean resource files creating styles for texts and common components.

### Credits - SVG icons
Icons made by **Those Icons** and **Freepik** from www.flaticon.com 
