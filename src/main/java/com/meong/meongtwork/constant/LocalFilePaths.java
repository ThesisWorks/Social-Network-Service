package com.meong.meongtwork.constant;

public enum LocalFilePaths {
    BOARD_IMAGE_DIR_PATH("uploads/board/images/"),
    DEFAULT_BOARD_IMAGE_PATH("images/base_board_image.png");

    private final String path;

    LocalFilePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
