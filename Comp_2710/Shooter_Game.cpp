#include <random> 
#include <iostream>
#include <time.h>
#include <assert.h>
using namespace std;

class shooter{

    public:

    bool alive = true;
    int probability;
    float wins = 0;
    bool hit = false;
    
    shooter(int P) : probability(P) {}

    void random() {hit = (rand()%probability ==0);}

    void reset(){alive = true;}

    void win(){wins++;}

    void kill(){alive = false;}

    void clear(){wins = 0;}
    };

shooter Aaron(3);
shooter Bob(2);
shooter Charlie(1);

void Reset()
{
Bob.reset();
Aaron.reset();
Charlie.reset();
}

void Shoot(shooter& S, shooter& T)
{
    S.random();

    if(S.hit) {T.kill();}

    S.hit = false;
}

void Strategy(int N)
{
Aaron.clear(); Bob.clear(); Charlie.clear();

for(int i = 0; i < 10000; i++)
{
Reset();

 if(N == 2){Aaron.random();}

while (Aaron.alive + Bob.alive + Charlie.alive > 1) 
    {
        if(Aaron.alive) Shoot(Aaron,Charlie.alive ? Charlie : Bob);
        if(Bob.alive) Shoot(Bob,Charlie.alive ? Charlie : Aaron);
        if(Charlie.alive) Shoot(Charlie,Bob.alive ? Bob : Aaron);
    }
    
if(Aaron.alive) Aaron.win();
if(Charlie.alive) Charlie.win();
if(Bob.alive) Bob.win();
}


    
cout<<"Aaron won "<<Aaron.wins<<"/10,000 duels or "<<Aaron.wins/100<<"%\n";
cout<<"Charlie won "<<Charlie.wins<<"/10,000 duels or "<<Charlie.wins/100<<"%\n";
cout<<"Bob won "<<Bob.wins<<"/10,000 duels or "<<Bob.wins/100<<"%\n";
}

int main(){

    srand(time(0));
cout<<"\n***Welcome To Mack's Duel Simulator***\n";

cout<<"\nStrategy One:\n\n";
    Strategy(1);

cout<<"\nStrategy Two:\n\n";
    Strategy(2);
    return 0;
    
    }
/*

I'm going to write the required functions but I can't bare to use them because 
Creating a class is just such a more effcient and less redudant way of
Implementing the Problem

*/



bool at_least_two_alive(bool A_alive, bool B_alive,bool C_alive)

{
 return(A_alive + B_alive +  C_alive > 1); 
}

void Aaron_shoots1(bool& B_alive, bool& C_alive)
{
Aaron.random();
if(Aaron.alive) Shoot(Aaron,Charlie.alive ? Charlie : Bob);

}
void Bob_shoots(bool& B_alive, bool& C_alive)
{
Bob.random();
 if(Bob.alive) Shoot(Bob,Charlie.alive ? Charlie : Aaron);

}
void Charlie_shoots(bool& B_alive, bool& C_alive)
{
Charlie.random();
if(Charlie.alive) Shoot(Charlie,Bob.alive ? Bob : Aaron);

}
void Aaron_shoots2(bool& B_alive, bool& C_alive){

    Aaron.random();
    Aaron.random();

if(Aaron.alive) Shoot(Aaron,Charlie.alive ? Charlie : Bob);
}


void test_at_least_two_alive(void) {
cout << "Unit Testing 1: Function - at_least_two_alive()\n";
cout << "Case 1: Aaron alive, Bob alive, Charlie alive\n";
assert(true == at_least_two_alive(true, true, true));
cout << "Case passed ...\n";
cout << "Case 2: Aaron dead, Bob alive, Charlie alive\n";
assert(true == at_least_two_alive(false, true, true));
cout <<"Case passed ...\n";
cout<<"Case 3: Aaron alive, Bob dead, Charlie alive\n";
assert(true == at_least_two_alive(true, false, true));
cout<<"Case passed ...\n";
cout<<"Case 4: Aaron alive, Bob alive, Charlie dead\n";
assert(true == at_least_two_alive(true, false, false));
cout<<"Case passed ...\n";
cout<<"Case 5: Aaron dead, Bob dead, Charlie alive\n";
assert(true == at_least_two_alive(false, false, true));
cout<<"Case passed ...\n";
cout<<"Case 6: Aaron dead, Bob alive, Charlie dead\n";
assert(true == at_least_two_alive(false, true, false));
cout<<"Case passed ...\n";
cout<<"Case 7: Aaron alive, Bob dead, Charlie dead\n";
assert(true == at_least_two_alive(true, false, false));
cout<<"Case passed ...\n";
cout<<"Case 8: Aaron dead, Bob dead, Charlie dead\n";
assert(true == at_least_two_alive(true, false, false));
cout<<"Case passed ...\n";
}
void test_Aaron_shoots1(void) {



}