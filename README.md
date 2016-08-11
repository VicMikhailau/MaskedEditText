[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MaskedEditText-green.svg?style=true)](https://android-arsenal.com/details/1/3659) [![Codewake](https://www.codewake.com/badges/ask_question.svg)](https://www.codewake.com/p/maskededittext)
# MaskedEditText

It allows you to add a mask to EditText

![GIF of its use](https://github.com/VicMikhailau/MaskedEditText/blob/master/resources/masked_edit_text.gif)

# Version

1.1.1

# Installation

To use this library in your android project, just simply add the following dependency into your build.gradle

```sh
dependencies {
    compile 'com.vicmikhailau:MaskedEditText:1.1.1'
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
mEditText.addTextChangedListener(new MaskedWatcher("your_mask"));
```

**For create your mask you need to use following keys:**

    ANYTHING KEY = *;
    DIGIT KEY = #;
    UPPERCASE KEY = U;
    LOWERCASE KEY = L;
    ALPHA NUMERIC KEY = A;
    LITERAL KEY = \';
    CHARACTER KEY = ?;
    HEX KEY = H;

For example: you would like create a mask for a mobile number in format **(029)777-77-77**. Just use the simple mask **"(###)###-##-##"**.

**If you want to get text without mask just use following code:**
 - For getting unmasked text for **MaskedEditText mEdtMaskedCustom** just use
 
    ```
    mEdtMaskedCustom.getUnMaskedString().
    ```
 - For getting unmasked text for default EditText with **MaskedWatcher mMaskedWatcher** just use
 
    ```
    mMaskedWatcher.getUnMaskedString().
    ```

# Change Logs

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

