#include "filehandling.h"

filehandling::filehandling() {
    name = "null";
    plate = "null";
    speed = false;
}

bool filehandling:: stringToBool(const string& str) {
    return str == "true" || str == "1";
}

void filehandling::loadfile(LinkedList& lk) {
    ifstream file("data.csv");
    if (!file.is_open()) {
        cerr << "Failed to open the file." << endl;
        return;
    }

    string line;
    while (getline(file, line)) {
        car c;
        int challan;
        istringstream iss(line);
        string token;
        getline(iss, token, ',');
        c.name = token;
        iss.ignore();
        getline(iss, token, ',');
        c.numplate = token;
        iss.ignore();
        getline(iss, token, ',');
        if (token == "true" || token == "false" || token == "1" || token == "0") {
            bool value = stringToBool(token);
            c.overspeeding = value;
        }
        iss.ignore();
        iss >> challan; 
        c.challanNumber.push(challan);
        lk.AddNodefromfile(c);

    }
    file.close();
}

void filehandling::writetofile(LinkedList& lk) {
    ofstream outfile("data.csv");
    if (!outfile.is_open()) {
        cerr << "Failed to read from file." << endl;
        return;
    }
    Node* temp = lk.gethead();
    while (temp != nullptr) {
        outfile << temp->record.name << ", " << temp->record.numplate << ", " << temp->record.overspeeding << ", " << temp->record.challanNumber.top() << endl;
        temp = temp->next;
    }
    outfile.close();
}