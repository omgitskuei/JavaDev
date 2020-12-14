package main.java.notes.operators;

//Table of contents
//	Types
//		Arithmetic Operators
//		Relational Operators
//		Bitwise Operators
//		Logical Operators
//		Assignment Operators
//		Misc Operators
//	Precedence


//Types:
//Arithmetic Operators
//	Let A=10, B=20
//	used in mathematical expressions in the same way that they are used in algebra
//		+ (Addition)		Adds values on either side of the operator.	A + B will give 30
//		- (Subtraction)		Subtracts right-hand operand from left-hand operand.	A - B will give -10
//		* (Multiplication)	Multiplies values on either side of the operator.	A * B will give 200
//		/ (Division)		Divides left-hand operand by right-hand operand.	B / A will give 2
//		% (Modulus)			Divides left-hand operand by right-hand operand and returns remainder.	B % A will give 0

//		++ (Increment)		Increases the value of operand by 1.	B++ gives 21
//		-- (Decrement)		Decreases the value of operand by 1.	B-- gives 19

//Relational Operators
//	Let A=10, B=20
//		== (equal to)		(A == B) is false.
//		!= (not equal to)	(A != B) is true.
//		> (greater than)	(A > B) is false.
//		< (less than)		(A < B) is true.
//		>= 					(A >= B) is false.
//		<=					(A <= B) is true.

//Bitwise Operators

//Logical Operators
//	Let A=true and variable B holds false
//		&& (logical and)	(A && B) is false
//		|| (logical or)		(A || B) is true
//		! (logical not)		!(A && B) is true, !(A || B) is false

//Assignment Operators
//		=	Simple assignment operator				C = A + B will assign value of A + B into C
//		+=	Add AND assignment operator				C += A is equivalent to C = C + A
//		-=	Subtract AND assignment operator.		C -= A is equivalent to C = C – A
//		*=	Multiply AND assignment operator.		C *= A is equivalent to C = C * A
//		/=	Divide AND assignment operator.			C /= A is equivalent to C = C / A
//		%=	Modulus AND assignment operator.		C %= A is equivalent to C = C % A

//		<<=	Left shift AND assignment operator.		C <<= 2 is same as C = C << 2
//		>>=	Right shift AND assignment operator.	C >>= 2 is same as C = C >> 2

//		&=	Bitwise AND assignment operator.		C &= 2 is same as C = C & 2
//		^=	bitwise exclusive OR and assignment operator.	C ^= 2 is same as C = C ^ 2
//		|=	bitwise inclusive OR and assignment operator.	C |= 2 is same as C = C | 2

//Misc Operators
//		?: Conditional/Ternary operator			variable x = (expression) ? value if true : value if false
//		instanceof Operator						( Object reference variable ) instanceof  (class/interface type)

//Precedence
//	operators with the highest precedence appear at the top of the table, those with the lowest appear at the bottom.
//	Within an expression, higher precedence operators will be evaluated first.
//		Category		|	Operator												|	Associativity
//		Postfix			|	expression++ expression--								|	Left to right
//		Unary			|	++expression –-expression +expression –expression ~ !	|	Right to left
//		Multiplicative	|	* / %													|	Left to right
//		Additive		|	+ -														|	Left to right
//		Shif			|	<< >> >>>												|	Left to right
//		Relational		|	< > <= >= instanceof									|	Left to right
//		Equality		|	== !=													|	Left to right
//		Bitwise			|	AND	&													|	Left to right
//		Bitwise			|	XOR	^													|	Left to right
//		Bitwise			|	OR	|													|	Left to right
//		Logical			|	AND	&&													|	Left to right
//		Logical			|	OR	||													|	Left to right
//		Conditional		|	?:														|	Right to left
//		Assignment		|	= += -= *= /= %= ^= |= <<= >>= >>>=						|	Right to left
