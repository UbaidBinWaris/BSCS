#pragma once
#include <iostream>
#include "car.h"
using namespace std;

class Node
{
public:
    car record;
    Node* next;
    Node* prev;
    Node();
};

