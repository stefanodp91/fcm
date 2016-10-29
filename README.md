## Screenshots ##

![screen1](https://imageshack.us/a/img922/7269/D61aYj.gif "")
![screen2](https://imageshack.us/a/img921/3439/UvoZwI.gif "")
![screen3](https://imageshack.us/a/img921/7407/I16Kv7.gif "")
![screen4](https://imageshack.us/a/img923/1019/0dOlem.gif "")

---


## Sample ##
[![get_it](http://evolvex.it/mobyx/images/nav/gplay-blk.png)](https://play.google.com/store/apps/details?id=org.bitbucket.stefanodp91.sample)

Sample source can be found [here](https://github.com/stefanodp91/fcm/tree/master/sample)

---


## Description ##

floating-contextual-menu is an Android library for creating floating contextual menus

---



## Usage ##

Include fcm in your build.gradle:

```Java
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:24.2.1'

    ...
    
    compile 'org.bitbucket.stefanodp91:fcm:0.1.0' // add this
}
```


Create the menu:

```Java
FloatingContextualMenu floatingContextualMenu = 
      new FloatingContextualMenu.Builder(getApplicationContext())
                        .add(new FloatingContextualItem.Builder("Reply", onReplyClickListener)
                                .icon(R.drawable.ic_reply_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Copy", onCopyClickListener)
                                .icon(R.drawable.ic_content_copy_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Forward", onForwardClickListener)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Select all", onSelectAllClickListener)
                                .visible(false)
                                .build())
                        .add(new FloatingContextualItem.Builder("Translate", onTranslateClickListener)
                                .visible(true)
                                .build())
                        .anchor(mAnchorView) // set the view to be anchored
      .build();
```

Show it:
```Java
...
floatingContextualMenu.show();
```

### FloatingContextualMenu.Builder customization ###

It can set the number of visible items when menu is collapsed
```Java
 new FloatingContextualMenu.Builder(getApplicationContext())
                        .add(new FloatingContextualItem.Builder("Reply", onReplyClickListener)
                                .icon(R.drawable.ic_reply_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Copy", onCopyClickListener)
                                .icon(R.drawable.ic_content_copy_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Forward", onForwardClickListener)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Select all", onSelectAllClickListener)
                                .visible(false)
                                .build())
                        .add(new FloatingContextualItem.Builder("Translate", onTranslateClickListener)
                                .visible(true)
                                .build())
                        .children(3) // number of visible items when the menu is collapsed
      .build();
```


Use:

1. **Type.TEXT** to show only text
2. **Type.ICON** to show only icons
3. **Type.BOTH** to show both text and icons


```Java
 new FloatingContextualMenu.Builder(getApplicationContext())
                        .add(new FloatingContextualItem.Builder("Reply", onReplyClickListener)
                                .icon(R.drawable.ic_reply_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Copy", onCopyClickListener)
                                .icon(R.drawable.ic_content_copy_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Forward", onForwardClickListener)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Select all", onSelectAllClickListener)
                                .visible(false)
                                .build())
                        .add(new FloatingContextualItem.Builder("Translate", onTranslateClickListener)
                                .visible(true)
                                .build())
                        .children(3)
                        .type(Type.TEXT) // show text only
      .build();
```

It can choose the less / more icon's color:
```Java
 new FloatingContextualMenu.Builder(getApplicationContext())
                        .add(new FloatingContextualItem.Builder("Reply", onReplyClickListener)
                                .icon(R.drawable.ic_reply_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Copy", onCopyClickListener)
                                .icon(R.drawable.ic_content_copy_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Forward", onForwardClickListener)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Select all", onSelectAllClickListener)
                                .visible(false)
                                .build())
                        .add(new FloatingContextualItem.Builder("Translate", onTranslateClickListener)
                                .visible(true)
                                .build())
                        .children(3)
                        .type(Type.TEXT)
                        .moreColor(R.color.grey_500) // change the more / less icon color
      .build();
```

and the menu's background color
```Java
 new FloatingContextualMenu.Builder(getApplicationContext())
                        .add(new FloatingContextualItem.Builder("Reply", onReplyClickListener)
                                .icon(R.drawable.ic_reply_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Copy", onCopyClickListener)
                                .icon(R.drawable.ic_content_copy_white_24dp)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Forward", onForwardClickListener)
                                .visible(true)
                                .build())
                        .add(new FloatingContextualItem.Builder("Select all", onSelectAllClickListener)
                                .visible(false)
                                .build())
                        .add(new FloatingContextualItem.Builder("Translate", onTranslateClickListener)
                                .visible(true)
                                .build())
                        .children(3)
                        .type(Type.TEXT)
                        .moreColor(R.color.grey_500) 
                        .backgroundColor(R.color.white) // background color
      .build();
```
 
### FloatingContextualItem.Builder customization ###

mItemTitle and mItemClickListener are ***mandatory***

```Java
FloatingContextualItem mFloatingContextualItem = 
      new FloatingContextualItem.Builder(mItemTitle, mItemClickListener)
      .build()
```

***mItemClickListener*** is a View.OnClickListener().

In the OnClickListener()  ***remember to dismiss*** the floatingContextualMenu

```Java
private View.OnClickListener mItemClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Toast.makeText(getApplicationContext(), "mItemClickListener pressed", Toast.LENGTH_SHORT).show();
          floatingContextualMenu.dismiss();
      }
  };
```

You can set your own icon drawable:
```Java
new FloatingContextualItem.Builder(mItemTitle, mItemClickListener)
      .icon(R.drawable.ic_reply_white_24dp) // set the icon drawable
      .build()
```

and the item visibility:
```Java
new FloatingContextualItem.Builder(mItemTitle, mItemClickListener)
      .icon(R.drawable.ic_reply_white_24dp)
      .visible(true) // set the item visibility
      .build()
```

you can choose a color for the title
```Java
new FloatingContextualItem.Builder(mItemTitle, mItemClickListener)
      .icon(R.drawable.ic_reply_white_24dp)
      .visible(true) 
      .textColor(R.color.black) // title color
      .build()
```

and for the icon
```Java
new FloatingContextualItem.Builder(mItemTitle, mItemClickListener)
      .icon(R.drawable.ic_reply_white_24dp)
      .visible(true) 
      .textColor(R.color.black) 
      .iconColor(R.color.black)  // icon color
      .build()
```
---

## Credits ##
Author: Stefano de Pascalis
[](https://it.linkedin.com/in/stefano-de-pascalis-1b51aa6a)

[![Google+](https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Antu_googleplus.svg/72px-Antu_googleplus.svg.png)](https://plus.google.com/u/1/+StefanoDePascalis)
[![LinkedIn](https://tks.com.au/Images/Home/LinkedIn.png)](https://it.linkedin.com/in/stefano-de-pascalis-1b51aa6a)

---

## License ##
    Copyright 2016 stefanodp91.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
