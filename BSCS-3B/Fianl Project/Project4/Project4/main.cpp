#include <iostream>
#include <limits>
#include <fstream>
#include "Tree.h"
#include "filehandling.h"
#include "Encryption.h"

using namespace std;

void AdminMenu(Encryption& en , Tree& lk)
{
	do
	{
		char inp = 0;
        string numplate;
		cout << "Press 1 Add cars in database. " << endl;
		cout << "Press 2 Delete for a specific record. " << endl;
		cout << "Press 3 Search for a specific record. " << endl;
		cout << "Press 4 Speedtrap database. " << endl;
		cout << "Press 5 Change admin login details. " << endl;
		cout << "Press 0 Return to main menu. " << endl << endl;
		cout << "Please enter your choice : ";
		cin >> inp;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
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
			if (lk.SearchNode(numplate) == nullptr)
			{
				cout << "No match found in database." << endl;
				break;
			}
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

void GuestMenu(Tree& lk)
{
	do
	{
		system("cls");
		char inp = 0;
		Node* temp;
        string numplate;
		cout << "Press 1 Add cars in database. " << endl;
		cout << "Press 2 Search for a specific record. " << endl;
		cout << "Press 3 Pay challan of car. " << endl;
		cout << "Press 0 Return to main menu " << endl << endl;
		cout << "Please enter your choice : ";
		cin >> inp;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
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
            lk.PrintSpecific(numplate);
            break;
		case'3':
			cout << "Enter car number plate : ";
			getline(cin, numplate);
			temp = lk.SearchNode(numplate);
			if (temp == nullptr)
			{
				cout << "No match foud in database" << endl;
				return;
			}
			if (temp->record.challanNumber.top() == 0)
			{
				cout << "No pabale Challan ! " << endl;
				return;
			}
			else if (temp->record.challanNumber.top() == 10)
			{
				cout << "you car is towed by the police only unloack the car ! ! ! " << endl;
				return;
			}
			else
			{
				temp->record.challanNumber.pop();
				cout << "Now your new challan is : " << temp->record.challanNumber.top() << endl;
			}
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
    Tree lk;
    filehandling fl;
	Encryption en;
	fl.loadfile(lk);
	do
	{
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
				AdminMenu(en, lk);
			}
			else
				cout << "Invalid login details.\n";
			break;

		case '2':
			GuestMenu(lk);
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