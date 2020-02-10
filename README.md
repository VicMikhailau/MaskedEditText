[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaskedEditText-green.svg?style=true)](https://android-arsenal.com/details/1/3659)
# MaskedEditText

It allows you to add a mask to EditText

![GIF of its use](https://github.com/VicMikhailau/MaskedEditText/blob/master/resources/masked_edit_text.gif)

# Version

3.0.3

# Installation

To use this library in your android project, just simply add the following dependency into your build.gradle

```sh
dependencies {
    implementation 'com.vicmikhailau:MaskedEditText:3.0.3'
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

```java
MaskedFormatter formatter = new MaskedFormatter("your_mask");
mEditText.addTextChangedListener(new MaskedWatcher(formatter, mEditText));
```
Object of MaskedWatcher class has got a weakreference to formatter object, so you must to conside this.
 
**For create your mask you need to use following keys:**
```
ANYTHING KEY = *;
DIGIT KEY = #;
UPPERCASE KEY = U;
LOWERCASE KEY = L;
ALPHA NUMERIC KEY = A;
CHARACTER KEY = ?;
HEX KEY = H;
```

For example: you would like create a mask for a mobile number in format **(029)777-77-77**. Just use the simple mask **"(###)###-##-##"**.

**If you want to get text without mask just use following code:**
 - For getting unmasked text for **MaskedEditText mEdtMaskedCustom** just use
 
    ```java
    String unamskedString = mEdtMaskedCustom.getUnMaskedString();
    ```
 - For getting unmasked text for default EditText just use
 
    ```java
    String text = mEditText.getText().toString();
    String unmaskedString = formatter.formatString(text).getUnMaskedString();
    ```

# Fixes and updates

Unfortunately, there is no way to devote much time to the project. Please feel free to Fork the project and add Pull requests. Thanks a lot!

# Change Logs

### v3.0.3

Removed private attribute of the setMask function (Pull Request #25).

### v3.0.2

Changed asserts to safe call (Pull Request #23).

### v3.0.1

Moved library to androidx and kotlin.

### v2.0.4

Updated support version.

### v2.0.3

Bug with getting сredit сard mask was fixed (issue #13).

### v2.0.2
 
Bug with getting unmasked string was fixed (issue #11).

### v2.0.1
 
Bug with mask was fixed.
 
### v2.0.0

Main logic was updated. Bugs with deleting and changing characters inside masked EditText was fixed

### v1.1.2

Fixed crash for getting unmasked text

### v1.1.1

Superclass for MaskedEditText was changed to AppCompatEditText, which supports compatible features on older version of the platform

### v1.1.0

Ability to get unmasked text (text without mask) was added

### v1.0.0

Initial version

## Licence
Copyright 2016 Vic Mikhailau<br />
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

