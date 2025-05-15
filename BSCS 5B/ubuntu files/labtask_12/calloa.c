#include <stdio.h>
#include <stdlib.h>

int main() {
    int original_days = 7;
    int updated_days = 4;
    float *expenses;

    // a. Allocate memory for 7 float values using calloc()
    expenses = (float *)calloc(original_days, sizeof(float));
    if (expenses == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

    // b. Accept user input for expenses
    printf("Enter expenses for %d days:\n", original_days);
    for (int i = 0; i < original_days; i++) {
        printf("Day %d: ", i + 1);
        scanf("%f", &expenses[i]);
    }

    // c. Shrink the memory size using realloc() to hold only the first 4 expenses
    float *temp = (float *)realloc(expenses, updated_days * sizeof(float));
    if (temp == NULL) {
        printf("Memory reallocation failed.\n");
        free(expenses); // still free the original block if realloc fails
        return 1;
    }
    expenses = temp;

    // d. Print the updated expenses list
    printf("\nUpdated expenses for %d days:\n", updated_days);
    for (int i = 0; i < updated_days; i++) {
        printf("Day %d: %.2f\n", i + 1, expenses[i]);
    }

    // e. Free the allocated memory
    free(expenses);

    return 0;
}
