#pragma once
#include <iostream>
using namespace std;
class Stack {
private:
	int array[10];
	int t;
public:
	Stack();
	bool Empty();
	int full();
	void push(int data);
	int top();
	int pop();
	int size();
};