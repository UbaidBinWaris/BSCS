#include <iostream>
#include <limits>
#include <fstream>
#include "Linkedlist.h"
#include "filehandling.h"
#include "Encryption.h"

using namespace std;

void AdminMenu(Encryption& en , filehandling& fl, LinkedList& lk)
{
	do
	{
		char inp = 0;
        string numplate;
		cout << "Press 1 to add cars in database. " << endl;
		cout << "Press 2 delete for a specific record. " << endl;
		cout << "Press 3 search for a specific record. " << endl;
		cout << "Press 4 check the speedtrap database. " << endl;
		cout << "Press 5 to change admin login details. " << endl;
		cout << "Press 0 to return to main menu. " << endl << endl;
		cout << "Enter an input: ";
		cin >> inp;
		//cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		system("cls");
		switch (inp)
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
            cout << "Match found and deleted. " << endl;
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
			en.SetLogin();
			break;

		case '0':
			cout << "Returning to main menu\n";
			return;

		default:
			cout << "Wrong input! Please enter a correct input " << endl;
		}

		system("pause");
		system("cls");
	} while (true);
}

void GuestMenu(filehandling& fl, LinkedList& lk)
{
	do
	{
		system("cls");
		char inp = 0;
        string numplate;
		cout << "Press 1 delete for a specific record. " << endl;
		cout << "Press 2 search for a specific record. " << endl;
		cout << "Press 0 to return to main menu " << endl << endl;
		cout << "Enter an input: ";
		cin >> inp;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		system("cls");
		switch (inp)
		{
        case '1':
            if (lk.Empty() == nullptr)
            {
                break;
            }
            cout << "Enter car number plate : ";
            getline(cin, numplate);
            lk.DeleteNode(numplate);
            cout << "Match found and deleted. " << endl;
            break;

        case '2':
            if (lk.Empty() == nullptr)
            {
                break;
            }
            cout << "Enter car number plate : ";
            getline(cin, numplate);
            lk.PrintSpecific(numplate);
            break;


		case '0':
			cout << "Returning to main menu\n";
			return;

		default:
			cout << "Wrong input! Please enter a correct input " << endl;
		}

		system("pause");
		system("cls");
	} while (true);
}

int main()
{
    LinkedList lk;
    filehandling fl;
	Encryption en;
	do
	{
		fl.loadfile(lk);
		char inp = 0;
		cout << "Press 1 to login as admin. " << endl;
		cout << "Press 2 to login as guest. " << endl;
		cout << "Press 0 to Exit " << endl << endl;
		cout << "Enter an input: ";
		cin >> inp;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		system("cls");
		switch (inp)
		{
		case '1':
			if (en.Login()) {
				system("cls");
				AdminMenu(en, fl, lk);
			}
			else
				cout << "Invalid login details.\n";
			break;

		case '2':
			GuestMenu(fl, lk);
			break;


		case '0':
			cout << "Exiting\n";
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