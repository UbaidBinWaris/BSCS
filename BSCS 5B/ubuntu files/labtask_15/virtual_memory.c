#include <stdio.h>

#define MAX_FRAMES 10
#define MAX_PAGES 100

int findLRU(int time[], int n) {
    int i, minimum = time[0], pos = 0;

    for(i = 1; i < n; ++i) {
        if(time[i] < minimum) {
            minimum = time[i];
            pos = i;
        }
    }
    return pos;
}

int main() {
    int frames[MAX_FRAMES], time[MAX_FRAMES];
    int pages[MAX_PAGES], n_pages, n_frames, i, j, k, pos, faults = 0, counter = 0, flag1, flag2;

    printf("Enter number of pages: ");
    scanf("%d", &n_pages);

    printf("Enter the reference string (space-separated): ");
    for(i = 0; i < n_pages; ++i) {
        scanf("%d", &pages[i]);
    }

    printf("Enter number of frames: ");
    scanf("%d", &n_frames);

    for(i = 0; i < n_frames; ++i) {
        frames[i] = -1;
    }

    printf("\nPage\tFrames\t\tPage Fault\n");

    for(i = 0; i < n_pages; ++i) {
        flag1 = flag2 = 0;

        // Check if page is already in frame
        for(j = 0; j < n_frames; ++j) {
            if(frames[j] == pages[i]) {
                counter++;
                time[j] = counter; // Update last used time
                flag1 = flag2 = 1;
                break;
            }
        }

        // Empty frame available
        if(flag1 == 0) {
            for(j = 0; j < n_frames; ++j) {
                if(frames[j] == -1) {
                    counter++;
                    faults++;
                    frames[j] = pages[i];
                    time[j] = counter;
                    flag2 = 1;
                    break;
                }
            }
        }

        // No empty frame: apply LRU
        if(flag2 == 0) {
            pos = findLRU(time, n_frames);
            counter++;
            faults++;
            frames[pos] = pages[i];
            time[pos] = counter;
        }

        // Display state of frames
        printf("%d\t", pages[i]);
        for(j = 0; j < n_frames; ++j) {
            if(frames[j] != -1)
                printf("%d ", frames[j]);
            else
                printf("- ");
        }

        if(flag1 == 0)
            printf("\tYes\n");
        else
            printf("\tNo\n");
    }

    printf("\nTotal Page Faults: %d\n", faults);

    return 0;
}
