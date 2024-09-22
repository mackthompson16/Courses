#include <fstream>
#include <iostream>
#include <cstdlib>
#include <string>
//Mack Thompson, JMT0107, 904316394, Filename: project3_Thompson_jmt0107.cpp

const int MAX_SIZE = 100;
int list_of_numbers[MAX_SIZE *2];
int last_index = 0;

using namespace std;

int printFile(bool first){
//new input stream each file.
ifstream instream;
int index = 0;
string filename;
// I use bool first so I don't have to write seperate print statements. 
cout << "Enter the "<< (first? "first " : "second ") << "file name: ";
cin >> filename;

  if(filename.substr(filename.size()-4)!=".txt"){

    filename += ".txt";

  }
  // simple human error prevention(the file must be a txt). 

instream.open(filename);


while(instream.fail()) {
cout << "Input file opening failed.\n Enter new file name: ";
cin >> filename;

instream.clear();
instream.open(filename);

}
//instantiate array of numbers and discover length
//below is my logic for ensuring a valid integer for each line.
int inputArray[MAX_SIZE];

while (!instream.eof()) {
  bool negative = false;
        string out = "";
        string line; 
        //this took me a while to figure out. getline(in,str) prevents me from spliting "- 5" by the space in the middle. 
        getline(instream, line);
        if(line.empty()) { continue;}
        if (line[0] == '-') {
            negative = true;
            line = line.substr(1);  
        }

        for (char a : line) {
            if (isdigit(a)) {
                out += a;
            } else if(a!=' ') {
                out.clear();  
                break;
            }
        }

        if (!out.empty()) {
            int val = std::stoi(out);  
            if (negative) {val = -val;}
            inputArray[index] = val;
            index++;
        }

      
  
}



cout<< "The list of "  << index << " numbers in file " << filename << " is:\n";
for(int i = 0; i < index; i++){
  //add to global list
    list_of_numbers[last_index] = inputArray[i];
    last_index++;
    cout <<inputArray[i]<<"\n";
}

cout<<"\n";
instream.close();
return index;

}

string insertionSort(int arr[], int length) {
  //simple and fast insertion algorithm that we learned in comp2210 with Dean Hendrix.
    int i, key, j;
    for (i = 1; i < length; i++) {
        key = arr[i];
        j = i - 1;

        //Move elements of array that are greater than "key" one position ahead
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }

    // Convert  array to a string based on requirements
    std::string result;
    for (int k = 0; k < length; k++) {
        result += std::to_string(arr[k]);
        if (k < length - 1) {
            result += " ";  
        }
    }

    return result;
}

void output(int length){
    string filename;

  cout<<"\nEnter the output file name: ";
  cin >> filename;
  
  if(filename.substr(filename.size()-4)!=".txt"){

    filename += ".txt";

  }
  ofstream outFile(filename);


//Try to create file for eternity...
  while(outFile.fail()){
    cout<<"Failed to create the file. \n Try a new File Name: ";
    cin >> filename;

    ofstream outFile(filename);
  }
  
  for(int i = 0; i < length; i++){

    outFile<< list_of_numbers[i] <<"\n";

  }
  cout << "\n*** Please check the new file - "<< filename<<" ***";

  //as per requirements but I would rather exclude this line
  outFile.close();

}

int main( )
{
   
    cout<<"***Welcome to Mack's sorting program***\n";
//return type is length. T/F for the order.
    int length  = printFile(true) + printFile(false);
    
    cout<<"The sorted list of " <<length <<" numbers is: " << insertionSort(list_of_numbers, length);

    output(length);

    cout<<"\n***Goodbye.***";



}