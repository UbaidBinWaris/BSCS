#include "filehandling.h"

filehandling::filehandling() {
    name = "null";
    plate = "null";
    speed = false;
}

bool filehandling:: stringToBool(const string& str) {
    return str == "true" || str == "1";
}

void filehandling::loadfile(Tree& lk) {
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
        add_challan(challan , &c);
        lk.AddNodefromfile(c);

    }
    file.close();
}
void filehandling::add_challan(int chalan, car* c)
{
    for (int i = 0; i < chalan; i++)
    {
        c->challanNumber.push(1);
    }
}

void filehandling::writetofile(Tree& lk) {
    ofstream outfile("data.csv",ios::trunc);
    if (!outfile.is_open()) {
        cerr << "Failed to read from file." << endl;
        return;
    }
    Node* ROOT = lk.root;
    load(ROOT , outfile );

    outfile.close();
}
void filehandling::load(Node* ROOT , ofstream& outfile)
{
    if (ROOT != nullptr)
    {
        load(ROOT->left , outfile);
        outfile << ROOT->record.name << ", " << ROOT->record.numplate << ", " << ROOT->record.overspeeding << ", " << ROOT->record.challanNumber.top() << endl;
        load(ROOT->right , outfile);
    }
}
