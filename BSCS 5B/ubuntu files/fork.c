#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>

int main()
{
	printf("Ubaid\n");
	int pid = fork();

	while(pid < 10)
	{
		printf("Bin Waris\n");
		pid ++;
	}

	return 0;
}
