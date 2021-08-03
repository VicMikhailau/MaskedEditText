[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.vicmikhailau/MaskedEditText/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.vicmikhailau/MaskedEditText)
[![GitHub license](https://img.shields.io/github/license/VicMikhailau/MaskedEditText)](https://github.com/VicMikhailau/MaskedEditText/blob/master/LICENSE)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaskedEditText-green.svg?style=true)](https://android-arsenal.com/details/1/3659)
# MaskedEditText

It allows you to add a mask to EditText

![GIF of its use](https://github.com/VicMikhailau/MaskedEditText/blob/master/resources/masked_edit_text.gif)

# Version

4.0.7

# Installation

**Step 1**: Add MavenCentral and Jitpack to your repositories in your *project* level `build.gradle` file:

```gradle
allprojects {
    repositories {
        mavenCentral()
    }
}
```

**Step 2**: Add the library as a dependency in your *module* level `build.gradle` file:

*Groovy:*

```sh
dependencies {
    implementation 'io.github.vicmikhailau:MaskedEditText:4.0.7'
}
```

*Kotlin:*

```sh
dependencies {
    implementation("io.github.vicmikhailau:MaskedEditText:4.0.7")
}
```

# Usage

Just add in xml custom MaskedEditText with attribute app:mask="your_mask" like below:

```xml
<com.vicmikhailau.maskededittext.MaskedEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:mask="your_mask" />
```
Or add TextChangedListener for your EditText like in following code:

```
val formatter = MaskedFormatter("your_mask")
mEditText.addTextChangedListener(MaskedWatcher(formatter, mEditText))
```
Object of MaskedWatcher class has got a weakreference to formatter object, so you must to conside this.
 
**For create your mask you need to use following keys:**
```
ANYTHING KEY = *
DIGIT KEY = #
UPPERCASE KEY = U
LOWERCASE KEY = L
ALPHA NUMERIC KEY = A
CHARACTER KEY = ?
HEX KEY = H
```

For example: you would like create a mask for a mobile number in format **(029)777-77-77**. Just use the simple mask **"(###)###-##-##"**.

**If you want to get text without mask just use following code:**
 - For getting unmasked text for **mEdtMaskedCustom: MaskedEditText** just use
 
    ```
    val unamskedString = mEdtMaskedCustom.unMaskedText
    ```
 - For getting unmasked text for default EditText just use
 
    ```
    val text = mEditText.text.toString()
    val unmaskedString = formatter.formatString(text).unMaskedString
    ```

# Kotlin dependency in pure Java project

Add in your build.gradle: ```implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"```
https://github.com/VicMikhailau/MaskedEditText/issues/27

# Fixes and updates

Unfortunately, there is no way to devote much time to the project. Please feel free to Fork the project and add Pull requests. Thanks a lot!

# Change Logs

### v4.0.7

Fixed InflateException appeared in 4.0.6.

### v4.0.6

Updated Gradle and libraries. Changed targetSdkVersion to 31.

### v4.0.5

Moved project to the MavenCentral. Changed groupId to io.github.vicmikhailau

### v3.1.1

Updated Gradle and libraries.

### v3.1.0

Updated Gradle and libraries.

### v3.0.4

Fixes (Pull Request #26).

### v3.0.3

Removed private attribute of the setMask function (Pull Request #25).

### v3.0.2

Changed asserts to safe call (Pull Request #23).

### v3.0.1

Moved library to androidx and kotlin.


## Licence
Copyright 2021 Victor Mikhailau<br />
<br />
Licensed under the Apache License, Version 2.0 (the "License");<br />
you may not use this file except in compliance with the License.<br />
You may obtain a copy of the License at<br />
<br />
   http://www.apache.org/licenses/LICENSE-2.0<br />
<br />
Unless required by applicable law or agreed to in writing, software<br />
distributed under the License is distributed on an "AS IS" BASIS,<br />
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br />
See the License for the specific language governing permissions and<br />
limitations under the License.

