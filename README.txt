Author: Group <COS30019_A02_T042 >, Carl Wilson <7671822>, Ann Southall <9740961>
Implimented Java code for Inference Engine of propositional logic for Horn-form Knowledge Base.

Features/Bugs/Missing
-===================-
USAGE:
	iEngine.bat <file> <method>

The system takes in an TELL and an ASK.
TELL - A group of Horn clauses sep
erated by semicolons used to build the knowledge base (KB).
ASK - A proposition symbol used to check whether the KB is correct. This is the Query (q).

iEngine takes two arguments.
The first refers to a text file which contains the TELL and ASK.
The second refers to the method that you want to use to determine whether the q can be entailed by the KB in the above file. See Algorithms for a list of valid methods.

Algorithms
Parameter	|Method Name
----------------+----------
TT		|Truth Table
FC		|Forward Chaining
BC		|Back Chaining

Known Bugs:
 - Error handling is limited, invalid knowledge base symbols do not throw error

Missing:
 - TT implimentation is not complete
 - KB must be Horn-form, other KB are not handled

Test Cases:
test1
TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
ASK
d

test2
TELL
p1&p2&p3=> p4; p5&p6 => p4; p1 => p2; p1&p2 => p3; p5&p7 => p6; p1; p4;
ASK
p7

test3
TELL
p=>q;q=>r;p;q;
ASK
r

test4
Tell
a&b&c=>d;d=>z;d&f=>g;g=>e;z=>e;a=>f;z1;z2;a;b;c
ask
e

Truth table checking
Based on test 3
p | q | r | p=>q|q=>r|KB|
0 | 0 | 0 | 1   |1   |1 |
0 | 0 | 1 | 1   |1   |1 |
0 | 1 | 0 | 1   |0   |0 |
0 | 1 | 1 | 1   |1   |1 |
1 | 0 | 0 | 0   |1   |0 |
1 | 0 | 1 | 0   |1   |0 |
1 | 1 | 0 | 1   |0   |0 |
1 | 1 | 1 | 1   |1   |1 |

With the intended output this would produce 4. Our implementation allowed us to get the model of the truth table for each symbol but not for implications.

Forward chaining 
Based on test 3
	r
      /  
     q
    /
   p 
   
Forward chaining looks at the agenda which in this case is q and p and then compares that to the implications which are the clauses.
From this it is determined that the forward chaining path is q -> p -> r.
Backward chaining 
Based on test 3
	r
      /  
     q
    /
   p 
Backward chaining looks at the goal which is r and try to prove the facts so the result is q -> r.

Awknowledgements/Resources:
https://stackoverflow.com/questions/6250114/java-bit-shifting-tutorial
- Demonstarted how to bit shift which was used in TT for assigning the values in the models (each permuntation of sysmbols)
http://codegists.com/snippet/java/ai-forward-chaining-implementation-for-propositional-logic-horn-form-knowledge-bases
- Based FC search of this information and tried to improve the efficiency of it. Tried to implement an iterator but was unsuccessful.
http://codegists.com/snippet/java/ai-backward-chaining-implementation-for-propositional-logic-horn-form-knowledge-bases
- Based BC search of this information and tried to improve the efficiency of it.

Notes:
TT was unable to be implimented, we could not find a way to compare the sentences to the models to valid if they were true

Summary Report:

Contribution
50/50
Carl - 7671822
FC algorithm & BC algorithm.

Team Work
 - A GitHub repository was used so all members could contribute to the code base indepemndantly
 - Members met weekly to discuss progress and changes
