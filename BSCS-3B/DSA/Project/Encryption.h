#pragma once
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

class Encryption {
private:
	string key, username, password;

public:
	Encryption();
	string Encrypt(string text);
	string Decrypt(string encrypted);
	void SetLogin();
	void GetLogin();
	bool Login();
};
