-dontobfuscate

-keep class org.codehaus.groovy.vmplugin.**
-keep class org.codehaus.groovy.runtime.dgm*

-keepclassmembers class org.codehaus.groovy.runtime.dgm* {*;}
-keepclassmembers class ** implements org.codehaus.groovy.runtime.GeneratedClosure {*;}
-keepclassmembers class org.codehaus.groovy.reflection.GroovyClassValue* {*;}
-keepclassmembers class groovyx.example.** {*;}
-keepclassmembers class com.arasthel.swissknife.utils.Finder {*;}

-dontwarn org.codehaus.groovy.**
-dontwarn groovy**