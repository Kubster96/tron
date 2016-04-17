"""
Liczebniki Churcha
Jakub Jungiewicz	
"""

#funkcja
function = lambda x: x+1

"""
Liczebniki Churcha - 5 przykladowych, na nich bedziemy operowac
"""
zero=lambda f: lambda x: x
one=lambda f: lambda x: f(x)
two=lambda f: lambda x: f(f(x))
three=lambda f: lambda x: f(f(f(x)))
four=lambda f: lambda x: f(f(f(f(x))))
five=lambda f: lambda x: f(f(f(f(f(x)))))

"""
Operacje arytmetyczne
"""
#nastepnik
def succ(n):
    return lambda f: lambda x: f(n(f)(x))
    
#dodawanie
def add(n,m):
	return lambda f: lambda x: m(f)(n(f)(x))

#mnozenie
def mult(n,m):
	return lambda f: lambda x: n(m(f))(x)

#potegowanie
def exp(n,m):
	return n(m)

#wyonywanie i wypisywanie
def printAndEval(x):
	print x, ' = ', eval(x)

#zamiana church na int
def church_to_int(n):
	return n(function)(0)

#testowanie

printAndEval('church_to_int(five)')
printAndEval('church_to_int(succ(one))')
printAndEval('church_to_int(mult(two, three))')
printAndEval('church_to_int(add(one, four))')
printAndEval('church_to_int(exp(four, two))')
