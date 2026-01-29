##---------------Begin: proguard configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }


##---------------Begin: proguard configuration for Gson ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
@com.google.gson.annotations.SerializedName <fields>;
}


##---------------Begin: proguard configuration for Retrofit ----------
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
# KotlinMetadata is needed for sealed classes and other Kotlin-specific features
-keepattributes Signature, InnerClasses, EnclosingMethod, KotlinMetadata

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
@retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-dontwarn kotlinx.**


##---------------Begin: proguard configuration for Glide ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
<init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
**[] $VALUES;
public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
*** rewind();
}

# Uncomment for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


##---------------Begin: proguard configuration for RxJava ----------
# Uncomment if you use RxJava
#-dontwarn java.util.concurrent.Flow*

##---------------Begin: proguard configuration for Core Module ----------
# Keep Resource sealed class and all its subtypes (used in when expressions)
-keep class com.setiawan.capstone.core.data.Resource { *; }
-keep class com.setiawan.capstone.core.data.Resource$Success { *; }
-keep class com.setiawan.capstone.core.data.Resource$Loading { *; }
-keep class com.setiawan.capstone.core.data.Resource$Error { *; }
-keep class com.setiawan.capstone.core.data.Resource$* { *; }
# Keep all classes that extend Resource (for sealed class subtypes)
-keep class * extends com.setiawan.capstone.core.data.Resource { *; }

# Keep Movie data class (Parcelable with @Parcelize)
-keep class com.setiawan.capstone.core.domain.model.Movie { *; }
-keepclassmembers class com.setiawan.capstone.core.domain.model.Movie {
    <init>(...);
    <fields>;
}

# Keep repository interface and implementation
-keep interface com.setiawan.capstone.core.domain.repository.IMovieRepository { *; }
-keep class com.setiawan.capstone.core.data.MovieRepository { *; }

# Keep use case interface and implementation
-keep interface com.setiawan.capstone.core.domain.usecase.MovieUseCase { *; }
-keep class com.setiawan.capstone.core.domain.usecase.MovieInteractor { *; }
-keepclassmembers class com.setiawan.capstone.core.domain.usecase.MovieInteractor {
    <init>(...);
}

# Keep MovieAdapter and its inner classes
-keep class com.setiawan.capstone.core.presentation.MovieAdapter { *; }
-keep class com.setiawan.capstone.core.presentation.MovieAdapter$ListViewHolder { *; }
-keep class com.setiawan.capstone.core.presentation.MovieAdapter$* { *; }

# Keep Koin module top-level properties (CoreModuleKt)
# Kotlin top-level properties are compiled to static fields in a class named <ModuleName>Kt
-keep class com.setiawan.capstone.core.di.CoreModuleKt { *; }
-keepclassmembers class com.setiawan.capstone.core.di.CoreModuleKt {
    static ** databaseModule;
    static ** networkModule;
    static ** repositoryModule;
}

# Keep AppModuleKt for Koin modules
-keep class com.setiawan.capstone.di.AppModuleKt { *; }
-keepclassmembers class com.setiawan.capstone.di.AppModuleKt {
    static ** useCaseModule;
    static ** viewModelModule;
}