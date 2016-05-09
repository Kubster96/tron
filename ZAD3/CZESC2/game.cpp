#include <iostream>
#include <cstdlib>
#include <cstring>
#include <fstream>

using namespace std;

void ClrScr()
{
	printf("\033[2J"); // Czyści ekran
	printf("\033[0;0f"); // Ustawia kursor w lewym, górnym rogu
}

char result(const string & file, char a, char b)
{
	string line;
	fstream plik;
	char c;
	plik.open(file.c_str()); 
    while(getline(plik, line)!=NULL)
    {
		if(line[0]==a and line[2]==b)
		{
			c = line[4];
			return c;
		}
	}
	return ' ';
}
char resultNOT(const string & file, char a)
{
	string line;
	fstream plik;
	char wynik;
	plik.open(file.c_str()); 
    while(getline(plik, line)!=NULL)
    {
		if(line[0]==a)
		{
			wynik = line[2];
			return wynik;
		}
	}
	return ' ';
}

int main(int argc, char **argv)
{
	string OR, NOT, AND, IMPL;
	string arguments[argc+1];
	int wynik = 5;
	char answer;
	char x;
	
	if(argc!=9)
	{
		cout << "Zła ilosc argumentow. Poprawna ilosc to 8" << endl;
		return 0;
	}
		
	for(int i=1;i<argc;i++)
	{
		arguments[i]=argv[i];
	}
	
	for(int i=1; i<argc;i+=2)
	{
		if(arguments[i]=="--not") NOT = arguments[i+1];
		if(arguments[i]=="--or") OR = arguments[i+1];
		if(arguments[i]=="--and") AND = arguments[i+1];
		if(arguments[i]=="--impl") IMPL = arguments[i+1];
	}
	
	//przywitanie gracza
	cout << "Witaj w grze logicznej" << endl;
	cout << "Zasady sa bardzo proste" << endl;
	cout << "Przed Tobą 5 zagadek logicznych" << endl;
	cout << "Pytania zamknięte typu a-b-c" << endl;
	cout << "Jesli odpowiesz dobrze dostajesz 2 pkt" << endl;
	cout << "Jezeli zle to dostajesz -1 pkt" << endl;
	cout << "Na początek dostajesz 5 pkt, a Twoim zadaniem jest uzbierac jak najwiecej" << endl;
	
	
	
	cout << "ARE YOU READY? (Y/N)" << endl;
	cin >> x;
	while(x!='Y' and x!='N' and x!='y' and x!='n')
	{
		cout << "Cos nie tak podaj odpowiedz jeszcze raz (Y/N)" << endl;
		cin >> x;
	}
	
	if(x=='n' or x=='N')
	{
		cout << "Wróć jak bedziesz gotów do gry" << endl;
		return 0;
	}
	
	ClrScr();
	
	cout << "Rozpoczynamy gre :) Twoj wynik to: " << wynik << endl;
	
	//zagadka nr 1
	
	cout << "Zagadka nr I" << endl;
	cout << "Marek ma dom" << endl;
	cout << "Czy z tego faktu wynika ze Marcin tez ma dom lub" << endl;
	cout << "czy z tego że Marcin posiada dom wynika to że " << endl;
	cout << "Marek go posiada?" << endl;
	cout << "1) tak" << endl;
	cout << "0) nie" << endl;
	cout << "X) nie wiadomo" << endl;
	cin >> answer;
	char Marek = '1';
	char Marcin = 'X';
	
	if(answer==result(OR, result(IMPL, Marek, Marcin), result(IMPL, Marcin, Marek))) wynik +=2;
	else wynik--;
	
	ClrScr();
	
	cout << "Twoj wynik to: " << wynik << endl;
	
	//zagadka nr 2
	cout << "Zagadka nr II" << endl;
	cout << "Cersei jest żoną Roberta" << endl;
	cout << "Khal Drogo jest mężem Daenerys" << endl;
	cout << "Cersei rządzi królewską przystanią" << endl;
	cout << "Daenerys nie ma żadnej władzy" << endl;
	cout << "Khal Drogo i Robert nie żyją" << endl;
	cout << "Czy prawdą jest że w kazdym małżeństwie jest władca/władczyni" << endl;
	cout << "1) tak" << endl;
	cout << "0) nie" << endl;
	cout << "X) nie wiadomo" << endl;
	cin >> answer;
	char C = '1';
	char R = 'X';
	char KD = 'X';
	char D = '0';
	
	if(answer==result(AND, result(OR, C, R), result(OR, KD, D))) wynik +=2;
	else wynik--;
	
	ClrScr();
	//zagadka nr 3
	cout << "Twoj wynik to: " << wynik << endl;
	cout << "Zagadka nr III" << endl;
	cout << "Kobiety często rozpoczynają kłótnie" << endl;
	cout << "Czy z tego wynika że brak kobiety to brak kłótni?" << endl;
	cout << "1) tak" << endl;
	cout << "0) nie" << endl;
	cout << "X) nie wiadomo" << endl;
	cin >> answer;
	char kobieta = '1';
	char klotnia = '1';
	
	
	if(answer==result(IMPL, result(IMPL, kobieta, klotnia), result(IMPL, resultNOT(NOT, kobieta), resultNOT(NOT, klotnia)))) wynik +=2; 
	else wynik--;
	
	ClrScr();
	//zagadka nr 4
	cout << "Twoj wynik to: " << wynik << endl;
	cout << "Zagadka nr IV" << endl;
	cout << "Wszyscy bracia Kasi maja tylko jedna siostre" << endl;
	cout << "Natomiast Ala ma 3 braci" << endl;
	cout << "Czy obie dziewczynki maja siostre?" << endl;
	cout << "1) tak" << endl;
	cout << "0) nie" << endl;
	cout << "X) nie wiadomo" << endl;
	cin >> answer;
	char Maja = '0';
	
	char Ala = 'X';
	
	if(answer==result(AND, Maja, Ala)) wynik +=2;
	else wynik--;
	
	ClrScr();
	//zagadka nr 5
	cout << "Twoj wynik to: " << wynik << endl;
	cout << "Zagadka nr V" << endl;
	cout << "Michał lubi koty, a Antek nie lubi" << endl;
	cout << "Monika nigdy nie miała kota" << endl;
	cout << "Czy mozna powiedziec, ze albo Antek lub Monika lubią koty" << endl;
	cout << "albo, że nie prawda jest ze Antek i Michał lubią koty?" << endl;
	cout << "1) tak" << endl;
	cout << "0) nie" << endl;
	cout << "X) nie wiadomo" << endl;
	cin >> answer;
	char Mi = '1';
	char Mo = 'X';
	char A = '0';
	
	if(answer==result(OR, result(OR, A, Mo), resultNOT(NOT, result(AND, A, Mi)))) wynik +=2;
	else wynik--;
	
	
	ClrScr();
	cout << "Quiz zakończony!" << endl;
	cout << "Twoj wynik to: " << wynik << endl;
	return 0;
}
