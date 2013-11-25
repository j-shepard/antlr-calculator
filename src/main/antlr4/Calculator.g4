grammar Calculator;

calculator
  : expression
  ;

expression
  : expression operator=('*'|'/') expression  # MultiplyDivide
  | expression operator=('+'|'-') expression  # AddSubtract
  | '-' expression                            # Negate
  | Number                                    # Number
  | '(' expression ')'                        # Parenthesis
  ;

Number
  : DIGIT+ '.' DIGIT*
  | '.' DIGIT+
  | DIGIT+
  ;

fragment
DIGIT: ('0'..'9');

WS: [ \t\r\n]+ -> skip;
MULTIPLY: '*';
DIVIDE: '/';
ADD: '+';
SUBTRACT: '-';
LEFT: '(';
RIGHT: ')';

