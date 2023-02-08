# proguard_rules.pro
-dontoptimize
-dontobfuscate

-dontwarn kotlinx.**

-keepclasseswithmembers public class com.calculator.MainKt {
    public static void main(java.lang.String[]);
}
-keep class org.jetbrains.skia.** { *; }
-keep class org.jetbrains.skiko.** { *; }