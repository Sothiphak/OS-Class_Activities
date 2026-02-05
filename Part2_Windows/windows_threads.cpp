#include <windows.h>
#include <iostream>
#include <vector>

// Thread function
DWORD WINAPI MyThreadFunction(LPVOID lpParam) {
    int threadNum = *(int*)lpParam;
    std::cout << "Thread " << threadNum << " is running (ID: " << GetCurrentThreadId() << ")." << std::endl;
    
    // Sleep for 30 seconds so you have time to check Process Explorer
    Sleep(30000); 
    return 0;
}

int main() {
    const int NUM_THREADS = 5;
    HANDLE hThreads[NUM_THREADS];
    int threadArgs[NUM_THREADS];

    std::cout << "Creating " << NUM_THREADS << " threads. Open Process Explorer now!" << std::endl;

    for(int i = 0; i < NUM_THREADS; i++) {
        threadArgs[i] = i + 1;
        hThreads[i] = CreateThread(
            NULL,                   // Default security attributes
            0,                      // Default stack size
            MyThreadFunction,       // Thread function
            &threadArgs[i],         // Argument to thread function
            0,                      // Default creation flags
            NULL);                  // Returns the thread identifier
    }

    // Wait for all threads to finish
    WaitForMultipleObjects(NUM_THREADS, hThreads, TRUE, INFINITE);

    std::cout << "All threads finished." << std::endl;

    // Close thread handles
    for(int i = 0; i < NUM_THREADS; i++) {
        CloseHandle(hThreads[i]);
    }

    return 0;
}