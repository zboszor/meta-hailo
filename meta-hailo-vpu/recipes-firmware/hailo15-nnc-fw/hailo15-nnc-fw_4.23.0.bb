DESCRIPTION = "Hailo15_NNC FW. \
               This recipe copy the already compiled Hailo15_NNC firmware to the image"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo15/Hailort/${PV}/FW"
FW = "${HAILO_HOST_NAME}_nnc_fw.${PV}.bin"
FW_DST = "${HAILO_HOST_NAME}_nnc_fw.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};name=fw \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};name=lic"

# 403 forbidden, so this firmware cannot be downloaded
#SRC_URI[fw.sha256sum] = "???"
SRC_URI[lic.sha256sum] = "ca96445e6e33ae0a82170ea847b0925c864492f0cbb6342d42c54fd647133608"

inherit allarch

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

S = "${UNPACKDIR}"

FW_PATH = "${S}/${FW}"
FW_DST_DIR = "${nonarch_base_libdir}/firmware/hailo"
FW_DST_PATH = "${FW_DST_DIR}/${FW_DST}"

do_install() {
	install -d ${D}${FW_DST_DIR}
	install -m 0644 ${FW_PATH} ${D}${FW_DST_PATH}
}

# Package contents
FILES:${PN} += "${FW_DST_PATH}"
