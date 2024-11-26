#include "Tree.h"

Tree::~Tree() {
    
}

void Tree::AddNode() {
    Node* newnode = new Node;
    newnode->record.get_numplate();
    if (root == nullptr)
    {
        newnode->record.get_data();
        cheakoverspeeding(newnode);
        speed_cheak(newnode);
        add_node_tree(newnode);
        return;
    }
    if (SearchNode(newnode->record.numplate) == nullptr)
    {
        newnode->record.get_data();

        if (newnode->record.challanNumber.top() == 10) {
            cout << "Your car has been towed by the police. " << endl;
            return;
        }
        cheakoverspeeding(newnode);
        speed_cheak(newnode);
        add_node_tree(newnode);
    }
    else
    {
        newnode = SearchNode(newnode->record.numplate);
        if (newnode->record.challanNumber.top() == 10) {
            cout << "Your car has been towed by the police. " << endl;
            return;
        }
        cheakoverspeeding(newnode);
        speed_cheak(newnode);
    }

}
    

void Tree::AddNodefromfile(car car) {
    if (SearchNode(car.numplate) != nullptr)
    {
        return;
    }
    Node* newnode = new Node;
    newnode->record = car; 

    add_node_tree(newnode);
}

Node* Tree::Empty() {
    if (root == nullptr)
    {
        cout << "Database is empty. " << endl;
        return nullptr;
    }
    return root;
}

Node* Tree::SearchNode(string input) {
    Node* temp = root;
    while (temp != nullptr)
    {
        if (temp->record.numplate == input)
        {
            return temp;
        }
        else if (temp->record.numplate < input)
        {
            temp = temp->right;
        }
        else if (temp->record.numplate > input)
        {
            temp = temp->left;
        }
    }
    return nullptr;
}

void Tree::DeleteNode(string input) {
    Node* ROOT = root;

    root = Del_root(ROOT, input);
    cout << "Data Removed Secussfully > > > \n";

}
Node* Tree::Del_root(Node* ROOT, string input)
{
    if (ROOT == nullptr)
    {
        return ROOT;
    }

    if (ROOT->record.numplate < input)
    {
        ROOT->right = Del_root(ROOT->right, input);
    }
    else if(ROOT->record.numplate > input)
    {
        ROOT->left = Del_root(ROOT->left, input);
    }
    else
    {
        if (ROOT->left == nullptr)
        {
            Node* temp = ROOT->right;
            delete ROOT;
            return temp;
        }
        else if (ROOT->right == nullptr)
        {
            Node* temp = ROOT->left;
            delete ROOT;
            return temp;
        }

        Node* temp = min_node(ROOT->right);
        ROOT->record = temp->record;
        ROOT->right = Del_root(ROOT->right, temp->record.numplate);
    }
    return ROOT;
}
Node* Tree::min_node(Node* temp)
{
    Node* cur = temp;
    while (cur && cur->left != nullptr)
    {
        cur = cur->left;
    }
    return cur;
}

void Tree::Print() {
    cout << "-----------Speed Trap Database-----------" << endl;
    display(root);
    i = 0;
}

void Tree::PrintSpecific(string numplate) {
    Node* temp;
    temp = SearchNode(numplate);
    if (temp == nullptr) {
        cout << "No match found in database. " << endl;
        return;
    }
    cout << "Match found in database. " << endl
         << endl;
    cout << "-----------Speed Trap Database-----------" << endl;
    cout << "Name: " << temp->record.name << endl
        << "Number plate: " << temp->record.numplate << endl
        << "Overspeeding: ";
    if (temp->record.overspeeding == true)
        cout << "Yes" << endl;
    else
        cout << "No" << endl;
    cout << "Number of Challans = ";
    cout <<  temp->record.challanNumber.top();
    cout << "\n";
}

Node* Tree::getroot() {
    return root;
}

void Tree::cheakoverspeeding(Node* temp)
{
    cout << "Enter 1 for over speeding or 0 for not overspeeding : ";
    cin >> temp->record.overspeeding;
    while (cin.fail()) {
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        cout << "Please enter 0 or 1 : ";
        cin >> temp->record.overspeeding;
        cout << endl;
    }
}

void Tree::add_node_tree(Node* temp)
{
    if (root == nullptr)
    {
        root = temp;
        return;
    }
    Node* curr = root;
    Node* parent = nullptr;
    while (curr != nullptr)
    {
        parent = curr;
        if (curr->record.numplate < temp->record.numplate)
        {
            curr = curr->right;
        }
        else
        {
            curr = curr->left;
        }
    }
    if (temp->record.numplate > parent->record.numplate)
    {
        parent->right = temp;
    }
    else
    {
        parent->left = temp;
    }
}

void Tree::speed_cheak(Node* newnode)
{
    int temp = 0;
    temp = newnode->record.challanNumber.top();
    if (newnode->record.overspeeding == 1)
    {
        temp++;
        newnode->record.challanNumber.push(1);
        cout << "Challan no." << temp << " generated " << endl;
    }
    else if (newnode->record.overspeeding == 0) {
        cout << "You did not overspeeded \n";
    }
}

void Tree::display(Node* ROOT)
{
    if (ROOT != nullptr)
    {
        display(ROOT->left);
        dispcar(ROOT);
        display(ROOT->right);
    }
}

void Tree::dispcar(Node* ROOT)
{
    cout << "\n" << setw(23) << "Car no." << i+1 << endl
        << "Name: " << ROOT->record.name << endl
        << "Number plate: " << ROOT->record.numplate << endl
        << "Overspeeding: ";
    if (ROOT->record.overspeeding == true)
        cout << "Yes" << endl;
    else
        cout << "No" << endl;
    cout << "Number of Challans = ";
    cout << ROOT->record.challanNumber.top();
    cout << "\n";
    i++;
}