# meta-docker/meta-rcar-gen3

This layer provides the configuration to use CCPF SK board with R-Car Starter Kit

## Dependencies

This layer depends on:

* URI: git://git.yoctoproject.org/poky
  * branch: dunfell
  * revision: 424296bf9bb4bae27febf91bce0118df09ce5fa1

* URI: git://github.com/renesas-rcar/meta-renesas
  * layers; meta-rcar-gen3
  * branch: dunfell
  * revision: fd8ab2bb1b5ca98483c6f4aecf09a85c4d6bc016

* URI: git://git.openembedded.org/meta-openembedded
  * layers: meta-oe, meta-python
  * branch: dunfell
  * revision: f2d02cb71eaff8eb285a1997b30be52486c160ae

## Patches

Please submit any patches against the meta-renesas-ccpf/meta-rcar-gen3 layer to the the maintainer:

Maintainer: Tomohiro Komagata <tomohiro.komagata.aj at renesas.com>

## Quick Start

1. TEMPLATECONF=$PWD/meta-renesas-ccpf/meta-rcar-gen3/docs/sample/conf/${h3ulcb|m3ulcb}-ccpf-sk/bsp/ \
   source poky/oe-init-build-env rcar-build
2. bitbake core-image-minimal
3. Prepare a SD card and set the u-boot environment variables. Please refer the [R-Car page on eLinux Wiki](https://elinux.org/R-Car/Boards/Yocto-Gen3/v5.1.0#Running_Yocto_images).
4. Boot your R-Car Starter Kit.

## Supported Boards/Machines

- Renesas Electronics Corporation. R-Car Starter Kit premier(H3ULCB) (R8A7795) on the CCPF SK board
- Renesas Electronics Corporation. R-Car Starter Kit pro(M3ULCB) (R8A7796) on the CCPF SK board
