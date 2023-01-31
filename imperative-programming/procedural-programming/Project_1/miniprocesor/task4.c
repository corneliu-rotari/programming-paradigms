#include <stdio.h> 
#include <math.h>

void f1(int nr){
    int masca = 1 << 31;
    int i;

    for(i=0; i<32; i++){
        if (nr & masca){
            printf("1");
        }
        else{
            printf("0");
        }
        nr<<=1;
        if ((i+1)%4==0) printf(" ");
    }
    printf("\n");
}
void f2(unsigned short nr){
    unsigned short masca = 1 << 15;
    int i;

    for(i=0; i<16; i++){
        if (nr & masca){
            printf("1");
        }
        else{
            printf("0");
        }
        nr<<=1;
        if ((i+1)%4==0) printf(" ");
    }
    printf("\n");
}

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
//1. Citim numarul;
    unsigned int inst;
        scanf ("%u", &inst);

//2. Determinam numarul de instructiuni ce vor fi executate
    unsigned int N, mask02 = 7 << 29;
        mask02 &= inst;
        mask02 >>= 29;
        N = mask02 + 1;

//3. Determinam instructiunile ce vor fi executate
    unsigned int mask03 = pow(2,N*2)-1;
    char Operatii[N];
        inst <<= 3; 
        mask03 <<= 32 - N*2;
        mask03 &= inst;

        for (int i = 0; i < N; i++)
        {
            int separator = 3<<30;
            int x = separator & mask03;
                Operatii[i] = Determinare_operand(x);
                mask03 <<= 2;
        }

//4. Determinam dimensiunea unui operand{}
    unsigned int Dim, mask04=15<<(32-4);
        inst <<= N*2;
        mask04 &= inst;
        mask04 >>= (32-4);
        Dim = mask04+1;

//5. Calculam si citimde cate nr avem nevoie
    unsigned short nr_de_introdus = ((N+1)*Dim)/16;
        if (nr_de_introdus < ((N+1)*Dim)/16.f){
            ++nr_de_introdus;
        }

    unsigned short numere[nr_de_introdus], operanzi[N+1];
        for (int i = 0; i < nr_de_introdus; i++){
            scanf("%hu", &numere[i]);
            
        }
//6. Calculam dimensiunea si citim care sunt operanzii   
    short k = -1;
    unsigned short Distanta = 16 - Dim ;    
        if (16 % Dim == 0){
            for (int i = 0; i < nr_de_introdus; i++){
                for (int j = 0; j < N+1 ; j++){
                            if (Distanta - Dim*(j-1) == 0 ){
                                break;
                            }
                            unsigned short mask06 = pow(2,Dim)-1;
                                mask06 <<= Distanta-Dim*j;
                                mask06 &=numere[i];
                                operanzi[++k] = mask06 >> (Distanta-Dim*j);
                }
                
            }
        }else{  
            int biti[16*(N+1)];
            for (int j = 0; j < nr_de_introdus; j++){
                int masca = 1 << 15;
                int nr = numere[j];

                    for(int i=0; i<16; i++){
                        if (nr & masca){
                            biti[16*j+i] = 1;
                        }
                        else{
                            biti[16*j+i] = 0;
                        }
                        nr<<=1;
                }
            }
            for (int x=0; x < N+1 ; x++){
                int ciuma=0;
                for (int j = 0; j < Dim; j++){
                    ciuma += biti[Dim*x+j]*pow(2,(Dim-(j+1)));
                }
                operanzi[x]=ciuma;
            }
        }      
        
       
//7. Calculam rezultatul dintre operanzi si operatiile corespunzatoare
    /* int rezultat = 0;
        for (int j = 0; j < N; j++ ){
                switch (Operatii[j]){
                    case '*' : operanzi[j+1] *= operanzi[j] ; operanzi[j] = 0 ; Operatii[j] = '?'; break;
                    case '/' : operanzi[j+1] = operanzi[j]/operanzi[j+1] ; operanzi[j] = 0 ; Operatii[j] = '?'; break;
                    default : break;
                }
        }       
        for (int i = 0; i < N; i++){
            switch (Operatii[i]){
                case '+' : operanzi[i+1] += operanzi[i] ; if (Operatii[i+1]=='?') Operatii[i+1]='+' ; break;
                case '-' : operanzi[i+1] = operanzi[i] - operanzi[i+1] ; if (Operatii[i+1]=='?') Operatii[i+1]='-' ;break;
                default : break;
            }
        }
        rezultat = operanzi[N+1];
        printf("%d", rezultat);

}*/


int rezultat = 0;
            for (int j = 0; j < N; j++ ){
                switch (Operatii[j]){
                    case '*' :  operanzi[j+1] *= operanzi[j] ; 
                                operanzi[j] = 0 ;Operatii[j] = '+';break;
                    case '/' :  operanzi[j+1] = operanzi[j]/operanzi[j+1] ;  
                                operanzi[j] = 0 ;Operatii[j] = '+';break;
                    default : break;
                }
            //printf("%d %d\n", operanzi[j], j);
            }
            rezultat = operanzi[0];
            for (int i = 0; i < N; i++){
            switch (Operatii[i]){
                case '+' : rezultat += operanzi[i+1] ; break;
                case '-' : rezultat -= operanzi[i+1] ; break;
                default : break;
            }
           // printf("%d\n", operanzi[i+1]);
           // printf("%d\n", rezultat);
        }
    printf("%d\n", rezultat);
}