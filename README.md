![image](https://user-images.githubusercontent.com/91452454/143868953-549a9960-66ae-44a3-9461-e9b9dedc83d7.png)


# final Capstone 


Tuwaiq Academy final Project.

NEWS Android Application

## Overview:



This project represents an android application **NEWS**


which helps the user to read news whenever he want.

## Technologies used:
This application was built using the following technologies:
### For Designing the pages of the app"
* Figma 
### For Programming the app:
* Android Architecture Components:Room,LiveData,ViewModel and Data binding.
* RecyclerViews & Adapters.
* Required Libraries
* Figma
* News Api


## Wireframes and User stories:

<img width="9416" alt="news" src="https://user-images.githubusercontent.com/91452454/150757225-6aad843a-1deb-47d4-b7b8-cf104543d616.png">

Wireframes


- As a user,I want to read news on app rather than tv or newspaper so that I can save my time.

- As a user, I would like to be able to save an important news so that I can read it later.

- As a user I want to delete any news so that I can make sure that I deleted the one I read.

- As a user I want to edite so that I can put comment in each news.
-------------------------------------------------------------------------
## List of the depencenceies used in the project:
 ```
   *  for motion layout:
 ```kotlin
    dependencies {
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    implementation 'com.google.firebase:firebase-auth-ktx:21.0.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    }
```
   * Navigation Component:
```kotlin
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    }
```
   * Recyclerview:
```kotlin
    dependencies {
   implementation 'androidx.recyclerview:recyclerview:1.2.1'
    }
```
```
   * Retrofit
```kotlin
    dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   }
```
   * viewModel
```kotlin
    dependencies {
     implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
   }
```
   * livedata
```kotlin
    dependencies {
     implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"
   }
```
   * Coroutines
```kotlin
    dependencies {
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
   }
```
   * picasso
```kotlin
    dependencies {
   implementation 'com.squareup.picasso:picasso:2.71828'
   }
```

## Development Process and Problem-solving Strategy:

planning :

On the beginning searched for different news apps to have full understanding about what I will work on and to have an idea about the design after that I designed my app using Figma then transformed the design on Android Studio. wrote down what my app need and what features I want to put on it, eventually start coding and working on my app.

development process :

* Searched:

Searched for different apps and what is the basic features in every app 

* wrote :

wrote down the features I wont for my apps and searched in different website for the code to understand what I will do when I start my coding. 

* designed :

designed my app in Figma then In Android Studio.

* wrote the code :

created packages for database,adapter,viewmodel,fragments and splash .

* run the app :

tried to run the app and solved the errors.

Problem solving strategy: decide the nature of the error (if it's syntax, runtime or logical) and Searched in different website like developer android,youtube,stackoverflow and medium asked programmers who have knowledge or Mr. Mohamed and Mr.Saad

## Unsolved Problems which would be fixed in future :


*  send notification to notifiy the user that there are some new news.
*  filter


## My favorite functions work:

* Share news 
