#include "bmp_header.h"

int main() {
    int open = 0;
    char *operation = calloc(MAX, sizeof(char));
    BMP_PACK *Image = malloc(sizeof(BMP_PACK));
    SET_LINE *Line = malloc(sizeof(SET_LINE));
    int set_count = 0;

    while (open == 0) {
        scanf("%s", operation);

        if (strncmp(operation, "edit", MAX) == 0) {
            Edit_op(Image);
            Image->matrix = Matrix_all(Image);
        } else if (strncmp(operation, "save", MAX) == 0) {
            Save_op(Image);
        } else if (strncmp(operation, "quit", MAX) == 0) {
            Quit_op(Image);
            open = 1;
        } else if (strncmp(operation, "insert", MAX) == 0) {
            Insert_op(Image);
        } else if (strncmp(operation, "set", MAX) == 0) {
            scanf("%s", operation);
            if (strncmp(operation, "draw_color", MAX) == 0) {
                if (set_count == 1) free(Line->color);
                Set_color_op(Line);
                if (set_count == 0) set_count++;
            } else if (strncmp(operation, "line_width", MAX) == 0) {
                Set_width_op(Line);
            }
        } else if (strncmp(operation, "draw", MAX) == 0) {
            scanf("%s", operation);
            if (strncmp(operation, "line", MAX) == 0) {
                Draw_line_op(Image, Line);
            } else if (strncmp(operation, "rectangle", MAX) == 0) {
                Draw_rectangle_op(Image, Line);
            } else if (strncmp(operation, "triangle", MAX) == 0) {
                Draw_triangle_op(Image, Line);
            }
        } else if (strncmp(operation, "fill", MAX) == 0) {
            Fill_op(Image, Line);
        } else {
            printf("No such operation : %s\n", operation);
        }
    }
    free(Line->color);
    free(Line);
    free(operation);
    return 0;
}
