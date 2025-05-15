#include <stdio.h>
#include <stdlib.h>

#define NUM_PROCESSES 5

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int priority;
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
    
    srand(1); // fixed seed for reproducibility

    // Randomly generate arrival time, burst time, and priority
    printf("Randomly Generated Processes:\n");
    printf("PID\tArrival Time\tBurst Time\tPriority\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        p[i].pid = i + 1;
        p[i].arrival_time = rand() % 10;      // Arrival time between 0 and 9
        p[i].burst_time = (rand() % 5) + 1;    // Burst time between 1 and 5
        p[i].priority = (rand() % 5) + 1;      // Priority between 1 and 5 (1 highest)
        p[i].is_completed = 0;                 // Initially not completed
        printf("P%d\t%d\t\t%d\t\t%d\n", p[i].pid, p[i].arrival_time, p[i].burst_time, p[i].priority);
    }

    int completed = 0, current_time = 0;
    printf("\nExecution Order: ");
    
    while (completed != NUM_PROCESSES) {
        int idx = -1;
        int highest_priority = 1e9; // Smaller number means higher priority

        // Find process with highest priority among arrived processes
        for (int i = 0; i < NUM_PROCESSES; i++) {
            if (p[i].arrival_time <= current_time && p[i].is_completed == 0) {
                if (p[i].priority < highest_priority) {
                    highest_priority = p[i].priority;
                    idx = i;
                }
                else if (p[i].priority == highest_priority) {
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

    printf("\n\n--- Priority Scheduling (Non-Preemptive) Results ---\n");
    printf("PID\tArrival\tBurst\tPriority\tCompletion\tTurnaround\tWaiting\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\t\t%d\n",
            p[i].pid,
            p[i].arrival_time,
            p[i].burst_time,
            p[i].priority,
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
