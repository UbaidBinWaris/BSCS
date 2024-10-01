#pragma once
#include <iostream>
#include "car.h"
using namespace std;

class Node
{
public:
    car record;
    Node* right;
    Node* left;
    Node();
};