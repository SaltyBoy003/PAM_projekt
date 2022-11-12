<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:paddingLeft="16dp"
android:paddingRight="16dp"
android:orientation="vertical" >
<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="@string/to" />
<EditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="@string/subject" />
<EditText
android:layout_width="match_parent"
android:layout_height="0dp"
android:layout_weight="1"
android:gravity="top"
android:hint="@string/message" />
<Button
android:layout_width="100dp"
android:layout_height="wrap_content"
android:layout_gravity="right"
android:text="@string/send" />
</LinearLayout>