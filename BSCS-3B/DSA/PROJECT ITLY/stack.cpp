#include "stack.h"
Stack::Stack() {
	t = 0;
}

bool Stack::Empty() {
	return (t == 0);
}

int Stack::full() {
	if (t == 10) {
		cout << "Your car has been towed by the police. " << endl;
		return 1;
	}
	return 0;
}

void Stack::push(int data) {
	if (full())
		return;

	if (data == 0) {
		array[t] = data;
		return;
	}

	if (t == 8) {
		cout << "You have last warining !! otherwise your car will be towed by the police. " << endl;
		array[t] = data;
		t++;
		return;
	}

	else if (t == 9) {
		cout << "Your car has been towed by the police. " << endl;
		array[t] = data;
		t++;
		return;
	}

	array[t] = data;
	t++;
}


int Stack::top() {
	if (Empty()) {
		return array[t];
	}

	else {
		return array[t-1];
	}
}

int Stack::pop() {
	if (Empty())
		cout << "No challan." << endl;
	else {
		return array[t--];
	}
}

int Stack::size() {
	return t;
}