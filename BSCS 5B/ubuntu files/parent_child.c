#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();  // Create a child process

    	if (pid < 0) {
        	printf("Fork failed!\n");
        	return 1;
   	 } 
	else if (pid == 0) 
	{
        	printf("Child : Hello From Ubaid Bin Waris\n");
       		sleep(2); 
        	exit(sizeof("Ubaid Bin Waris") - 1);
    	} 
	else 
	{
        	printf("Parent : My name is Ubaid Bin Waris\n");
        	wait(NULL); 
   		printf("Child exit eith status : X \n");
   	}
    return 0;
}
