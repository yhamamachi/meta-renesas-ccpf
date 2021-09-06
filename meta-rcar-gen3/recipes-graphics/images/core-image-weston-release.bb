require ${@"recipes-graphics/images/core-image-weston.bb" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"recipes-graphics/images/core-image-renesas-base.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"recipes-graphics/images/core-image-weston.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}
require ${@"core-image-renesas-base-ccpf-sk.inc" if "rcar-gen3" in d.getVar("OVERRIDES") else ""}

ROOTFS_POSTPROCESS_COMMAND_append = " remove_gfx_mmp_files ; "

WKS_FILE="rcar-dualpart-noloader.wks"

IMAGE_INSTALL_append = " gfx-mmp-auto-installer"

IMAGE_INSTALL_append = " kernel-devicetree"

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


