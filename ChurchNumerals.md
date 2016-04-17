# Projekt nr 2 - Liczebniki Churcha

### Rachunek lambda
Rachunek lambda - system formalny używany do badania zagadnień związanych z podstawami matematyki jak rekurencja,
definiowalność funkcji, obliczalność, podstawy matematyki np. definicja liczb naturalnych, wartości logicznych, itd.
Rachunek lambda jest przydatny do badania algorytmów. Istnieje wiele rodzajów rachunku lambda, z czego najprostszym jest rachunek lambda bez typów.

### Liczebniki Churcha
Liczebniki Churcha – sposób na reprezentację liczb naturalnych, które w rachunku lambda bez typów należy skonstruować.
Reprezentacja liczby n odbywa się za pomocą funkcji f. Funkcja f wykonana n razy oznacza liczbę n. Liczby naturalne Churcha
to funkcje wyrzszego rzędu, które jako argumenty przyjmują funkcje f oraz x:

```
zero  :=  f(x) = x       -------------------------> lambda f: lambda x: x
one   :=  f(x) = f(x)
two   :=  f(x) = f(f(x))
three :=  f(x) = f(f(f(x)))
four  :=  f(x) = f(f(f(f(x))))
.
.
.
.
n     := f(x) = f^n(x)
```

### Operacje arytmetyczne
Operacje arytmetyczne mogą być reprezntowane przez funkcje na liczbach Churchach, możemy zdefiniować m.in. takie operacje jak następnik, dodawanie, mnożenie, poprzednik, odejmowanie, potęgowanie.

Następnik (inkrementacja) - Liczba n+1 może powstać poprzez wykonanie funkcji f o jeden raz więcej na liczbie n. Definiujemy ja jako następnik (succ).

```
succ  :=  n f(x) = f(n f(x))      ----------------> lambda n: lambda f: lambda x: f(n(f)(x))
```

Otrzymanie kolejnych następników:

```
succ(zero) = f (zero f(x)) = f((f(x) = x) f(x)) = f(x) = f(x) = one
succ(one) = f(one f(x)) = f((f(x) = f(x)) f(x)) = f(f(x)) = two
.
.
```

Dodawanie - aby dodać do siebie dwie liczby m i n nalzey do liczby m zaaplikowac n-krotnie funkcję f lub do liczby n zaaplikowac m-krotnie funkcję f.

```
add   :=  m n f(x) = m f( n f(x)) ---------------> lambda m: lambda n: lambda f: lambda x: m(f)(n(f)(x))
```

Mnożenie - mnozenie dwóch liczb m i n polega na wykonaniu funkcji m*n razy.

```
mult  := m n f(x) = m (n f)(x) ------------------> lambda m: lambda n: lambda f: lambda x: m(n(f))(x)
```

