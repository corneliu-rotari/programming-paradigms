#include <stdio.h>
#include <math.h>
#include <stdint.h>
#define NR_32 32
#define NR_16 16
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

// 3.
    unsigned int mask03 = (int) pow(2, N * 2) - 1;
    char Operatii[N];
        inst <<= 3;
        mask03 <<= NR_32 - N * 2;
        mask03 &= inst;
        for (int i = 0; i < N; i++) {
            unsigned int separator = 3 << (NR_32 - 2);
            unsigned int x = separator & mask03;
                Operatii[i] = Determinare_operand(x);
                mask03 <<= 2;
        }
// 4.
    unsigned int Dim = 0, mask04 = BITI_1111 << (NR_32 - 4);
        inst <<= N * 2;
        mask04 &= inst;
        mask04 >>= (NR_32 - 4);
        Dim = mask04 + 1;

// 5.
    uint16_t nr_de_introdus = ((N+1)*Dim)/NR_16;
    float nr_cu_zecimale = (float) ((N+1)*Dim)/NR_16;
        if ((float) nr_de_introdus < nr_cu_zecimale) {
            ++nr_de_introdus;
        }

    uint16_t numere[nr_de_introdus], operanzi[N+1];
        for (int i = 0; i < nr_de_introdus; i++) {
            scanf("%hu", &numere[i]);
        }
// 6.
    int k = -1;
        for (int i = 0; i < nr_de_introdus; i++) {
            for (int j = 0; j < NR_16 / Dim; j++) {
                uint16_t mask06 = (uint16_t) pow(2, Dim) - 1;
                    mask06 <<= (NR_16 - Dim) - Dim * j;
                    mask06 &= numere[i];
                    operanzi[++k] = mask06 >> ((NR_16 - Dim) - Dim * j);
            }
        }
// 7.
    int rezultat = operanzi[0];
        for (int i = 0; i < N; i++) {
           switch (Operatii[i]) {
            case '+' : rezultat += operanzi[i+1]; break;
            case '-' : rezultat -= operanzi[i+1]; break;
            case '*' : rezultat *= operanzi[i+1]; break;
            case '/' : rezultat /= operanzi[i+1]; break;
            default : break;
            }
        }
        printf("%d\n", rezultat);
    return 0;
}
