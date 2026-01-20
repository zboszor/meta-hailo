DESCRIPTION = "gsthailotools GStreamer plugin \
               compiles the tappas libgsthailotools gstreamer plugin \ 
               and copies it to usr/lib/gstreamer-1.0 (gstreamer's plugins directory) "

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM += "file://../../LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git@github.com/hailo-ai/tappas.git;protocol=https;branch=master"
SRCREV = "84e6617ebfaa381ab226d59eb56f6587234415f8"

SRC_URI += "file://0001-Fix-build-with-newer-xtensor-versions.patch;patchdir=${S}/../.."

inherit hailotools-base

do_install:append() {
    rm -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so
    rm -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so.[0-9]
    mv -f ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so.${PV} ${D}/${libdir}/gstreamer-1.0/libgsthailotools.so
}


DEPENDS += "glib-2.0-native glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base rapidjson cppzmq zeromq"
EXTRA_OEMESON += " \
    -Dlibrapidjson='${STAGING_INCDIR}/rapidjson' \
    "

# libgsthailotools requires opencv, xtensor, xtl, and libgsthailo to compile and run
TAPPAS_BUILD_TARGET = "plugins"

FILES:${PN} += "${libdir}/gstreamer-1.0/libgsthailotools.so  ${libdir}/libgsthailometa.so.${PV} ${libdir}/libhailo_tracker.so.${PV}"
FILES:${PN}-lib += "${libdir}/libgsthailometa.so.${PV} ${libdir}/libhailo_tracker.so.${PV} ${libdir}/gstreamer-1.0/libgsthailotools.so"
RDEPENDS:${PN}-staticdev = ""
RDEPENDS:${PN}-dev = ""
RDEPENDS:${PN}-dbg = ""
