grammar ZLang;

program :   method+ EOF;

method  :   variable ('(' variable* ')')* '{' stmnt* '}';

stmnt   :
        (
                ifStmt
            |   whileStmt
            |   forStmt
            |   assignStmt
            |   printStmt
        )
        END
        ;

ifStmt      :   IF;
whileStmt   :   WHILE;
forStmt     :   FOR;
assignStmt  :   (q+=variable ASSIGN)+ expression;
printStmt   :   PRINT (q+=expression)*;


expression  :   NUMBER              #nNUMBER
            |   variable            #nVariable
            |   string              #nString
            |   expression op=(ADD | SUB)                           expression  #opExpression
            |   expression op=(MUL | DIV)                           expression  #opExpression
            |   expression op=(ORR | AND)                           expression  #opExpression
            |   expression op=(MEQ | LEQ | MOR | LES | EQU | NEQ)   expression  #opExpression
            ;

//LEXER
PRINT   :   'print';
END     :   ';';
NOP     :   'nop';
IF      :   'if';
ELSE    :   'else';
WHILE   :   'while';
FOR     :   'for';
ASSIGN  :   '=';
ADD     :   '+';
SUB     :   '-';
DIV     :   '/';
MUL     :   '*';
MEQ     :   '>=';
LEQ     :   '<=';
MOR     :   '>';
LES     :   '<';
EQU     :   '==';
NEQ     :   '!=';
ORR     :   '||';
AND     :   '&&';

string      :   '"' WORD '"';
variable    :   WORD;



NUMBER  :   INTTYPE | DOUBLETYPE;
WORD    :   LETTER (DIGIT | LETTER)* ;

fragment    DOUBLETYPE      :   DIGIT+ '.' DIGIT+;
fragment    INTTYPE         :   DIGIT+;
fragment    LETTER          :   [a-zA-Z];
fragment    DIGIT           :   [0-9];


WS              :   [ \t\r\n\u000C]+ -> skip;
COMMENT         :   '/*' .*? '*/' -> skip;
LINE_COMMENT    :   '//' ~[\r\n]* -> skip;




//lexer grammar Strings;
//LQUOTE : '"' -> more, mode(STR) ;
//WS : [ \r\t\n]+ -> skip ;
//mode STR;
//STRING : '"' -> mode(DEFAULT_MODE) ; // token we want parser to see
//TEXT : . -> more ; // collect more text for string


//command-names: skip,  more,  popMode,  mode( x ),  pushMode( x ), type( x ), channel( x )
//TokenName : «alternative» -> command-name1 («identifier or integer»), command-name2, command-name3

//you can set those manually : _type, _text, _channel, _tokenStartCharIndex, _tokenStartLine, and _tokenStartCharPositionInLine
//ENUM : 'enum' {if (!enumIsKeyword) setType(Identifier);} ;

//like, if you are inside braces you use different methods of pasing, than outside, so there are MODES:
//rules in default mode
//...
//mode MODE1;
//rules in MODE1
//...
//mode MODEN;
//rules in MODEN
//...


//https://github.com/antlr/antlr4/blob/master/doc/actions.md -> Dynamically-Scoped Attributes for functions

// stat: 'if' expr 'then' stat (el='else' stat)?
//    {if ( $el!=null ) System.out.println("found an else");}
//    | ...
//    ;


//stat: r='return' expression ';' {System.out.println("line="+$r.line);} ;

//decl  : type ID       ';'     {System.out.println("var "+$ID.text+":"+$type.text+";");}
//      | t=ID id=ID    ';'     {System.out.println("var "+$id.text+":"+$t.text+";");}
//    ;


//e : e '*' e # BinaryOp
//    | e '+' e # BinaryOp
//    | INT # Int
//    ;

//array : '{' el+=INT (',' el+=INT)* '}' ;

//add[int x] returns [int result] : '+=' INT {$result = $x + $INT.int;} ;

///** Derived from rule "row : field (',' field)* '\r'? '\n' ;" */
//row[String[] columns]
//   returns [Map<String,String> values]
//   locals [int col=0]
//    @init {
//    $values = new HashMap<String,String>();
//    }
//    @after {
//    if ($values!=null && $values.size()>0) {
//    System.out.println("values = "+$values);
//    }
//    }
//    : ...
//    ;

//a[Map<String,String> x, int y] : ... ;



//keywords
//import, fragment, lexer, parser, grammar, returns,
//locals, throws, catch, finally, mode, options, tokens