#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MIN_PROCESSES 15
#define MAX_PROCESSES 20

int main() {
    FILE *file = fopen("processes.txt", "w");
    if (!file) {
        printf("Error creating file!\n");
        return 1;
    }

    srand(time(NULL)); // Seed with current time

    // Generate between 15 to 20 processes randomly
    int num_processes = MIN_PROCESSES + rand() % (MAX_PROCESSES - MIN_PROCESSES + 1);

    for (int i = 0; i < num_processes; i++) {
        int arrival_time = rand() % 21;      // 0 to 20
        int burst_time = (rand() % 10) + 1;  // 1 to 10
        fprintf(file, "%d %d\n", arrival_time, burst_time);
    }

    fclose(file);
    printf("%d processes generated and written to 'processes.txt'\n", num_processes);
    return 0;
}
