#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main(int, char**){

    long long carbon, hydrogen, oxygen;

    cin >> carbon >> hydrogen >> oxygen;

    long long firstCalc = (2 * oxygen - hydrogen) / 4;
    long long secCalc = (hydrogen + 2 * oxygen) / 4 - carbon;

    long long caronDiox = firstCalc;
    long long water = secCalc;
    long long glucose = (carbon - firstCalc) / 6;

    if (caronDiox + 6 * glucose == carbon
        && 2 * water + 12 * glucose == hydrogen
        && water + 2 * caronDiox + 6 * glucose == oxygen) {
        cout << water << " " << caronDiox << " " << glucose;
    }
    else cout << "Error";
}