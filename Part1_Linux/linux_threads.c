#include <pthread.h>
#include <stdio.h>
#include <unistd.h>

// Function that the thread will execute
void* thread_function(void* arg) {
    printf("Thread is running...\n");
    sleep(2); // Simulate work
    printf("Thread execution completed.\n");
    return NULL;
}

int main() {
    pthread_t thread_id;

    printf("Main: Creating thread...\n");
    // Create the thread
    if (pthread_create(&thread_id, NULL, thread_function, NULL) != 0) {
        perror("Failed to create thread");
        return 1;
    }

    printf("Main: Waiting for thread to finish...\n");
    // Wait for the thread to finish (Join)
    pthread_join(thread_id, NULL);

    printf("Main: Thread joined. Exiting.\n");
    return 0;
}