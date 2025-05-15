#include <stdio.h>
#include <stdlib.h>

int main() {
    int count = 6;
    float *prices;
    float sum = 0.0, average;

    // a. Dynamically allocate memory for 6 float product prices
    prices = (float *)malloc(count * sizeof(float));
    if (prices == NULL) {
        printf("Memory allocation failed.\n");
        return 1;
    }

    // b. Accept user input and calculate the average price
    printf("Enter prices of %d products:\n", count);
    for (int i = 0; i < count; i++) {
        printf("Price %d: ", i + 1);
        scanf("%f", &prices[i]);
        sum += prices[i];
    }

    average = sum / count;
    printf("\nAverage Price: %.2f\n", average);

    // c. Free the allocated memory
    free(prices);

    return 0;
}
