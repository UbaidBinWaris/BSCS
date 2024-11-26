#pragma once
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include "Linkedlist.h"
#include "car.h"

using namespace std;

class filehandling {
private:
    string name;
    string plate;
    bool speed;

public:
    filehandling();
    bool stringToBool(const string& str);
    void loadfile(LinkedList& lk);
    void writetofile(LinkedList& lk);
};