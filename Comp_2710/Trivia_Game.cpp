
#include <iostream>
#include <string>
#include <assert.h>
//Mack Thompson, JMT0107, 904316394, Filename: project4_Thompson_jmt0107.cpp

using namespace std;
int total_points;

class TriviaNode{
    public:

    string question;
    string answer;

    TriviaNode* next;

    int points;


    TriviaNode(string q, int p, string a) :  question(q), points(p), answer(a){}

    int ask(bool hard_input, string ans)
    {

cout<<"\n"<< question <<"\n";
string input;

if(!hard_input){
cout << "\nAnswer: ";
cin >> input;
} else input = ans; cout<<"Answer: "<< input;


if(input!=answer)
    {
    cout<<"\nYour answer is wrong. The correct answer is "<< answer<<"\n";
    return 0;
    }
else
{
cout<<"\nYour answer is correct! You recieve " << points << " points.\n";
total_points+=points;
return 1;
}

    }




};

class TriviaList {

public:

    TriviaNode* top;
    TriviaNode* bottom;
    int size;
        TriviaList() : size(0){};

    void addQuestion(string q, int p, string a) {

        TriviaNode* newNode = new TriviaNode(q,p,a);
        size++;
        if (!top)
        {
            top = newNode;
            bottom = newNode;
        }

        else {

            top->next = newNode;
            top = newNode;
        }
    }

    TriviaNode* getQuestion(int q){

        TriviaNode* n = bottom;
        int i = 0;

        while( i < q)
        {
            if(!n->next)
            {
                return n->next;
            }
            n = n->next;
            i++;
         }
        return n;
    }



};




#define UNIT_TESTING
#ifdef UNIT_TESTING

int askQuestions(TriviaList* T, int questions, bool correct){
if(questions < 1){cout<< "\nWarning - the number of trivia to be asked must be equal to or larger than 1.";return 0;}
if(questions > T->size) { cout<< "\nWarning - There are only " << T->size <<" trivia in the list."; return 0;}
if(T){
TriviaNode* n = T->bottom;
int i = 0;
    while(n && i < questions)
    {

    if(correct) { assert (1==n->ask(true, n->answer)); }
    else {        assert (0 == n->ask(true, (n->answer) + "X"));}

    cout<<"Your total points: " << total_points<<"\n";
    n = n->next;
    i++;
    }
   return 1;
}
else cout<<"\nWarning - the trivia list must not be empty. ";
return 0;

}
TriviaList* HardCode(){

TriviaList* HardCode = new TriviaList();
    HardCode ->addQuestion("Question: How long was the shortest war on record? (Hint: how many minutes)", 100, "38");
    HardCode ->addQuestion("Question: What was Bank of America’s original name? (Hint: Bank of Italy or Bank of Germany)", 50, "Bank of Italy");
    HardCode ->addQuestion("Question: What is the best-selling video game of all time? (Hint: Call of Duty or Wii Sports)?", 20 , "Wii Sports");
return HardCode;
}

int main(){
    TriviaList* testList = HardCode();
    cout<<"\n*** This is a debugging version ***";
    cout<<"\nUnit Test Case 1: Ask no question. The program should give a warning message. ";
    assert(0 == askQuestions(testList, 0, true));;
    cout << "\n\nCase 1 passed\n";

    cout<< "\nUnit Test Case 2.1: Ask 1 question in the linked list. The tester enters an incorrect answer.";
    assert(1==askQuestions(testList, 1, false));
    cout << "\n\nCase 2.1 passed\n";

    cout<< "\nUnit Test Case 2.2: Ask 1 question in the linked list. The tester enters a correct answer.";
    assert(1 == askQuestions(testList, 1, true));
    cout<< "\n\nCase 2.2 passed\n";
    total_points = 0;
    cout<<"\nUnit Test Case 3: Ask all the questions of the last trivia in the linked list.";
    assert(1 == askQuestions(testList, testList->size, true));
    cout<<"\n\nCase 3 passed\n";
    total_points = 0;
    cout<<"\n\nUnit Test Case 4: Ask 5 questions in the linked list.";
    assert(0 == askQuestions(testList, testList->size + 2, true));
    cout<<"\n\nCase 4 passed\n";
    cout<<"\n\n*** End of the Debugging Version ***";

}
#else

TriviaList* inputList() {

TriviaList* inputList = new TriviaList();

bool cont = true;

while (cont) {
string q, a, c;
int p;
cout << "\nEnter a question: ";
cin >> q;
cout<< "\nEnter an answer: ";
cin >> a;
cout<< "\nEnter award points: ";

while(!(cin >> p)){
    cin.clear();
    cin.ignore();
    cout<<"\nInvalid input. Enter integer award points: " ;
}

cout<<"\nContinue? (Yes/No): ";
cin >> c;
if(c!="Yes"){cont = false;}

inputList->addQuestion(q,p,a);
}


return inputList;

}

int main(){
    cout<< "*** Welcome to Mack's trivia quiz game ***";
    TriviaList* newList = inputList();
    TriviaNode* n = newList->bottom;

    while(n)

    {
    n->ask(false, "nullptr");
    cout<<"Your total points: " << total_points<<".\n";
    n = n->next;

    }

    cout<<"*** Thank you for playing the trivia quiz game. Goodbye! ***";



}

#endif

