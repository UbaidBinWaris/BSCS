#pragma once
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include "Tree.h"
#include "car.h"

using namespace std;

class filehandling {
private:
    string name, plate;
    bool speed;

public:
    filehandling();
    bool stringToBool(const string& str);
    void loadfile(Tree& lk);
    void writetofile(Tree& lk);
    void load(Node* ROOT , ofstream& outfile);
    void add_challan(int chalan, car* c);
};