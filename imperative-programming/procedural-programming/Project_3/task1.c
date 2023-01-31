#include "bmp_header.h"

void Save_op(BMP_PACK *Image) {
    char path[MAX];
    scanf("%s", path);
    FILE *Location = NULL;
    Location = fopen(path, "wb+");
    fwrite(Image->fihead, sizeof(bmp_fileheader), 1, Location);
    fwrite(Image->inhead, sizeof(bmp_infoheader), 1, Location);
    for (int i = 0; i < Image->inhead->height; i++) {
        for (int j = 0; j < Image->inhead->width; j++) {
            fwrite(&Image->matrix[i][j], sizeof(pixel), 1, Location);
        }
        int off = Image->inhead->width % 4;
        if (off != 0) {
            for (int j = 0; j < off; j++) {
                unsigned char x = 0;
                fwrite(&x, sizeof(char), 1, Location);
            }
        }
    }
    fclose(Location);
}
void Quit_op(BMP_PACK *Image) {
    for (int j = 0; j < Image->inhead->height; j++) {
        free(Image->matrix[j]);
    }
    free(Image->matrix);
    free(Image->inhead);
    free(Image->fihead);
    fclose(Image->Image);
    free(Image);
}

void Edit_op(BMP_PACK *Image_saved) {
    char path[MAX];
    scanf("%s", path);
    Image_saved->Image = fopen(path, "rb");
    if (Image_saved->Image == NULL) {
        printf("%s : No such file \n", path);
        return;
    }
    Image_saved->fihead = malloc(sizeof(bmp_fileheader));
    Image_saved->inhead = malloc(sizeof(bmp_infoheader));
    fread(Image_saved->fihead, sizeof(bmp_fileheader), 1, Image_saved->Image);
    fread(Image_saved->inhead, sizeof(bmp_infoheader), 1, Image_saved->Image);
}

pixel **Matrix_all(BMP_PACK *Image_saved) {
    Image_saved->matrix = (pixel **)malloc(sizeof(pixel*) *
                        Image_saved->inhead->height);

    for (int j = 0; j < Image_saved->inhead->height; j++) {
        Image_saved->matrix[j] = (pixel*) malloc(sizeof(pixel)*
                    Image_saved->inhead->width);
    }
    for (int i = 0; i < Image_saved->inhead->height; i++) {
        for (int j = 0; j < Image_saved->inhead->width; j++) {
            fread(&Image_saved->matrix[i][j], sizeof(pixel), 1,
                    Image_saved->Image);
        }
        int off = Image_saved->inhead->width % 4;
        if (off != 0) {
            fseek(Image_saved->Image, off, 1);
        }
    }
    return Image_saved->matrix;
}
