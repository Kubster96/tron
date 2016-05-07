#include <iostream>
#include <cstdlib>

using namespace std;

int main(int argc, char **argv)
{
	int a,b,c,d,e,f,g;
	
	if(argc!=8)
	{
		cout << "Błąd wywołania programu! Proszę podać 7 argumentów (liczby całkowite)" << endl;
		return 0;
	}
	
	a = atoi(argv[1]);
	b = atoi(argv[2]);
	c = atoi(argv[3]);
	d = atoi(argv[4]);
	e = atoi(argv[5]);
	f = atoi(argv[6]);
	g = atoi(argv[7]);
	
	if((a>0 and (b%c==0 or c%d ==0)) and (e<5 and ((f+g)>4 or f==4 or g==4))) cout << 1 << endl;
	else cout << 0 << endl;
	
	return 0;
}
