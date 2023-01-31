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
    const short marime_int = 32, marime_short = 16;
    unsigned int inst;
        scanf ("%u", &inst);

//2.
    unsigned int N, mask02 = 7 << marime_int - 3;
        mask02 &= inst;
        mask02 >>= marime_int - 3;
        N = mask02 + 1;

//3.
    unsigned int mask03 = pow (2, N * 2) - 1;
    char Operatii[N];
        inst <<= 3; 
        mask03 <<= marime_int - N * 2;
        mask03 &= inst;

        for (int i = 0; i < N; i++)
        {
            int separator = 3 << ( marime_int - 2 ) ;
            int x = separator & mask03;
                Operatii[i] = Determinare_operand(x);
                mask03 <<= 2;
        }

//4.
    unsigned int Dim, mask04 = 15 << (marime_int - 4);
        inst <<= N * 2;
        mask04 &= inst;
        mask04 >>= (marime_int - 4);
        Dim = mask04 + 1;

//5.
    unsigned short nr_de_introdus = ((N+1)*Dim)/16;
        if (nr_de_introdus < ((N+1)*Dim)/16.f){
            ++nr_de_introdus;
        }

    unsigned short numere[nr_de_introdus], operanzi[N+1];
        for (int i = 0; i < nr_de_introdus; i++){
            scanf("%hu", &numere[i]);
        }
//6.
   int biti[marime_short * (N + 1)];
        for (int j = 0; j < nr_de_introdus; j++){
            
            int masca = 1 << marime_short - 1, nr = numere[j];
                for(int i=0; i < marime_short; i++){   
                    if (nr & masca){
                        biti[marime_short * j + i] = 1;
                    }
                    else{
                        biti[marime_short * j + i] = 0;
                    }
                    nr <<= 1;
                }
        }
    for (int x=0; x < N+1 ; x++){
        int trans_bin_in_zeci = 0;
            for (int j = 0; j < Dim; j++){
                trans_bin_in_zeci += biti[Dim*x+j] * pow (2, (Dim - (j + 1)));
            }
        operanzi[x] = trans_bin_in_zeci;
    } 
        
//7.
    int rezultat = operanzi[0];
        for (int i = 0; i < N; i++){
           switch (Operatii[i]){
            case '+' : rezultat += operanzi[i+1] ; break;
            case '-' : rezultat -= operanzi[i+1] ; break;
            case '*' : rezultat *= operanzi[i+1] ; break;
            case '/' : rezultat /= operanzi[i+1] ; break;
            default : break;
            }
        }
        printf("%d\n", rezultat);
}