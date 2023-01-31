#include <stdio.h>
#include <math.h>
#include <stdint.h>
#define NR_32 32
#define BITI_111 7
#define BITI_1111 15

char Determinare_operand(unsigned int z) {
    z >>= NR_32 - 2;
    switch (z) {
        case 0 : return '+'; break;
        case 1 : return '-'; break;
        case 2 : return '*'; break;
        case 3 : return '/'; break;
        default : return ' '; break;
    }
}

int main() {
// 1.
    unsigned int inst = 0;
        scanf("%u", &inst);

// 2.
    unsigned int N = 0, mask02 = BITI_111 << (NR_32 - 3);
        mask02 &= inst;
        mask02 >>= NR_32 - 3;
        N = mask02 + 1;
            printf("%d ", N);

// 3.
    unsigned int mask03 = (unsigned int) pow(2, N * 2) - 1;
        inst <<= 3;
        mask03 <<= NR_32 - N * 2;
        mask03 &= inst;
            for (int i = 0; i < N; i++) {
                unsigned int separator = 3 << (NR_32 - 2);
                unsigned int x = separator & mask03;
            printf("%c ", Determinare_operand(x));
                    mask03 <<= 2;
            }

// 4.
    unsigned int Dim = 0, mask04 = (BITI_1111) << (NR_32 - 4);
        inst <<= N * 2;
        mask04 &= inst;
        mask04 >>= (NR_32 - 4);
        Dim = mask04 + 1;
    printf("%d\n", Dim);
    return 0;
}
