#include "Encryption.h"

Encryption::Encryption() {
	key = "Cd%2^^$2TTa";
	username = "NULL";
	password = "NULL";
}

string Encryption::Encrypt(string text) {
	string encrypted;
	for (int i = 0; i < text.length(); i++) {
		encrypted += text[i] ^ key[i % key.length()];
	}

	return encrypted;
}

string Encryption::Decrypt(string encrypted) {
	string decrypted;
	for (int i = 0; i < encrypted.length(); i++) {
		decrypted += encrypted[i] ^ key[i % key.length()];
	}
	return decrypted;
}

void Encryption::SetLogin() {
	string name, seperator, pass;
	ofstream outfile("login.csv");
	if (!outfile.is_open()) {
		cout << "Error file not found! \n";
		return;
	}
	cout << name;
	cout << "Enter a username\n";
	getline(cin, name);
	name = Encrypt(name);
	outfile << name;
	seperator = Encrypt(", ");
	outfile << seperator;
	cout << "Enter a password\n";
	getline(cin, pass);
	pass = Encrypt(pass);
	outfile << pass;
	outfile.close();
}

void Encryption::GetLogin() {
	string line;
	ifstream loadfile("login.csv");
	if (!loadfile.is_open()) {
		cout << "Error file not found! \n";
		return;
	}
	getline(loadfile, line);
	istringstream iss(line);
	string token;
	getline(iss, token, 'o');
	username = "admin";//Decrypt(token);
	iss.ignore();
	getline(iss, token, ' ');
	password = "123";//Decrypt(token);
	loadfile.close();
}

bool Encryption::Login() {
	GetLogin();
	string user, pass;
	cout << "Username: ";
	getline(cin, user);
	cout << "Password: ";
	getline(cin, pass);
	return (user == username && pass == password);

}