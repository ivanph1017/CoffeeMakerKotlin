# CoffeeMakerKotlin

This is part of project to manage a coffee maker remotely. More details can be found here:

- [CoffeeMaker](https://github.com/ivanph1017/CoffeeMaker)

The Android app was developed in Kotlin following clean arquitecture, repository, factory, observer-subcriber and MVVM patterns. More details can be found here: 

- [MVVM arquitecture guide](https://developer.android.com/topic/libraries/architecture/guide.html) by Google
- [MVVM project reference](https://github.com/kevicsalazar/UpLabs-Kotlin/tree/Architecture-Components) by Kevin Salazar
- [MVVM article](https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-1-604f50cda1) by Hazem Saleh
- [Arquitecting Android](https://fernandocejas.com/2015/07/18/architecting-android-the-evolution/) by Fernando Cejas
- [Repository pattern - Be aware of over abstraction](http://hannesdorfmann.com/android/evolution-of-the-repository-pattern) Hannes Dorfmann
- [Why clean arquitecture?](http://xurxodev.com/por-que-utilizo-clean-architecture-en-mis-proyectos/) by Jorge Sánchez
- [Clean arquitecture code smells](http://xurxodev.com/clean-architecture-code-smells-parte-1/) by Jorge Sánchez

## Before run it

Replace the app/google-services.json template for your own google-services.json.

Manually add Firebase
To add Firebase to your app you'll need a Firebase project and a Firebase configuration file for your app.

- Create a Firebase project in the Firebase console, if you don't already have one. If you already have an existing Google project associated with your mobile app, click Import Google Project. Otherwise, click Add project.
- Click Add Firebase to your Android app and follow the setup steps. If you're importing an existing Google project, this may happen automatically and you can just download the config file.
- When prompted, enter your app's package name. It's important to enter the package name your app is using; this can only be set when you add an app to your Firebase project.
- At the end, you'll download a google-services.json file. You can download this file again at any time.
- If you haven't done so already, copy this into your project's module folder, typically app/.

For more details, check out the Firebase documention:
- [Firebase doc](https://firebase.google.com/docs/android/setup?hl=en-419)
