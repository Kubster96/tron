##############################################
# Liczebniki Churcha
# Jakub Jungiewicz	
##############################################

# Liczebniki Churcha

def zero(f):
    return lambda x: x

def one(f):
    return lambda x: f(x)

def two(f):
    return lambda x: f(f(x))

# Operacje arytmetyczne

def successor(n):
    return lambda f: lambda x: f(n(f)(x))

def add(n,m):
	return lambda f: lambda x: m(f)(n(f)(x))

def mult(n,m):
	return lambda f: lambda x: n(m(f))(x)

def exp(n,m):
	return n(m)
