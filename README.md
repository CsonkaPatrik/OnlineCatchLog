# An Android Studio project replicating the hungarian paper based fishing catch log

## How to run

Prerequisites:
* Android Studio Iguana 2023.2.1
* Firebase account

1. Download the project, and open it in Android Studio
2. In Firebase create a new project, the add this Android app to the freshly created Firebase project.
3. Enable E-mail and Password authentication.
4. Create a default user.
5. Open a terminal in the project folder, the type:
```
npm install -g firebase-tools
```
6. Then login with:
```
firebase login
```
7. Then initialize firebase in the project:
```
firebase init
```
8. After that start the local emulator:
```
firebase starts:emulator
```
9. Lastly run the project, and login with the previously created user credentials.
