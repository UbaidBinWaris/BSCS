#include <stdio.h>
#include <limits.h>

#define MAX_FRAMES 10
#define MAX_PAGES 100

// Part 1: Basic Paging Technique
void basicPaging() {
    int n_pages, n_frames;
    printf("\n--- Basic Paging Simulation ---\n");
    printf("Enter number of logical pages: ");
    scanf("%d", &n_pages);

    printf("Enter number of physical frames: ");
    scanf("%d", &n_frames);

    int page_to_frame[n_pages];
    int frame_used[n_frames];

    for(int i = 0; i < n_pages; ++i)
        page_to_frame[i] = -1;

    for(int i = 0; i < n_frames; ++i)
        frame_used[i] = 0;

    int frame_index = 0;
    for(int i = 0; i < n_pages; ++i) {
        if(frame_index < n_frames) {
            page_to_frame[i] = frame_index;
            frame_used[frame_index] = 1;
            frame_index++;
        } else {
            page_to_frame[i] = -1; // Cannot be loaded
        }
    }

    printf("\nPage\tFrame\n");
    for(int i = 0; i < n_pages; ++i) {
        if(page_to_frame[i] != -1)
            printf("%d\t%d\n", i, page_to_frame[i]);
        else
            printf("%d\tNot Loaded (No Frame)\n", i);
    }
}

// Part 2: LFU Page Replacement
void lfuReplacement() {
    int pages[MAX_PAGES], frequency[MAX_PAGES] = {0};
    int frames[MAX_FRAMES], freq_in_frame[MAX_FRAMES];
    int n_pages, n_frames, faults = 0;

    printf("\n--- LFU Page Replacement ---\n");
    printf("Enter number of pages in reference string: ");
    scanf("%d", &n_pages);

    printf("Enter reference string (space-separated): ");
    for(int i = 0; i < n_pages; ++i)
        scanf("%d", &pages[i]);

    printf("Enter number of frames: ");
    scanf("%d", &n_frames);

    for(int i = 0; i < n_frames; ++i) {
        frames[i] = -1;
        freq_in_frame[i] = 0;
    }

    printf("\nPage\tFrames\t\tPage Fault\n");

    for(int i = 0; i < n_pages; ++i) {
        int current = pages[i];
        frequency[current]++;
        int found = 0;

        // Check if page is already in frame
        for(int j = 0; j < n_frames; ++j) {
            if(frames[j] == current) {
                freq_in_frame[j] = frequency[current];
                found = 1;
                break;
            }
        }

        // If not in frame
        if(!found) {
            faults++;
            int min_freq = INT_MAX;
            int replace_index = -1;

            // Find empty frame or least frequently used
            for(int j = 0; j < n_frames; ++j) {
                if(frames[j] == -1) {
                    replace_index = j;
                    break;
                }
                if(freq_in_frame[j] < min_freq) {
                    min_freq = freq_in_frame[j];
                    replace_index = j;
                }
            }

            frames[replace_index] = current;
            freq_in_frame[replace_index] = frequency[current];
        }

        // Output state
        printf("%d\t", current);
        for(int j = 0; j < n_frames; ++j) {
            if(frames[j] != -1)
                printf("%d ", frames[j]);
            else
                printf("- ");
        }

        if(!found)
            printf("\tYes\n");
        else
            printf("\tNo\n");
    }

    printf("\nTotal Page Faults: %d\n", faults);
}

// Main
int main() {
    basicPaging();
    lfuReplacement();
    return 0;
}
