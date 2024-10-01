#include "car.h"

car::car() {
    name = "NULL";
    numplate = "NULL";
    overspeeding = false;
}

void car::get_numplate() {
    cout << "Enter the car's number plate: ";
    getline(cin, numplate);
    
}

void car::get_data() {
    cout << "Enter the car's name: ";
    getline(cin, name);

}
