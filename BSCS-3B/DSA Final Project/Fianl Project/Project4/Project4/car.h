#pragma once
#include <iostream>
#include "stack.h"
#include <string>
using namespace std;

class car
{
public:
    string name;
    string numplate;
    bool overspeeding;
    Stack challanNumber;
    car();
    void get_data();
    void get_numplate();
};