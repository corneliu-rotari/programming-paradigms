/* Tells the compiler not to add padding for these structs. This may
   be useful when reading/writing to binary files.
   http://stackoverflow.com/questions/3318410/pragma-pack-effect
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
#define MAX 50
#define MAT 1000
#pragma pack(1)

typedef struct {
    unsigned char  fileMarker1; /* 'B' */
    unsigned char  fileMarker2; /* 'M' */
    unsigned int   bfSize; /* File's size */
    unsigned short unused1; /* Aplication specific */
    unsigned short unused2; /* Aplication specific */
    unsigned int   imageDataOffset; /* Offset to the start of image data */
} bmp_fileheader;

typedef struct {
    unsigned int   biSize; /* Size of the info header - 40 bytes */
    signed int     width; /* Width of the image */
    signed int     height; /* Height of the image */
    unsigned short planes;
    unsigned short bitPix; /* for each channel R, G, B we need 8 bits */
    unsigned int   biCompression; /* Type of compression */
    unsigned int   biSizeImage; /* Size of the image data */
    int            biXPelsPerMeter;
    int            biYPelsPerMeter;
    unsigned int   biClrUsed;
    unsigned int   biClrImportant;
} bmp_infoheader;

typedef struct {
    unsigned char B;
    unsigned char G;
    unsigned char R;
} pixel;

typedef struct {
    FILE *Image;  // Image file open
    bmp_fileheader *fihead;  // file header
    bmp_infoheader *inhead;  // info header
    pixel **matrix;  // matrix of pixels
} BMP_PACK;

typedef struct {
    pixel *color;
    unsigned char width;
} SET_LINE;


#pragma pack()

void Save_op(BMP_PACK *Image);
void Quit_op(BMP_PACK *Image);
void Edit_op(BMP_PACK *Image_saved);
pixel **Matrix_all(BMP_PACK *Image_saved);

void Insert_op(BMP_PACK *Image);

void nr_swap(int *x, int *y);
void Set_color_op(SET_LINE *line);
void Set_width_op(SET_LINE *line);
void Width_line(int x, int y, SET_LINE *line, BMP_PACK *Image);
void Line_creation(int *x1_p, int *x2_p, int *y1_p, int *y2_p,
                    BMP_PACK *Image, SET_LINE *line);
void Draw_line_op(BMP_PACK *Image, SET_LINE *line);
void Draw_rectangle_op(BMP_PACK *Image, SET_LINE *line);
void Draw_triangle_op(BMP_PACK *Image, SET_LINE *line);

void Fill_op(BMP_PACK *Image, SET_LINE *line);
void Fill(unsigned int x, unsigned int y, pixel old, pixel new,
            BMP_PACK *Image);
