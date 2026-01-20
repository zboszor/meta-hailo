DESCRIPTION = "hailo firmware \
				hailo8 chip firmware (hailo_fw.bin) \
				the recipe copies the file to /lib/firmware/hailo/ on the target device’s root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};name=fw \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};name=lic"

SRC_URI[fw.sha256sum] = "1ba9528972091ec17bebc0dc7ea2e6f4449efe70664890f6387ccbc7b60626ee"
SRC_URI[lic.sha256sum] = "ca96445e6e33ae0a82170ea847b0925c864492f0cbb6342d42c54fd647133608"

inherit allarch

LICENSE = "LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_FILE};md5=263ee034adc02556d59ab1ebdaea2cda"

S = "${UNPACKDIR}"

FW_PATH = "${S}/${FW}"

do_install() {
	# Stores hailo8_fw.bin in the rootfs under ${nonarch_base_libdir} - /usr/lib/firmware/hailo
	install -d ${D}${nonarch_base_libdir}/firmware/hailo
	install -m 0644 ${FW_PATH} ${D}${nonarch_base_libdir}/firmware/hailo/hailo8_fw.bin
}

# Package contents
FILES:${PN} += "${nonarch_base_libdir}/firmware/hailo/hailo8_fw.bin"
