#pragma once
#include <iostream>
#include "Node.h"
#include <iomanip>
#include <string>
using namespace std;

class Tree {
public:
    int i = 0;
    Node* root = nullptr;
    ~Tree();
    void AddNode();
    Node* Empty();
    Node* SearchNode(string input);
    void DeleteNode(string input);
    void Print();
    void PrintSpecific(string numplate);
    Node* getroot();
    void AddNodefromfile(car car);
    void cheakoverspeeding(Node* temp);
    void add_node_tree(Node* temp);
    void display(Node* ROOT);
    void dispcar(Node* ROOT);
    void speed_cheak(Node* newnode);
    Node* find_parent(Node* temp);
    Node* Del_root(Node* ROOT, string input);
    Node* min_node(Node* temp);
};