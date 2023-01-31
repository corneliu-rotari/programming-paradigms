#include "bmp_header.h"

void Fill(unsigned int x, unsigned int y, pixel old, pixel new,
            BMP_PACK *Image) {
    if ((Image->matrix[x][y].R == old.R) && (Image->matrix[x][y].G == old.G)
            && (Image->matrix[x][y].B == old.B)) {
        if (((x == Image->inhead->height) && (y == Image->inhead->width)) ||
            ((x < 0) && (y < 0))) {
                return;
        }
            Image->matrix[x][y].R = new.R;
            Image->matrix[x][y].G = new.G;
            Image->matrix[x][y].B = new.B;

        if (((x+1) < Image->inhead->height) && (y < Image->inhead->width)) {
            Fill(x+1, y, old, new, Image);
        }
        if ((x < Image->inhead->height) && ((y+1) < Image->inhead->width)) {
            Fill(x, y+1, old, new, Image);
        }
        if (((x-1) < Image->inhead->height) && (y < Image->inhead->width)) {
            Fill(x-1, y, old, new, Image);
        }
        if ((x < Image->inhead->height) && ((y-1) < Image->inhead->width)) {
            Fill(x, y-1, old, new, Image);
        }
    }
}

void Fill_op(BMP_PACK *Image, SET_LINE *line) {
    unsigned int x = 0, y = 0;
    scanf("%d %d", &y, &x);
    pixel old, new;
    old.R = Image->matrix[x][y].R;
    old.G = Image->matrix[x][y].G;
    old.B = Image->matrix[x][y].B;

    new.R = line->color->R;
    new.G = line->color->G;
    new.B = line->color->B;
    Fill(x, y, old, new, Image);
}
