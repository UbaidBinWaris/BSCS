#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_FILES 100
#define USED_FILES 30
#define MAX_NAME_LEN 100

int main() {
    char **fileNames;

    // a. Allocate memory for 100 file name entries using calloc()
    fileNames = (char **)calloc(MAX_FILES, sizeof(char *));
    if (fileNames == NULL) {
        printf("Memory allocation for file pointers failed.\n");
        return 1;
    }

    // b. Simulate name input for the first 30 entries
    for (int i = 0; i < USED_FILES; i++) {
        fileNames[i] = (char *)calloc(MAX_NAME_LEN, sizeof(char));
        if (fileNames[i] == NULL) {
            printf("Memory allocation for file name %d failed.\n", i + 1);
            // Clean up already allocated memory
            for (int j = 0; j < i; j++) {
                free(fileNames[j]);
            }
            free(fileNames);
            return 1;
        }

        // Simulate input (you can replace with scanf if real input is desired)
        snprintf(fileNames[i], MAX_NAME_LEN, "file_%d.txt", i + 1);
    }

    // c. Shrink the memory to only hold 30 entries using realloc()
    char **temp = (char **)realloc(fileNames, USED_FILES * sizeof(char *));
    if (temp == NULL) {
        printf("Memory reallocation failed.\n");
        // Still free individual file names
        for (int i = 0; i < USED_FILES; i++) {
            free(fileNames[i]);
        }
        free(fileNames);
        return 1;
    }
    fileNames = temp;

    // d. Display the list and ensure memory is freed properly
    printf("File list after resizing to %d entries:\n", USED_FILES);
    for (int i = 0; i < USED_FILES; i++) {
        printf("%s\n", fileNames[i]);
        free(fileNames[i]);  // Free each file name string
    }

    free(fileNames);  // Free the array of pointers

    return 0;
}
