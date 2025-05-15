#include <stdio.h>
#include <stdlib.h>

#define NUM_PROCESSES 5

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int completion_time;
    int turnaround_time;
    int waiting_time;
    int is_completed;
} Process;

void swap(Process *a, Process *b) {
    Process temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    Process p[NUM_PROCESSES];
    int total_waiting_time = 0, total_turnaround_time = 0;
    float throughput;
    
    srand(0); // fixed seed for reproducibility

    // Randomly generate arrival and burst times
    printf("Randomly Generated Processes:\n");
    printf("PID\tArrival Time\tBurst Time\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        p[i].pid = i + 1;
        p[i].arrival_time = rand() % 10;    // Random arrival time between 0 and 9
        p[i].burst_time = (rand() % 5) + 1;  // Random burst time between 1 and 5
        p[i].is_completed = 0;               // Initially, no process is completed
        printf("P%d\t%d\t\t%d\n", p[i].pid, p[i].arrival_time, p[i].burst_time);
    }

    int completed = 0, current_time = 0;
    printf("\nExecution Order: ");
    
    while (completed != NUM_PROCESSES) {
        int idx = -1;
        int min_burst = 1e9; // Large number

        // Find process with shortest burst among arrived processes
        for (int i = 0; i < NUM_PROCESSES; i++) {
            if (p[i].arrival_time <= current_time && p[i].is_completed == 0) {
                if (p[i].burst_time < min_burst) {
                    min_burst = p[i].burst_time;
                    idx = i;
                }
                else if (p[i].burst_time == min_burst) {
                    // Tie breaker: earlier arrival time
                    if (p[i].arrival_time < p[idx].arrival_time) {
                        idx = i;
                    }
                }
            }
        }

        if (idx != -1) {
            // Execute the selected process
            p[idx].completion_time = current_time + p[idx].burst_time;
            p[idx].turnaround_time = p[idx].completion_time - p[idx].arrival_time;
            p[idx].waiting_time = p[idx].turnaround_time - p[idx].burst_time;
            p[idx].is_completed = 1;

            total_waiting_time += p[idx].waiting_time;
            total_turnaround_time += p[idx].turnaround_time;
            current_time = p[idx].completion_time;
            completed++;

            printf("P%d", p[idx].pid);
            if (completed != NUM_PROCESSES) {
                printf(" -> ");
            }
        }
        else {
            // No process has arrived yet, move time forward
            current_time++;
        }
    }

    throughput = (float)NUM_PROCESSES / (float)current_time;

    printf("\n\n--- Shortest Job Next (SJN/SJF) Scheduling Results ---\n");
    printf("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
            p[i].pid,
            p[i].arrival_time,
            p[i].burst_time,
            p[i].completion_time,
            p[i].turnaround_time,
            p[i].waiting_time
        );
    }

    printf("\nTotal Time: %d", current_time);
    printf("\nThroughput: %.2f processes/unit time", throughput);
    printf("\nAverage Waiting Time: %.2f", (float)total_waiting_time / NUM_PROCESSES);
    printf("\nAverage Turnaround Time: %.2f\n", (float)total_turnaround_time / NUM_PROCESSES);

    return 0;
}
