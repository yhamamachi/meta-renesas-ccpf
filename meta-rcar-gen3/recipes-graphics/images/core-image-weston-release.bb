require ${@"recipes-graphics/images/core-image-weston.bb" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"recipes-graphics/images/core-image-renesas-base.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"recipes-graphics/images/core-image-weston.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"core-image-renesas-base-ccpf-sk.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}

ROOTFS_POSTPROCESS_COMMAND_append = " remove_gfx_mmp_files ; "
addtask create_release_package after do_image_complete before do_build

WKS_FILE="rcar-dualpart-noloader.wks"

IMAGE_INSTALL_append = " gfx-mmp-auto-installer"

IMAGE_INSTALL_append = " kernel-devicetree"

IMAGE_INSTALL_append = " renesas-bsp-rom-writer"

remove_gfx_mmp_files() {
    find ${BASE_WORKDIR}/${MACHINE}-poky-linux/gles-user-module/1.0-r0/image -type f \
        | awk -F/ '{print $NF}' \
        | xargs -i find '${IMAGE_ROOTFS}/' -name {} -type f \
        | xargs -i rm -f '{}'

    find ${BASE_WORKDIR}/${MACHINE}-poky-linux/omx-user-module/1.0-r0/image -type f \
        | awk -F/ '{print $NF}' \
        | xargs -i find '${IMAGE_ROOTFS}/' -name {} -type f\
        | xargs -i rm -f '{}'

    rm -rf ${DEPLOY_DIR_IMAGE}/gfx_mmp
    mkdir -p ${DEPLOY_DIR_IMAGE}/gfx_mmp

    cp -rpf ${BASE_WORKDIR}/${MACHINE}-poky-linux/gles-user-module/1.0-r0/image/* \
        -t ${DEPLOY_DIR_IMAGE}/gfx_mmp

    cp -rpf ${BASE_WORKDIR}/${MACHINE}-poky-linux/omx-user-module/1.0-r0/image/* \
        -t ${DEPLOY_DIR_IMAGE}/gfx_mmp

    tar -jcf ${DEPLOY_DIR_IMAGE}/gfx_mmp.tar.bz2 \
        -C ${DEPLOY_DIR_IMAGE}/gfx_mmp .
}

do_create_release_package() {
    BINARY_DIR=${DEPLOY_DIR}/release/binary
    SOURCE_DIR=${DEPLOY_DIR}/release/source
    GFXMMP_DIR=${DEPLOY_DIR}/release/gfx_mmp

    rm -rf ${BINARY_DIR} ${SOURCE_DIR} ${GFXMMP_DIR}
    mkdir -p ${BINARY_DIR} ${SOURCE_DIR} ${GFXMMP_DIR}

    # Binary
    cp -rpf ${DEPLOY_DIR}/licenses \
        -t ${BINARY_DIR}/

    mkdir -p ${BINARY_DIR}/rootfs/
    cp -f ${DEPLOY_DIR_IMAGE}/core-image-weston-release-${MACHINE}.wic* \
        -t ${BINARY_DIR}/rootfs

    mkdir -p ${BINARY_DIR}/ipl/
    cp -f ${DEPLOY_DIR_IMAGE}/*.srec \
        -t ${BINARY_DIR}/ipl

    # source
    cp -rpf ${DEPLOY_DIR}/sources \
        -t ${SOURCE_DIR}/

    mkdir -p ${SOURCE_DIR}/yocto-layers
    ls -d ${TOPDIR}/../poky ${TOPDIR}/../meta-* \
        | xargs -i cp -rpf {} -t ${SOURCE_DIR}/yocto-layers/
    find ${SOURCE_DIR}/yocto-layers -name ".git" \
        | xargs -i rm -rf {}

    # GFX/MMP
    cp -f ${DEPLOY_DIR_IMAGE}/gfx_mmp.tar.bz2 \
        -t ${GFXMMP_DIR}/

    # Copy renesas-bsp-rom-writer
    cp -rf ${DEPLOY_DIR_IMAGE}/renesas-bsp-rom-writer \
        -t ${BINARY_DIR}/ipl
}

