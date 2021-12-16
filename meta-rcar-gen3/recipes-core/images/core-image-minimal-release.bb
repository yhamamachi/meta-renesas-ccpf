LICENSE = "MIT"

require ${@"recipes-core/images/core-image-minimal.bb" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"recipes-graphics/images/core-image-renesas-base.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"recipes-graphics/images/core-image-renesas-base-ccpf-sk.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}

WKS_FILE="rcar-dualpart-noloader_ext4.wks"

IMAGE_INSTALL_append = " kernel-devicetree"
IMAGE_INSTALL_append = " kernel-modules"
IMAGE_INSTALL_append = " renesas-bsp-rom-writer"
IMAGE_INSTALL_append = " flash-writer"

ROOTFS_POSTPROCESS_COMMAND_append = "copy_kernel_modules; "
addtask create_release_package after do_image_complete before do_build

copy_kernel_modules() {
    install -d ${IMAGE_ROOTFS}/boot/
    install -m 644 ${DEPLOY_DIR_IMAGE}/modules-${MACHINE}.tgz ${IMAGE_ROOTFS}/boot/
}

do_create_release_package() {
    BINARY_DIR=${DEPLOY_DIR}/release/binary
    SOURCE_DIR=${DEPLOY_DIR}/release/source
    rm -rf ${BINARY_DIR} ${SOURCE_DIR}
    mkdir -p ${BINARY_DIR} ${SOURCE_DIR}

    # binary
    mkdir -p ${BINARY_DIR}/rootfs/ ${BINARY_DIR}/ipl/
    cp -rpf ${DEPLOY_DIR}/licenses -t ${BINARY_DIR}
    cp -f ${DEPLOY_DIR_IMAGE}/${PN}-${MACHINE}.wic* -t ${BINARY_DIR}/rootfs
    cp -f ${DEPLOY_DIR_IMAGE}/*.srec -t ${BINARY_DIR}/ipl

    # source
    mkdir -p ${SOURCE_DIR}/yocto-layers
    cp -rpf ${DEPLOY_DIR}/sources -t ${SOURCE_DIR}
    ls -d ${TOPDIR}/../poky ${TOPDIR}/../meta-* \
        | xargs -i cp -rpf {} -t ${SOURCE_DIR}/yocto-layers/
    find ${SOURCE_DIR}/yocto-layers -name ".git" \
        | xargs -i rm -rf {}

    # Copy renesas-bsp-rom-writer
    cp -rf ${DEPLOY_DIR_IMAGE}/renesas-bsp-rom-writer -t ${BINARY_DIR}/ipl

    # Copy flash-writer to renesas-bsp-rom-writer
    cp -f ${DEPLOY_DIR_IMAGE}/AArch64_Flash_writer_SCIF_DUMMY_CERT_E6300400_ULCB.mot \
        -t ${BINARY_DIR}/ipl/renesas-bsp-rom-writer/starterkit/
}
