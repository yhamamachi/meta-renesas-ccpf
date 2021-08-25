FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_ulcb = " \
    file://can.cfg \
    file://nvme.cfg \
    file://0001-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
    file://0002-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
"

KERNEL_DEVICETREE_append_h3ulcb = " \
    renesas/r8a77951-ulcb-ccpf-sk.dtb \
"

KERNEL_DEVICETREE_append_m3ulcb = " \
    renesas/r8a77960-ulcb-ccpf-sk.dtb \
    renesas/r8a77961-ulcb-ccpf-sk.dtb \
"
