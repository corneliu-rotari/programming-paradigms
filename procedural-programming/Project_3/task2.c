#include "bmp_header.h"

void Insert_op(BMP_PACK *Image) {
    BMP_PACK *Overlap = malloc(sizeof(BMP_PACK));
    Edit_op(Overlap);
    Overlap->matrix = Matrix_all(Overlap);

    int x = 0, y = 0;
    scanf("%d %d", &y, &x);
    int k = 0, h = 0;
    for (int i = x; i < Image->inhead->height; i++) {
        for (int j = y; j < Image->inhead->width; j++) {
            if ((h < Overlap->inhead->width) && (k < Overlap->inhead->height)) {
            Image->matrix[i][j] = Overlap->matrix[k][h];
            h++;
            }
        }
        h = 0;
        if ((h < Overlap->inhead->width) && (k < Overlap->inhead->height))
        k++;
    }
    Quit_op(Overlap);
}
