
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("anzuowanju")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             anzuowanju

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             native-lib.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       anzuowanju

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

# -Dver_no_cls_ini_ -Dver_no_cls_ini2_ -Dver_clang_3_8_arm64_v8a_ -Dver_chk_up_
# -Dver_no_std_to_string_
add_definitions(-Dvar_system_=\"android\"
        -Dver_no_asm_
        -Dver_ex_print_ -Dver_ex_file_get_)
add_library(
        zhscript2
        SHARED
        zhscript2/keyword.cpp
        zhscript2/util.cpp
        zhscript2/lib.cpp
        zhscript2/arg.cpp
        zhscript2/result2.cpp
        zhscript2/debug.cpp
        zhscript2/var.cpp
        zhscript2/qv.cpp
        zhscript2/file.cpp
        zhscript2/segm/call.cpp
        zhscript2/segm/debug.cpp
        zhscript2/segm/func.cpp
        zhscript2/segm/if.cpp
        zhscript2/segm/switch.cpp
        zhscript2/segm/with.cpp
        zhscript2/segm/guandaojie.cpp
        zhscript2/segm/mod_.cpp
        zhscript2/segm/mod_free.cpp
        zhscript2/segm/for/base.cpp
        zhscript2/segm/for/base2.cpp
        zhscript2/segm/item1.cpp
        zhscript2/segm/exit.cpp
        zhscript2/segm/func/call.cpp
        zhscript2/segm/func/param/callback.cpp
        zhscript2/segm/func/param/callstack.cpp
        zhscript2/segm/func/param/qv_for.cpp
        zhscript2/segm/set.cpp
        zhscript2/segm/alias.cpp
        zhscript2/segm/dl.cpp
        zhscript2/segm/def.cpp
        zhscript2/segm/exec.cpp
        zhscript2/segm/no.cpp
        zhscript2/segm/var.cpp
        zhscript2/segm/all.cpp
        zhscript2/segm/load.cpp
        zhscript2/segm/name.cpp
        zhscript2/segm/print.cpp
        zhscript2/segm/var/args.cpp
        zhscript2/segm/var/arg.cpp
        zhscript2/segm/var/attr.cpp
        zhscript2/segm/var/reserved.cpp
        zhscript2/segm/var/qv.cpp
        zhscript2/segm/eval.cpp
        zhscript2/segm/item.cpp
        zhscript2/segm/mod.cpp
        zhscript2/segm/tag.cpp
        zhscript2/eval.cpp
        zhscript2/equname.cpp
        zhscript2/ret.cpp
        zhscript2/clpars.cpp
        zhscript2/textpars.cpp
        zhscript2/shell2.cpp
        zhscript2.cpp
)

target_link_libraries(
        zhscript2
        log
        android
)

add_library(
        miio
        SHARED
        miio.cpp
        miio/socket_class.cpp
        miio/log_process.cpp
        miio/type_tools.cpp
        miio/mi_home_device.cpp
        miio/md5.cpp
        miio/aes.c
)
target_link_libraries(
        miio
        log
)
