# Projekt nr 2 - Liczebniki Churcha

Rachunek lambda - system formalny używany do badania zagadnień związanych z podstawami matematyki jak rekurencja,
definiowalność funkcji, obliczalność, podstawy matematyki np. definicja liczb naturalnych, wartości logicznych, itd.
Rachunek lambda jest przydatny do badania algorytmów. Istnieje wiele rodzajów rachunku lambda, z czego najprostszym jest rachunek lambda bez typów.

Liczebniki Churcha – sposób na reprezentację liczb naturalnych, które w rachunku lambda bez typów należy skonstruować.
Reprezentacja liczby n odbywa się za pomocą funkcji f. Funkcja f wykonana n razy oznacza liczbę n. Liczby naturalne Churcha
to funkcje wyrzszego rzędu, które jako argumenty przyjmują funkcje f oraz x:

```
zero  :=  f(x) = x
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


