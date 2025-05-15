#include <stdio.h>
#include <pthread.h>

#define SIZE 3

int A[SIZE][SIZE] = {
    {3, 0, 6},
    {3, 0, 6},
    {3, 0, 6}
};

int B[SIZE][SIZE] = {
    {3, 0, 6},
    {3, 0, 6},
    {3, 0, 6}
};

int result_add[SIZE][SIZE];
int result_sub[SIZE][SIZE];
int result_mul[SIZE][SIZE];

void* add_matrices(void* arg) {
    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            result_add[i][j] = A[i][j] + B[i][j];

    pthread_exit(NULL);
}

void* subtract_matrices(void* arg) {
    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            result_sub[i][j] = A[i][j] - B[i][j];

    pthread_exit(NULL);
}

void* multiply_matrices(void* arg) {
    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++) {
            result_mul[i][j] = 0;
            for (int k = 0; k < SIZE; k++)
                result_mul[i][j] += A[i][k] * B[k][j];
        }

    pthread_exit(NULL);
}

void print_matrix(const char* label, int matrix[SIZE][SIZE]) {
    printf("%s:\n", label);
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++)
            printf("%d ", matrix[i][j]);
        printf("\n");
    }
    printf("\n");
}

int main() {
    pthread_t t1, t2, t3;

    pthread_create(&t1, NULL, add_matrices, NULL);
    pthread_create(&t2, NULL, subtract_matrices, NULL);
    pthread_create(&t3, NULL, multiply_matrices, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t3, NULL);

    print_matrix("Matrix A", A);
    print_matrix("Matrix B", B);
    print_matrix("Addition (A + B)", result_add);
    print_matrix("Subtraction (A - B)", result_sub);
    print_matrix("Multiplication (A * B)", result_mul);

    return 0;
}