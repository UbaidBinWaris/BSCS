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
} Process;

void swap(Process *a, Process *b) {
    Process temp = *a;
    *a = *b;
    *b = temp;
}

// Sort by arrival time
void sort_by_arrival(Process p[], int n) {
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            if (p[j].arrival_time > p[j+1].arrival_time) {
                swap(&p[j], &p[j+1]);
            }
        }
    }
}

int main() {
    Process p[NUM_PROCESSES];
    int total_waiting_time = 0, total_turnaround_time = 0;
    float throughput;
    
    // Random seed
    srand(0);

    // Randomly generate arrival and burst times
    printf("Randomly Generated Processes:\n");
    printf("PID\tArrival Time\tBurst Time\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        p[i].pid = i + 1;
        p[i].arrival_time = rand() % 10;    // Random arrival time between 0 and 9
        p[i].burst_time = (rand() % 5) + 1;  // Random burst time between 1 and 5
        printf("P%d\t%d\t\t%d\n", p[i].pid, p[i].arrival_time, p[i].burst_time);
    }

    // Sort processes according to arrival time
    sort_by_arrival(p, NUM_PROCESSES);

    // Calculate completion, turnaround, and waiting times
    int current_time = 0;
    for (int i = 0; i < NUM_PROCESSES; i++) {
        if (current_time < p[i].arrival_time) {
            current_time = p[i].arrival_time; // CPU remains idle
        }
        p[i].completion_time = current_time + p[i].burst_time;
        p[i].turnaround_time = p[i].completion_time - p[i].arrival_time;
        p[i].waiting_time = p[i].turnaround_time - p[i].burst_time;
        
        current_time = p[i].completion_time;

        total_waiting_time += p[i].waiting_time;
        total_turnaround_time += p[i].turnaround_time;
    }

    throughput = (float)NUM_PROCESSES / (float)current_time;

    printf("\n\n--- FCFS Scheduling Results ---\n");
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

    printf("\nExecution Order: ");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        printf("P%d", p[i].pid);
        if (i != NUM_PROCESSES-1) {
            printf(" -> ");
        }
    }

    printf("\n\nTotal Time: %d", current_time);
    printf("\nThroughput: %.2f processes/unit time", throughput);
    printf("\nAverage Waiting Time: %.2f", (float)total_waiting_time / NUM_PROCESSES);
    printf("\nAverage Turnaround Time: %.2f\n", (float)total_turnaround_time / NUM_PROCESSES);

    return 0;
}
