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
    file://0011-gpio-pca953x-do-not-ignore-i2c-errors.patch \
    file://0012-media-v4l2-async-workaround-for-RCAR-VIN.patch \
    file://0013-media-platform-rcar-vin-add-GREY-format.patch \
    file://0014-media-platform-rcar-vin-add-G-S_PARM-ioctl.patch \
    file://0015-media-i2c-imx219-Set-subdevice-fwnode.patch \
    file://0016-media-i2c-imx219-Set-8-bit-SBGGR-mode-by-default.patch \
    file://0017-media-rcar-vin-Fix-VNIS_REG-settings.patch \
    file://0018-media-i2c-imx219-Align-default-frame-width-to-32.patch \
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
