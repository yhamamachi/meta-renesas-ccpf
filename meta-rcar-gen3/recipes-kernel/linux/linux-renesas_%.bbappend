FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SUPPORT_LTTNG = " \
    file://lttng.cfg \
"

SRC_URI_append_ulcb = " \
    file://can.cfg \
    file://nvme.cfg \
    file://0001-dt-bindings-arm-renesas-Document-Renesas-CCPF-SK-boa.patch \
    file://0002-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
    file://0003-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
    file://0004-soc-renesas-rcar_s2ram-Add-new-support-for-S2RAM-exe.patch \
    file://0005-arm64-dts-renesas-Add-ext-interrupt-for-s2ram.patch \
    file://0006-can-rcar_can-Fix-suspend-resume.patch \
    file://0007-arm64-dts-renesas-Add-support-for-CCPF-SK-with-R-Car.patch \
    file://0008-arm64-dts-renesas-Add-support-pwm0-for-CCPF-SK.patch \
    file://0009-PCI-rcar-Don-t-allocate-extra-memory-for-the-MSI-cap.patch \
    file://0010-PCI-rcar-Convert-to-MSI-domains.patch \
    ${@oe.utils.conditional("USE_LTTNG", "1", "${SUPPORT_LTTNG}", "", d)} \
"

KERNEL_DEVICETREE_append_h3ulcb = " \
    renesas/r8a77951-ulcb-ccpf-sk.dtb \
    renesas/r8a779m1-ulcb-ccpf-sk.dtb \
"

KERNEL_DEVICETREE_append_m3ulcb = " \
    renesas/r8a77960-ulcb-ccpf-sk.dtb \
    renesas/r8a77961-ulcb-ccpf-sk.dtb \
"
