(*
 * Zadanie domowe 1, czesc 1
 *  structure file
 *)
structure id283102 :> PART_ONE =
struct
  exception NotImplemented
 
  datatype 'a tree= Leaf of 'a | Node of 'a tree * 'a * 'a tree
 
  fun sum n =
    if n = 1 then 1
    else n + sum (n-1)

  fun fac n =
    if n = 1 then 1
    else n * fac (n-1)

  fun fib n =
    if n = 1 then 1
    else if n = 2 then 2
    else fib (n-1) + fib (n-2)

  fun gcd (n, m) =
    if n = m then n
    else if n > m then gcd (n-m, m)
    else gcd (n, m-n)

  fun max (l:int list) =
    case l of
    nil => 0
    | head :: tail =>
        if tail = [] then head
        else if head > max tail then head
        else max tail
 
  fun sumTree (t:int tree) =
    case t of
    Leaf l => l
    | Node (l,v,p) => sumTree (l) + v + sumTree (p) 

  fun depth (t:'a tree) =
    case t of
    Leaf l => 0
    | Node (l,_,p) => 1 + (if depth l > depth p then depth l else depth p);

  fun binSearch (t:int tree) (x:int) = 
	case t of
	Leaf l => x = l
	| Node (l,v,p) =>
		if x = v then true
		else if x > v then binSearch p x
		else binSearch l x

  fun preorder (t:'a tree) = 
	case t of
	Leaf l => [l]
	| Node (l, v, p) => [v] @ preorder l @ preorder p
 
  fun listAdd [] (l:int list) = l
	| listAdd (x:int list) [] = x
	| listAdd (l:int list as hl :: tl) (x:int list as hx :: tx) =
		(hl + hx) :: listAdd tl tx

  fun insert (x:int) [] = [x]
	| insert (x:int) (l:int list as hl :: tl) =
		if x < hl then x :: l
		else hl :: insert x tl	
	  
  fun insort (l:int list) =			
	let
		fun sort [] res = res
		| sort (hl::tl) res = sort tl (insert hl res)
	in
		sort l nil
	end  
 
  fun compose f g  = (fn x => g (f x))
  fun curry f x y = f(x,y) 
  fun uncurry f (x,y) = f x y
  fun multifun f n = 
	if n = 1 then (fn x => f x)
    	else (fn x => f ( (multifun f (n-1)) x ))
 
  fun ltake [] (x:int) = []
	| ltake (l:'a list) 0 = []
	| ltake (l:'a list as h :: t) n = h :: (ltake t (n-1))

  fun lall _ [] = true
	| lall f (l:'a list as h :: t) = if (f h) then (lall f t) else false

  fun lmap _ [] = []
	| lmap f (l:'a list as h :: t) = (f h) :: (lmap f t)

  fun lrev [] = []
	| lrev [x] = [x]
	| lrev (l:'a list as t :: h) = (lrev h) @ [t]

  fun lzip (_,[]) = []
	| lzip ([],_) = []
	| lzip ((ha :: ta),(hb :: tb)) = (ha,hb) :: (lzip(ta,tb))

  fun split [] = ([],[])
	| split [x] = ([x],[])
	| split (x1::x2::t) = let val (x1t,x2t) = split t
                        	in ((x1::x1t),(x2::x2t)) end; 
  fun cartprod _ [] =[]
    |cartprod [] _ = []
    |cartprod (x::xs) ys = (lmap (fn y=> (x,y)) ys) @ (cartprod xs ys)

 
end
