#include "utils.h"
#define NR_TEN 10
#define MAX 1000
#define NR_ONE 1
#define NR_ZERO 0
#define SIZE_ALPHA 26
#define NR_TWO 2

void Caesar_Code(char *addres) {
    int key = 0;
    unsigned char crypt[MAX];
    scanf("%d", &key);
    scanf("%s", crypt);
    int length = (int) strlen(crypt);
    char nr[] = "1234567890",
        lett[] = "abcdefghijklmnopqrstuvwxyz",
        LETT[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        *pointer = NULL;
    int nbr_key = (key % NR_TEN),
        ltr_key = (key - SIZE_ALPHA * (key / SIZE_ALPHA));

    for (int i = 0; i < length; i++) {
        pointer = strchr(nr, crypt[i]);
        if (pointer != NULL) {
            if (((crypt[i] - '0') - nbr_key) < NR_ZERO) {
                crypt[i] = crypt[i] + (NR_TEN - nbr_key);
            } else {
                crypt[i] = crypt[i] - nbr_key;
            }
        } else {
            pointer = strchr(lett, crypt[i]);
            if (pointer != NULL) {
                if (((crypt[i] - 'a') - ltr_key) < NR_ZERO) {
                    crypt[i] = crypt[i] + (SIZE_ALPHA - ltr_key);
                } else {
                    crypt[i] = crypt[i] - ltr_key;
                }
            } else {
                pointer = strchr(LETT, crypt[i]);
                if (pointer != NULL) {
                    if (((crypt[i] - 'A') - ltr_key) < NR_ZERO) {
                        crypt[i] = crypt[i] + (SIZE_ALPHA - ltr_key);
                    } else {
                        crypt[i] = crypt[i] - ltr_key;
                    }
                } else {
                    crypt[i] = crypt[i];
                }
            }
        }
    }
    ++length;
    addres = realloc(addres, length * sizeof(char));
    memcpy(addres, crypt, length);
}

void Vigenere_Code(char *addres) {
    unsigned char crypt[MAX], key[NR_TEN], *pointer = NULL,
        nr[] = "1234567890", lett[] = "abcdefghijklmnopqrstuvwxyz";
    scanf("%s %s", key, crypt);

    int length_key = (int) strlen(key), length_crypt = (int) strlen(crypt);

    for (int i = 0; i < length_crypt; i++) {
        int position = i % length_key, moves = key[position] - 'A',
            nbr_key = (moves % NR_TEN);

        pointer = strchr(nr, crypt[i]);
        if (pointer != NULL) {
            if (((crypt[i] - '0') - nbr_key) < NR_ZERO) {
                crypt[i] = crypt[i] + (NR_TEN - nbr_key);  // case of roation
            } else {
                crypt[i] = crypt[i] - nbr_key;  // normal decipher
            }
        } else {
            pointer = strchr(lett, crypt[i]);
            if (pointer != NULL) {
                if (((crypt[i] - 'a') - moves) < NR_ZERO) {
                    crypt[i] = crypt[i] + (SIZE_ALPHA - moves);
                } else {
                    crypt[i] = crypt[i] - moves;
                }
            } else {
                if (((crypt[i] - 'A') - moves) < NR_ZERO) {
                    crypt[i] = crypt[i] + (SIZE_ALPHA - moves);
                } else {
                    crypt[i] = crypt[i] - moves;
                }
            }
        }
    }
    ++length_crypt;
    addres = realloc(addres, length_crypt * sizeof(char));
    memcpy(addres, crypt, length_crypt);
}

void Addition_Sum(char number1[], int length1, char number2[], int length2,
                    char *address) {
    unsigned char aux[MAX];
    int i = NR_ZERO;
    aux[NR_ZERO] = '0';
    while (length2 > NR_ZERO) {
        length1--;
        length2--;
        if ((((aux[i] - '0') + (number2[length2] - '0'))
                    / NR_TEN) == NR_ONE) {
                aux[i + NR_ONE] = '1';
            } else {
                aux[i + NR_ONE] = '0';
            }
        aux[i] = (((aux[i] - '0') + (number2[length2] - '0'))
                    % NR_TEN) + '0';

        if (length1 >= NR_ZERO) {
            if ((((aux[i] - '0') + (number1[length1] - '0'))
                    / NR_TEN) == NR_ONE) {
                aux[i + NR_ONE] = '1';
            }

            aux[i] = (((aux[i] - '0') + (number1[length1] - '0'))
                    % NR_TEN) + '0';

            if (((number2[length2] - '0') + (number1[length1] - '0'))
                > (NR_TEN - NR_ONE)) {
                aux[i + NR_ONE] = '1';
            }
        }
        aux[i + NR_TWO] = '\0';
        i++;
    }
    i = (int) strlen(aux) - NR_ONE;
    while (aux[i] == '0') {
        aux[i] = '\0';
        i--;
    }

    int length_aux = (int) strlen(aux), end = length_aux - 1;
    for (int i = 0; i < length_aux; i++) {
        address[i] = (char)aux[end];
        end--;
    }
    address[length_aux] = '\0';
}

void Addition_Code(char *addres) {
    int key = 0;
    unsigned char N1[MAX], N2[MAX];
    char nr[] = "1234567890",
        *pointer = NULL;

    scanf("%d", &key);
    scanf("%s %s", N1, N2);
    int nbr_key = (key % NR_TEN);
    int len_n1 = (int) strlen(N1),
        len_n2 = (int) strlen(N2);

    for (int i = 0; i < len_n1; i++) {
        pointer = strchr(nr, N1[i]);
            if (pointer != NULL) {
                if (((N1[i] - '0') - nbr_key) < NR_ZERO) {
                    N1[i] = N1[i] + (NR_TEN - nbr_key);
                } else {
                    N1[i] = N1[i] - nbr_key;
                }
            } else {
                break;
            }
    }
    for (int i = 0; i < len_n2; i++) {
        pointer = strchr(nr, N2[i]);
            if (pointer != NULL) {
                if (((N2[i] - '0') - nbr_key) < NR_ZERO) {
                    N2[i] = N2[i] + (NR_TEN - nbr_key);
                } else {
                    N2[i] = N2[i] - nbr_key;
                }
            } else {
                break;
            }
    }
    int diff = len_n2 - len_n1;
    if (diff < NR_ZERO) {
        Addition_Sum(N2, len_n2, N1, len_n1, addres);
    } else {
        Addition_Sum(N1, len_n1, N2, len_n2, addres);
    }
}

void SolveTask2() {
    char cipher[MAX];
    scanf("%s", cipher);

    char *decrypt = NULL;
    decrypt = calloc(MAX, sizeof(char));
    switch (cipher[NR_ZERO]) {
          case 'c': Caesar_Code(decrypt);
              break;
          case 'v': Vigenere_Code(decrypt);
              break;
          case 'a': Addition_Code(decrypt);
              break;
          default: break;
    }
    printf("%s", decrypt);
    free(decrypt);
}
