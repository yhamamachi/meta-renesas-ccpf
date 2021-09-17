FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SUPPORT_LTTNG = " \
    file://lttng.cfg \
"

SRC_URI_append_ulcb = " \
    file://can.cfg \
    file://nvme.cfg \
    file://0001-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
    file://0002-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
    file://0003-soc-renesas-rcar_s2ram-Add-new-support-for-S2RAM-exe.patch \
    file://0004-arm64-dts-renesas-Add-ext-interrupt-for-s2ram.patch \
    file://0005-can-rcar_can-Fix-suspend-resume.patch \
    ${@oe.utils.conditional("USE_LTTNG", "1", "${SUPPORT_LTTNG}", "", d)} \
"

KERNEL_DEVICETREE_append_h3ulcb = " \
    renesas/r8a77951-ulcb-ccpf-sk.dtb \
"

KERNEL_DEVICETREE_append_m3ulcb = " \
    renesas/r8a77960-ulcb-ccpf-sk.dtb \
    renesas/r8a77961-ulcb-ccpf-sk.dtb \
"
