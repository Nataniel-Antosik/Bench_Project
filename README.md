# Bench Project

## General description:
Use any free API, it would be appreciated to use a few different API (optional, the second API can be added as work progress). Operation on a local database, save, read, and modify data. Scrollable list. Splash screen. Fetch and show images. Registration and log in (optional). Unit tests.

## Stack:
* `Clean architecture` and pattern `MVVM`
* `Dagger Hilt` - dependency injection
* `Retrofit` + `Moshi` or `Gson` - to control API
* `Room` - to control local database
* `RecyclerView` - to show list. `Optional` pagination
* `LiveData` + `Data Binding` + on the plus you can use `Binding Adapter` eg. to show picture or control `RecyclerView`
* `Kotlin Coroutines` - to every manipulation of data like integration with `Retrofit` and `Room`, asynchronous operations
* `Navigation Component` - to navigate, one `Activity` and a lot of `Fragments`, but you can create another `Activity`, to become familiar with navigation between `Activity`
* `jUnit` + `MockK` (mocking) + `Kluent` ("to make pretty and readable assertions") - to unit tests
* `Github Actions` - basic CI, verification if code in pull request is building correct and check unit tests

## To read:

* Clean Code
* Clean Architecture

## Main goal on nearly future:
* Change `binding` package from module `data` to `app`
* Add `mapper` to all class and unit tests for them
* Look at the all project and think about what I can test
* Annotation to tests
* Check all TODO and do them
* Make the project on `Github`
* Read about `lifecycle event observer interface` generic class in the view model