#include <stdio.h>
#include <stdlib.h>

#define NUM_PROCESSES 5
#define TIME_QUANTUM 3

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int remaining_time;
    int completion_time;
    int turnaround_time;
    int waiting_time;
} Process;

int main() {
    Process p[NUM_PROCESSES];
    int total_waiting_time = 0, total_turnaround_time = 0;
    float throughput;
    
    srand(2); // fixed seed for reproducibility

    // Randomly generate arrival time and burst time
    printf("Randomly Generated Processes:\n");
    printf("PID\tArrival Time\tBurst Time\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        p[i].pid = i + 1;
        p[i].arrival_time = rand() % 10;      // Arrival time between 0 and 9
        p[i].burst_time = (rand() % 7) + 1;    // Burst time between 1 and 7
        p[i].remaining_time = p[i].burst_time;
        printf("P%d\t%d\t\t%d\n", p[i].pid, p[i].arrival_time, p[i].burst_time);
    }

    int completed = 0, current_time = 0;
    int queue[NUM_PROCESSES];
    int front = 0, rear = 0;
    int visited[NUM_PROCESSES] = {0};

    printf("\nExecution Order: ");
    
    while (completed != NUM_PROCESSES) {
        // Enqueue newly arrived processes
        for (int i = 0; i < NUM_PROCESSES; i++) {
            if (p[i].arrival_time <= current_time && visited[i] == 0) {
                queue[rear++] = i;
                visited[i] = 1;
            }
        }

        if (front == rear) {
            // CPU idle
            current_time++;
            continue;
        }

        int idx = queue[front++];

        if (p[idx].remaining_time <= TIME_QUANTUM) {
            current_time += p[idx].remaining_time;
            p[idx].remaining_time = 0;
            p[idx].completion_time = current_time;
            completed++;

            printf("P%d ", p[idx].pid);

            // Enqueue new processes during execution
            for (int i = 0; i < NUM_PROCESSES; i++) {
                if (p[i].arrival_time <= current_time && visited[i] == 0) {
                    queue[rear++] = i;
                    visited[i] = 1;
                }
            }
        }
        else {
            current_time += TIME_QUANTUM;
            p[idx].remaining_time -= TIME_QUANTUM;
            printf("P%d ", p[idx].pid);

            // Enqueue new processes during execution
            for (int i = 0; i < NUM_PROCESSES; i++) {
                if (p[i].arrival_time <= current_time && visited[i] == 0) {
                    queue[rear++] = i;
                    visited[i] = 1;
                }
            }

            // Put current process back in queue
            queue[rear++] = idx;
        }
    }

    printf("\n\n--- Round Robin Scheduling (Time Quantum = %d) Results ---\n", TIME_QUANTUM);
    printf("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\n");
    for (int i = 0; i < NUM_PROCESSES; i++) {
        p[i].turnaround_time = p[i].completion_time - p[i].arrival_time;
        p[i].waiting_time = p[i].turnaround_time - p[i].burst_time;

        total_turnaround_time += p[i].turnaround_time;
        total_waiting_time += p[i].waiting_time;

        printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
            p[i].pid,
            p[i].arrival_time,
            p[i].burst_time,
            p[i].completion_time,
            p[i].turnaround_time,
            p[i].waiting_time
        );
    }

    throughput = (float)NUM_PROCESSES / (float)current_time;

    printf("\nTotal Time: %d", current_time);
    printf("\nThroughput: %.2f processes/unit time", throughput);
    printf("\nAverage Waiting Time: %.2f", (float)total_waiting_time / NUM_PROCESSES);
    printf("\nAverage Turnaround Time: %.2f\n", (float)total_turnaround_time / NUM_PROCESSES);

    return 0;
}
