# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/native-lib.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

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
src/main/cpp/zhscript2/keyword.cpp
src/main/cpp/zhscript2/util.cpp
src/main/cpp/zhscript2/lib.cpp
src/main/cpp/zhscript2/arg.cpp
src/main/cpp/zhscript2/result2.cpp
src/main/cpp/zhscript2/debug.cpp
src/main/cpp/zhscript2/var.cpp
src/main/cpp/zhscript2/qv.cpp
src/main/cpp/zhscript2/file.cpp
src/main/cpp/zhscript2/segm/call.cpp
src/main/cpp/zhscript2/segm/debug.cpp
src/main/cpp/zhscript2/segm/func.cpp
src/main/cpp/zhscript2/segm/if.cpp
src/main/cpp/zhscript2/segm/switch.cpp
src/main/cpp/zhscript2/segm/with.cpp
src/main/cpp/zhscript2/segm/guandaojie.cpp
src/main/cpp/zhscript2/segm/mod_.cpp
src/main/cpp/zhscript2/segm/mod_free.cpp
src/main/cpp/zhscript2/segm/for/base.cpp
src/main/cpp/zhscript2/segm/for/base2.cpp
src/main/cpp/zhscript2/segm/item1.cpp
src/main/cpp/zhscript2/segm/exit.cpp
src/main/cpp/zhscript2/segm/func/call.cpp
src/main/cpp/zhscript2/segm/func/param/callback.cpp
src/main/cpp/zhscript2/segm/func/param/callstack.cpp
src/main/cpp/zhscript2/segm/func/param/qv_for.cpp
src/main/cpp/zhscript2/segm/set.cpp
src/main/cpp/zhscript2/segm/alias.cpp
src/main/cpp/zhscript2/segm/dl.cpp
src/main/cpp/zhscript2/segm/def.cpp
src/main/cpp/zhscript2/segm/exec.cpp
src/main/cpp/zhscript2/segm/no.cpp
src/main/cpp/zhscript2/segm/var.cpp
src/main/cpp/zhscript2/segm/all.cpp
src/main/cpp/zhscript2/segm/load.cpp
src/main/cpp/zhscript2/segm/name.cpp
src/main/cpp/zhscript2/segm/print.cpp
src/main/cpp/zhscript2/segm/var/args.cpp
src/main/cpp/zhscript2/segm/var/arg.cpp
src/main/cpp/zhscript2/segm/var/attr.cpp
src/main/cpp/zhscript2/segm/var/reserved.cpp
src/main/cpp/zhscript2/segm/var/qv.cpp
src/main/cpp/zhscript2/segm/eval.cpp
src/main/cpp/zhscript2/segm/item.cpp
src/main/cpp/zhscript2/segm/mod.cpp
src/main/cpp/zhscript2/segm/tag.cpp
src/main/cpp/zhscript2/eval.cpp
src/main/cpp/zhscript2/equname.cpp
src/main/cpp/zhscript2/ret.cpp
src/main/cpp/zhscript2/clpars.cpp
src/main/cpp/zhscript2/textpars.cpp
src/main/cpp/zhscript2/shell2.cpp
src/main/cpp/zhscript2.cpp
)

target_link_libraries(
zhscript2
log
android
)

add_library(
        miio
        SHARED
        src/main/cpp/miio.cpp
        src/main/cpp/miio/socket_class.cpp
        src/main/cpp/miio/log_process.cpp
        src/main/cpp/miio/type_tools.cpp
        src/main/cpp/miio/mi_home_device.cpp
        src/main/cpp/miio/md5.cpp
        src/main/cpp/miio/aes.c
)
target_link_libraries(
        miio
        log
)
