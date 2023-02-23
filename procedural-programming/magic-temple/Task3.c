#include "utils.h"
#define NR_TEN 10
#define MAX 10000
#define NR_ONE 1
#define NR_ZERO 0
#define SIZE_ALPHA 26
#define NR_TWO 2


void SolveTask3() {
    scanf("\n");
    char words[MAX];
    char partial[MAX];
  while (fgets(partial, sizeof(partial), stdin) != NULL) {
      int length_partial = (int) strlen(partial);
      for (int i = 0; i < length_partial; i++) {
          if (partial[i] == ',' || partial[i] == '.' || partial[i] == '!'
            || partial[i] == ';') {
              for (int j = i; j < (length_partial - NR_ONE); j++) {
                  partial[j] = partial[j + NR_ONE];
              }
              partial[length_partial-NR_ONE] = '\0';
          }
      }
      strncat(words, partial, strlen(partial));
  }
    for (int i = 0; i < strlen(words); i++) {
      if (words[i] == '\n') {
        words[i] = ' ';
      }
    }
    for (int i = 0; i < strlen(words) - NR_ONE; i++) {
      if (words[i] == ' ' && words[i + NR_ONE] == ' ') {
        for (int j = i; j < (strlen(words) - NR_ONE); j++) {
          words[j] = words[j+1];
        }
        words[strlen(words) - NR_ONE] = '\0';
      }
    }
    int length_words = (int) strlen(words),
        count = NR_ZERO,
        j = 1,
        length_1_word = 0;
    unsigned char two_words[MAX];
    char n_grams[MAX],
        how_many_words[MAX];

    n_grams[0] = '\0';
    two_words[0] = ' ';

    how_many_words[0] = ' ';
    how_many_words[1] = '\0';
    strncat(how_many_words, words, MAX);
    how_many_words[strlen(how_many_words)] = ' ';
    how_many_words[strlen(how_many_words)+1] = '\0';

    for (int i = 0; i < length_words; i++) {
      two_words[j] = words[i];
      if (isspace(two_words[j]) != NR_ZERO) {
          count++;
      }
      if (count == NR_ONE && isspace(two_words[j]) != NR_ZERO) {
        two_words[j+1] = '\0';
        length_1_word = (int) strlen(two_words)-1;
      }
      j++;
      if (count == NR_TWO) {
        two_words[j] = '\0';
        if (strstr(n_grams, two_words) == NULL) {
          char *pointer = NULL, *string = how_many_words;
          int nr_of_app = 0;
          while ((pointer = strstr(string, two_words))) {
            nr_of_app++;
            string = pointer + strlen(two_words)-1;
        }
        if (nr_of_app < NR_TEN) {
          two_words[j] = nr_of_app + '0';
          two_words[j+1] = '\n';
          two_words[j+2] = '\0';
        } else {
          two_words[j] = (nr_of_app / NR_TEN) + '0';
          two_words[j+1] = (nr_of_app % NR_TEN)+ '0';
          two_words[j+2] = '\n';
          two_words[j+3] = '\0';
        }

          strncat(n_grams, two_words, MAX);
        }
        for (int k = 0; k < (length_words - length_1_word); k++) {
          words[k] = words[k + length_1_word];
        }
        words[length_words - length_1_word] = '\0';
        length_words = (int) strlen(words);
        i = -1;
        j = 1;
        count = NR_ZERO;
      }
    }

    int nr_n_grams = 0;
    for (int i = 0; i < strlen(n_grams); i++) {
      if (n_grams[i] == '\n') {
        nr_n_grams++;
      }
    }
        for (int j = 0; j< strlen(n_grams)-1; j++) {
          n_grams[j] = n_grams[j+1];
        }
        n_grams[strlen(n_grams)-1] = '\0';

    for (int i = 0; i < strlen(n_grams)-1; i++) {
      if (isdigit(n_grams[i]) != 0 && n_grams[i+1] == '\n') {
        for (int j = i+2; j < strlen(n_grams)-1; j++) {
          n_grams[j] = n_grams[j+1];
        }
        n_grams[strlen(n_grams)-1] = '\0';
      }
    }
    printf("%d\n", nr_n_grams);
    printf("%s\n", n_grams);
}
