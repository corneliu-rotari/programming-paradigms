#include "utils.h"
#define MAX_MOVES 25
#define NR_ONE 1
#define NR_ZERO 0
#define LEFT 1
#define RIGHT 2
#define UP 3
#define DOWN 4
#define MAX_INPUT 1000
#define NR_TEN 10

int FirstA(char string[]) {
    int place = NR_ONE;
    char max = string[NR_ONE];
    for (int i = 1; i < strlen(string); i++) {
        if (max < string[i]) {
            max = string[i];
            place = i;
        }
    }
    switch (place) {
        case 1: return RIGHT; break;
        case 2: return UP; break;
        case 3: return LEFT; break;
        case 4: return DOWN; break;
        default: break;
        }
    return 0;
}

int FirstB(char string[]) {
    int lengh = (int) strlen(string);
    int k = (string[lengh-2] - '0') * NR_TEN + (string[lengh-1] - '0');
    int prim = NR_ZERO;
    for (int i = 2; i <= (k/2); i++) {
        if (k % i == NR_ZERO) {
            prim = NR_ONE;
            break;
        }
    }
    int pol = NR_ZERO;
    for (int i = 1; i < ((lengh-2)); i++) {
        if (string[i] != string[lengh-i]) {
            pol = NR_ONE;
            break;
        }
    }
    switch (pol) {
        case 0:
            switch (prim) {
                case 0: return LEFT; break;
                case 1: return RIGHT; break;
                default: break;
            }break;
        case 1:
            switch (prim) {
                case 0: return UP; break;
                case 1: return DOWN; break;
                default: break;
            }break;
        default: break;
    }
    return 0;
}

int FirstC(char string[]) {
    char aux = string[NR_ONE];
    int nr = aux - '0';
    aux = string[NR_ONE + NR_ONE];
    int k = aux - '0';
    for (int i = 0; i < strlen(string) - 3; i++) {
        string[i] = string[i + 3];
    }
    string[strlen(string)-3] = '\0';
    char numbers[MAX_MOVES];
    strncpy(numbers, string, nr);

    int sum = 0;
    for (int i = 0; i < k; i++) {
        if (k*i < nr) {
            sum = sum + (numbers[k * i] - '0');
        } else {
            sum = sum + (numbers[(k * i) % nr] - '0');
        }
    }
    switch (sum % 4) {
        case 0: return LEFT; break;
        case 1: return UP; break;
        case 2: return RIGHT; break;
        case 3: return DOWN; break;
        default: break;
        }

    return 0;
}

void SolveTask1() {
    int n = 0, m = 0;
    scanf("%d %d\n", &n, &m);

    int matrix[n][m];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            matrix[i][j] = 0;
        }
    }
    char magic_words[MAX_INPUT];
    int position[MAX_MOVES];
    char delim[]=" \n";
    int i = NR_ZERO;

    fgets(magic_words, MAX_INPUT, stdin);
    char *word = strtok(magic_words, delim);

    while (word != NULL) {
        switch (word[NR_ZERO]) {
            case 'a':
                position[i] = FirstA(word); i++;
                break;
            case 'b':
                position[i] = FirstB(word); i++;
                break;
            case 'c':
                position[i] = FirstC(word); i++;
                break;
            default: break;
        }
        word = strtok(NULL, delim);
    }
    int moves = i;
    int count = NR_ONE;
    matrix[NR_ZERO][NR_ZERO] = count;
    int aux_row = NR_ZERO, aux_col = NR_ZERO;

    for (int i = 0; i < moves; i++) {
            count++;
            switch (position[i]) {
                case 1: aux_col--; matrix[aux_row][aux_col] = count; break;
                case 2: aux_col++; matrix[aux_row][aux_col] = count; break;
                case 3: aux_row--; matrix[aux_row][aux_col] = count; break;
                case 4: aux_row++; matrix[aux_row][aux_col] = count; break;
                default: break;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}
