#include <iostream>
#include <limits>
#include <fstream>
#include "Linkedlist.h"
#include "filehandling.h"

using namespace std;

char menu()
{
    char inp = 0;
    cout << "Press 1 to add cars in database. " << endl;
    cout << "Press 2 delete for a specific record. " << endl;
    cout << "Press 3 search for a specific record. " << endl;
    cout << "Press 4 check the speedtrap database. " << endl;
    cout << "Press 5 to load speedtrap database. " << endl;
    cout << "Press 0 to Exit and save to database. " << endl << endl;
    cout << "Enter an input: ";
    cin >> inp;
    cin.ignore((numeric_limits<streamsize>::max)(), '\n');
    system("cls");
    return inp;
}


int main()
{
    LinkedList lk;
    filehandling fl;
    string numplate;
    do
    {
        switch (menu())
        {
        case '1':
            lk.AddNode();
            break;

        case '2':
            if (lk.Empty() == nullptr)
            {
                break;
            }
            cout << "Enter car number plate : ";
            getline(cin, numplate);
            lk.DeleteNode(numplate);
            break;

        case '3':
            if (lk.Empty() == nullptr)
            {
                break;
            }
            cout << "Enter car number plate : ";
            getline(cin, numplate);
            lk.PrintSpecific(numplate);
            break;

        case '4':
            if (lk.Empty() == nullptr)
            {
                break;
            }
            lk.Print();
            break;

        case '5':
            fl.loadfile(lk);
           
            break;


        case '0':
            cout << "Exiting the program " << endl;
            fl.writetofile(lk);
            return 0;

        default:
            cout << "Wrong input! Please enter a correct input " << endl;
        }

        system("pause");
        system("cls");
    } while (true);
    return 0;
}