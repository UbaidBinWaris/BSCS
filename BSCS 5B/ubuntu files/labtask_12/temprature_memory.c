#include <stdio.h>
#include <stdlib.h>

int main() {
    float *temperatures = NULL;
    int count = 0;
    float input;

    printf("Enter temperature readings one by one (enter -1 to stop):\n");

    while (1) {
        printf("Reading %d: ", count + 1);
        scanf("%f", &input);

        if (input == -1.0f)
            break;

        // b. Reallocate memory to store one more reading
        float *temp = (float *)realloc(temperatures, (count + 1) * sizeof(float));
        if (temp == NULL) {
            printf("Memory reallocation failed.\n");
            free(temperatures);
            return 1;
        }

        temperatures = temp;
        temperatures[count] = input;
        count++;
    }

    // d. Display all stored readings
    printf("\nStored Temperature Readings:\n");
    for (int i = 0; i < count; i++) {
        printf("Reading %d: %.2f\n", i + 1, temperatures[i]);
    }

    // Free allocated memory
    free(temperatures);

    return 0;
}
