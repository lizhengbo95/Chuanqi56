# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
#【混淆时不会产生形形色色的类名 】
-dontusemixedcaseclassnames
#【指定不去忽略非公共的库类。 】
-dontskipnonpubliclibraryclasses
#【不预校验】
-dontpreverify
-keepattributes Signature
-verbose
-dontshrink

#【错误统计问题Unknown source】
-keepattributes SourceFile,LineNumberTable


#【优化】
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#【不进行混淆类名的类，保持其原类名和包名】
-keep public class * extends android.app.Activity
#-keep public class * extends android.widget.BaseAdapter
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

#【引用的库的jar，用于解析injars所指定的jar类】
#-libraryjars libs/android-support-v4.jar
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep class android.support.v4.view.**{ *;}
-keep class android.support.v4.content.**{ *;}

#【引用的库的jar，用于解析injars所指定的jar类】
#-libraryjars libs/android-support-v7.jar
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep public class * extends android.app.Fragment
-keep class android.support.v7.view.**{ *;}
-keep class android.support.v7.content.**{ *;}

#retrofit2
-dontwarn okio.**
-dontwarn javax.annotation.**

#okhttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

#腾讯Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}







