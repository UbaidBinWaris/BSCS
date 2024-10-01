#include "LinkedList.h"

LinkedList::~LinkedList() {
    Node* cur = head;
    Node* temp;
    while (cur != nullptr)
    {
        temp = cur;
        cur = cur->next;
        delete temp;
    }
}

void LinkedList::AddNode() {
    Node* newnode = new Node;
    newnode->record.get_numplate();
    if (head != nullptr)
    {
        if (SearchNode(newnode->record.numplate) != nullptr)
        {
            PrintSpecific(newnode->record.numplate);
            int temp = 0;
            temp = cur->record.challanNumber.top();
            if (temp == 10) {
                cout << "Your car has been towed by the police. " << endl;
                return;
            }
            cout << "Were you overspeeding? ";
            cin >> cur->record.overspeeding;
            while (cin.fail()) {
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
                cout << "Please enter 0 or 1 : ";
                cin >> cur->record.overspeeding;
                cout << endl;
            }
            if (cur->record.overspeeding == 1)
            {
                temp++;
                cur->record.challanNumber.push(temp);
                cout << "Challan no." << temp << " generated " << endl;
                return;
            }
            else if (cur->record.overspeeding == 0) {
                cout << "You did not overspeeded \n";
                return;
            }
        }
    }
    newnode->record.get_data();
    cout << "Enter 1 for over speeding or 0 for not overspeeding : ";
    cin >> newnode->record.overspeeding;
    while (cin.fail()) {
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        cout << "Please enter 0 or 1 : ";
        cin >> newnode->record.overspeeding;
        cout << endl;
    }
 
    if (newnode->record.overspeeding == 1) {
        newnode->record.challanNumber.push(1);
        cout << "Challan no." << newnode->record.challanNumber.top() << " generated " << endl;
    }

    else {
        cout << "You did not overspeeded \n";
        newnode->record.challanNumber.push(0);
    }

    if (head == nullptr) {
        head = newnode;
        return;
    }
    Node* ptr = head;
    while (ptr->next != nullptr) {
        ptr = ptr->next;
    }
    ptr->next = newnode;
    newnode->prev = ptr;
}

void LinkedList::AddNodefromfile(car car) {
    Node* newnode = new Node;
    int challan = 0;
    int i = 0;
    newnode->record.name = car.name;
    newnode->record.numplate = car.numplate;
    newnode->record.overspeeding = car.overspeeding;
    challan = car.challanNumber.top();
    if (challan == 0) {
        newnode->record.challanNumber.push(0);
    }
    else if (challan == 1) {
        newnode->record.challanNumber.push(1);
    }
    while (i != challan) {
        newnode->record.challanNumber.push(i+1);
        i++;
    }
    if (head == nullptr) {
        head = newnode;
        return;
    }
    Node* ptr = head;
    while (ptr->next != nullptr) {
        ptr = ptr->next;
    }
    ptr->next = newnode;
    newnode->prev = ptr;
    newnode->next = nullptr;
    return;
}

Node* LinkedList::Empty() {
    if (head == nullptr)
    {
        cout << "Database is empty. " << endl;
        return nullptr;
    }
    return head;
}

Node* LinkedList::SearchNode(string input) {
    cur = head;
    while (cur->record.numplate != input)
    {
        if (cur->next == nullptr)
        {
            cur = nullptr;
            return nullptr;
        }
        cur = cur->next;
    }
    return cur;
}

void LinkedList::DeleteNode(string input) {
    if (SearchNode(input) == nullptr) {
        cout << "No match found in database." << endl;
        return;
    }
    Node* temp;
    cout << "Match found and deleted. " << endl;
    if (cur->next == nullptr)
    {
        if (cur->prev == nullptr)
        {
            delete cur;
            head = nullptr;
            return;
        }
        temp = cur->prev;
        temp->next = nullptr;
        delete cur;
        return;
    }

    if (cur->prev == nullptr)
    {
        head = cur->next;
        delete cur;
        return;
    }
    temp = cur->prev;
    temp->next = cur->next;
    delete cur;
    return;
}

void LinkedList::Print() {
    Node* ptr = head;
    string stats;
    int i = 1;
    cout << "-----------Speed Trap Database-----------" << endl;
    while (ptr != nullptr)
    {
        cout << "\n" << setw(23) << "Car no." << i << endl
            << "Name: " << ptr->record.name << endl
            << "Number plate: " << ptr->record.numplate << endl
            << "Overspeeding: ";
        if (ptr->record.overspeeding == true)
            cout << "Yes" << endl;
        else
            cout << "No" << endl;
        cout << "Number of Challans = ";
        cout << ptr->record.challanNumber.top();
        ptr = ptr->next;
        i++;
        cout << "\n";
    }
}

void LinkedList::PrintSpecific(string numplate) {
    Node* temp;
    temp = SearchNode(numplate);
    if (temp == nullptr) {
        cout << "No match found in database. " << endl;
        return;
    }
    else
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

Node* LinkedList::gethead() {
    return head;
}
