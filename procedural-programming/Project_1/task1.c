#include <stdio.h> 
#include <math.h>

char Determinare_operand(unsigned int z){
    z >>= 30;
    switch (z){
        case 0 : return '+'; break;
        case 1 : return '-'; break;
        case 2 : return '*'; break;
        case 3 : return '/'; break;
        default : return ' '; break;
    }
}

int main(){
//1.     
    const short marime_int = 32 ;
    unsigned int inst;
        scanf ("%u", &inst);

//2.    
    unsigned int N, mask02 = 7 << marime_int - 3;
        mask02 &= inst;
        mask02 >>= marime_int - 3;
        N = mask02 + 1;
            printf("%d ", N);

//3.    
    unsigned int mask03 = pow (2, N * 2) - 1;
        inst <<= 3; 
        mask03 <<= marime_int - N * 2;
        mask03 &= inst;
            for (int i = 0; i < N; i++){
                int separator = 3 << (marime_int - 2);
                int x = separator & mask03;
            printf("%c ", Determinare_operand(x));
                    mask03 <<= 2;
            }

//4.
    unsigned int Dim, mask04 = 15 << (marime_int - 4);
        inst <<= N * 2;
        mask04 &= inst;
        mask04 >>= (marime_int - 4);
        Dim = mask04 + 1;
    printf("%d\n", Dim);
}