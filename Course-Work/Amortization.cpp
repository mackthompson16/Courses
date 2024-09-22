 #include<iostream>
#include<sstream>
#include <string>
#include<iomanip>
#include<limits>
//Mack Thompson, JMT0107, 904316394, Filename: project1_Thompson_jmt0107.cpp
//Compiled using vscode and mingw. I used geeksforgeeks as reference
using namespace std;


float input(){

    float userInput = -1;

 while (userInput<0)
    {
    //attempt to convert a string to an integer
    std::string input;
    std::cin >> input;
    std::istringstream iss(input);
    iss>>userInput;
    if(iss.fail()){
        /*I need this method because my program Incorrectly asked for the Rate
        before receiving a valid loan amount(when I input "abc"). This flag
        and the std::cin.ignore method prevents that. I found this on stack overflow.
        */
            cout << "Invalid input. Try a new number: ";
            // Clear the error flag
            iss.clear();
            // Ignore the rest of the line to get fresh input on the next iteration
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            userInput = -1;}
    }

    return userInput;
}


int main()
 {


cout << "Loan Amount: ";
float loan = input();

cout << "Interest Rate (% per year): ";
float rate = input()/12;

cout << "Monthly Payments:";
float payment = input();


int months = 0;
float interest = loan * rate/100;
float principal = loan;
float total= 0;

while(interest>=payment)
{

cout<<"Payment too low. Enter higher payment amount: ";
payment = input();


}

cout<<"*************************************************************************\n";
cout<<"                           Amortization Table\n";
cout<<"*************************************************************************\n";
// I used setw, fixed, and setprecision to format instead of /t because I was having issues with formatting edge cases
cout << left << setw(10) << "Month" << setw(15) << "Balance";
cout << setw(15) << "Payment" << setw(10) << "Rate";
cout << setw(15) << "Interest" << setw(15) << "Principal" << "\n";
cout << setw(10) << months;
cout << "$" << fixed << setprecision(2) << setw(14) << loan;
cout << "N/A\t\t" << "N/A\t" << "  N/A\t\t" << " N/A\n";
while (loan>0){

interest = loan * rate/100;
principal = loan;
loan = loan * (1 + rate/100) - payment;
principal = principal - loan;

total = total + interest;
months++;
if(loan<0)
{
principal = principal + loan;
loan = 0;

}
cout << left << setw(10) << months;
cout << "$" << setw(14) << fixed << setprecision(2) << loan << "$";
cout << setw(14) << payment << setprecision(2) << rate << "%     ";
cout << setw(14) << interest << " $" << setw(14) << principal << "\n";
}
cout<<"*************************************************************************\n\n";
cout<<"It takes "<<months<< " months to pay off the loan.\n";
cout<<"Total interest paid off is: $" <<total<<"\n\n\n";
}
