#include "bmp_header.h"

void nr_swap(int *x, int *y) {
    int temp = *x;
    *x = *y;
    *y = temp;
}

void Set_color_op(SET_LINE *line) {
    line->color = malloc(sizeof(pixel));
    scanf("%hhd%hhd%hhd", &line->color->R, &line->color->G, &line->color->B);
}

void Set_width_op(SET_LINE *line) {
    scanf("%hhd", &line->width);
}

void Width_line(int x, int y, SET_LINE *line, BMP_PACK *Image) {
    int w = ((line->width)/2);
    for (int k = (0 - w) ; k < (w + 1); k++) {
        for (int o = (0 - w); o < (w + 1); o++) {
            if (((x + k) < 0) || ((y + o) < 0)) {
                break;
            }
            if (((x + k) < Image->inhead->height) &&
                ((y + o) < Image->inhead->width)) {
                Image->matrix[x + k][y + o] = Image->matrix[x][y];
            }
        }
    }
}

void Line_creation(int *x1_p, int *x2_p, int *y1_p, int *y2_p,
                    BMP_PACK *Image, SET_LINE *line) {
    int x1 = (*x1_p), x2 = (*x2_p), y1 = (*y1_p), y2 = (*y2_p);
    if (((abs(x2-x1) > abs(y2-y1)) && (x1 > x2)) ||
        ((abs(x2-x1) < abs(y2-y1)) && (y1 > y2))) {
        nr_swap(&x1, &x2);
        nr_swap(&y1, &y2);
    }
    if ((x2-x1) > (y2-y1)) {
        for (int i = x1; i < x2; i++) {
            int j = (((y2 - y1) * (i - x1)) + y1 * (x2 - x1)) / (x2 - x1);
            if ((i < Image->inhead->height) && (j < Image->inhead->width)) {
                Image->matrix[i][j].R = line->color->R;
                Image->matrix[i][j].G = line->color->G;
                Image->matrix[i][j].B = line->color->B;
                Width_line(i, j, line, Image);
            }
        }
    } else {
        for (int j = y1; j < y2; j++) {
            int i = (((x2 - x1) * (j - y1)) + x1 * (y2 - y1)) / (y2 - y1);
            if ((i < Image->inhead->height) && (j < Image->inhead->width)) {
            Image->matrix[i][j].R = line->color->R;
            Image->matrix[i][j].G = line->color->G;
            Image->matrix[i][j].B = line->color->B;
            Width_line(i, j, line, Image);
            }
        }
    }
    Width_line(x1, y1, line, Image);
    Width_line(x2, y2, line, Image);
}

void Draw_line_op(BMP_PACK *Image, SET_LINE *line) {
    int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
    scanf("%d%d%d%d", &y1, &x1, &y2, &x2);
    Line_creation(&x1, &x2, &y1, &y2, Image, line);
}

void Draw_rectangle_op(BMP_PACK *Image, SET_LINE *line) {
     int x1 = 0,
        y1 = 0,
        width = 0,
        height = 0;
     scanf("%d%d%d%d", &y1, &x1, &width, &height);
     int x2 = x1 + height,
        y2 = y1 + width;

    Line_creation(&x1, &x2, &y1, &y1, Image, line);
    Line_creation(&x2, &x2, &y1, &y2, Image, line);
    Line_creation(&x2, &x1, &y2, &y2, Image, line);
    Line_creation(&x1, &x1, &y1, &y2, Image, line);
}

void Draw_triangle_op(BMP_PACK *Image, SET_LINE *line) {
    int x1 = 0, x2 = 0, y1 = 0, y2 = 0, x3 = 0, y3 = 0;
    scanf("%d %d %d %d %d %d", &y1, &x1, &y2, &x2, &y3, &x3);

    Line_creation(&x1, &x2, &y1, &y2, Image, line);
    Line_creation(&x3, &x2, &y3, &y2, Image, line);
    Line_creation(&x1, &x3, &y1, &y3, Image, line);
}
