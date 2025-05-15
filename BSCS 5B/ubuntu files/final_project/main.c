#include <stdio.h>
#include <stdlib.h>

#define TIME_QUANTUM 3
#define MAX_PROCESSES 100

typedef struct {
    int pid;
    int arrival_time;
    int burst_time;
    int remaining_time;
    int completion_time;
    int turnaround_time;
    int waiting_time;
} Process;

// Global variables
int n = 0; // number of processes

// Function declarations
int read_processes(const char* filename, Process p[]);
void enqueue_new_arrivals(Process p[], int queue[], int *rear, int visited[], int current_time);
void run_round_robin(Process p[], int queue[], int *front, int *rear, int visited[]);
void calculate_metrics(Process p[], int total_time);
void print_table(Process p[]);
void print_gantt_chart(int gantt_chart[], int chart_len);

int main() {
    Process p[MAX_PROCESSES];
    int queue[MAX_PROCESSES], front = 0, rear = 0;
    int visited[MAX_PROCESSES] = {0};

    // Read processes
    if (!read_processes("processes.txt", p)) return 1;

    // Run scheduler
    run_round_robin(p, queue, &front, &rear, visited);

    return 0;
}

// Reads process data from file
int read_processes(const char* filename, Process p[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        printf("Error opening file: %s\n", filename);
        return 0;
    }

    while (fscanf(file, "%d %d", &p[n].arrival_time, &p[n].burst_time) == 2) {
        p[n].pid = n + 1;
        p[n].remaining_time = p[n].burst_time;
        n++;
    }
    fclose(file);
    return 1;
}

// Enqueues newly arrived processes
void enqueue_new_arrivals(Process p[], int queue[], int *rear, int visited[], int current_time) {
    for (int i = 0; i < n; i++) {
        if (p[i].arrival_time <= current_time && visited[i] == 0) {
            queue[(*rear)++] = i;
            visited[i] = 1;
        }
    }
}

// Executes Round Robin Scheduling
void run_round_robin(Process p[], int queue[], int *front, int *rear, int visited[]) {
    int completed = 0, current_time = 0;
    int gantt_chart[MAX_PROCESSES * 10];
    int chart_index = 0;

    printf("\n--- Gantt Chart ---\n|");

    while (completed != n) {
        enqueue_new_arrivals(p, queue, rear, visited, current_time);

        if (*front == *rear) {
            current_time++;
            continue;
        }

        int idx = queue[(*front)++];

        if (p[idx].remaining_time <= TIME_QUANTUM) {
            current_time += p[idx].remaining_time;
            p[idx].remaining_time = 0;
            p[idx].completion_time = current_time;
            completed++;
        } else {
            current_time += TIME_QUANTUM;
            p[idx].remaining_time -= TIME_QUANTUM;
        }

        gantt_chart[chart_index++] = p[idx].pid;
        printf(" P%d |", p[idx].pid);

        enqueue_new_arrivals(p, queue, rear, visited, current_time);

        if (p[idx].remaining_time > 0) {
            queue[(*rear)++] = idx;
        }
    }

    printf("\n");

    calculate_metrics(p, current_time);
    print_table(p);
    print_gantt_chart(gantt_chart, chart_index);
}

// Calculates turnaround and waiting times
void calculate_metrics(Process p[], int total_time) {
    int total_waiting_time = 0, total_turnaround_time = 0;

    for (int i = 0; i < n; i++) {
        p[i].turnaround_time = p[i].completion_time - p[i].arrival_time;
        p[i].waiting_time = p[i].turnaround_time - p[i].burst_time;
        total_waiting_time += p[i].waiting_time;
        total_turnaround_time += p[i].turnaround_time;
    }

    printf("\nAverage Waiting Time: %.2f\n", (float)total_waiting_time / n);
    printf("Average Turnaround Time: %.2f\n", (float)total_turnaround_time / n);
    printf("Throughput: %.2f processes/unit time\n", (float)n / total_time);
}

// Prints the final process table
void print_table(Process p[]) {
    printf("\n--- Process Table ---\n");
    printf("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting\n");
    for (int i = 0; i < n; i++) {
        printf("P%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
               p[i].pid,
               p[i].arrival_time,
               p[i].burst_time,
               p[i].completion_time,
               p[i].turnaround_time,
               p[i].waiting_time);
    }
}

// Optional: show Gantt Chart as a list
void print_gantt_chart(int gantt_chart[], int chart_len) {
    printf("\nExecution Order: ");
    for (int i = 0; i < chart_len; i++) {
        printf("P%d ", gantt_chart[i]);
    }
    printf("\n");
}
