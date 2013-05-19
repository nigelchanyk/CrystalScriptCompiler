/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals, 
   because the input is read twice and the methods used to parse
   the numbers are not very fast. 
   For a production quality application (e.g. a Java compiler) 
   this could be optimized */


import java_cup.runtime.*;

%%

%public
%class Scanner
%implements CrystalSymbol

%unicode

%line
%column

%cupsym CrystalSymbol
%cup
%cupdebug

%{
  boolean completed = false;
  StringBuffer string = new StringBuffer();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*
    
/* number literals */        
NumberLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
EscapeCharacters = \\\'|\\\"|\\\\|\\n|\\r|\\t|\\b|\\f

%state STRING

%%

<YYINITIAL> {

  /* keywords */
  "abstract"                     { return symbol(ABSTRACT); }
  "as"							 { return symbol(AS); }
  "array"						 { return symbol(ARRAY); }
  "boolean"                      { return symbol(BOOLEAN); }
  "break"                        { return symbol(BREAK); }
  "case"                         { return symbol(CASE); }
  "catch"                        { return symbol(CATCH); }
  "class"                        { return symbol(CLASS); }
  "continue"                     { return symbol(CONTINUE); }
  "do"                           { return symbol(DO); }
  "def"							 { return symbol(DEF); }
  "else"                         { return symbol(ELSE); }
  "extends"                      { return symbol(EXTENDS); }
  "finally"                      { return symbol(FINALLY); }
  "for"                          { return symbol(FOR); }
  "default"                      { return symbol(DEFAULT); }
  "implements"                   { return symbol(IMPLEMENTS); }
  "import"						 { return symbol(IMPORT); }
  "interface"                    { return symbol(INTERFACE); }
  "new"                          { return symbol(NEW); }
  "number"						 { return symbol(NUMBER); }
  "if"                           { return symbol(IF); }
  "is"							 { return symbol(IS); }
  "isnt"						 { return symbol(ISNT); }
  "object"						 { return symbol(OBJECT); }
  "pass"						 { return symbol(PASS); }
  "public"                       { return symbol(PUBLIC); }
  "string"						 { return symbol(STRING); }
  "super"                        { return symbol(SUPER); }
  "switch"                       { return symbol(SWITCH); }
  "private"                      { return symbol(PRIVATE); }
  "protected"                    { return symbol(PROTECTED); }
  "return"                       { return symbol(RETURN); }
  "static"                       { return symbol(STATIC); }
  "while"                        { return symbol(WHILE); }
  "this"                         { return symbol(THIS); }
  "throw"                        { return symbol(THROW); }
  "try"                          { return symbol(TRY); }
  "var"							 { return symbol(VAR); }
  
  /* boolean literals */
  "true"                         { return symbol(BOOLEAN_LITERAL, new Boolean(true)); }
  "false"                        { return symbol(BOOLEAN_LITERAL, new Boolean(false)); }
  
  /* null literal */
  "null"                         { return symbol(NULL_LITERAL); }
  "undefined"					 { return symbol(UNDEFINED_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
  "{"                            { return symbol(LBRACE); }
  "}"                            { return symbol(RBRACE); }
  "["                            { return symbol(LBRACK); }
  "]"                            { return symbol(RBRACK); }
  ";"                            { return symbol(SEMICOLON); }
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }
  
  /* operators */
  "="                            { return symbol(EQ); }
  ">"                            { return symbol(GT); }
  "<"                            { return symbol(LT); }
  "!"                            { return symbol(NOT); }
  "~"                            { return symbol(COMP); }
  "?"                            { return symbol(QUESTION); }
  ":"                            { return symbol(COLON); }
  "=="                           { return symbol(EQEQ); }
  "<="                           { return symbol(LTEQ); }
  ">="                           { return symbol(GTEQ); }
  "!="                           { return symbol(NOTEQ); }
  "&&"                           { return symbol(ANDAND); }
  "||"                           { return symbol(OROR); }
  "++"                           { return symbol(PLUSPLUS); }
  "--"                           { return symbol(MINUSMINUS); }
  "+"                            { return symbol(PLUS); }
  "-"                            { return symbol(MINUS); }
  "*"                            { return symbol(MULT); }
  "/"                            { return symbol(DIV); }
  "&"                            { return symbol(AND); }
  "|"                            { return symbol(OR); }
  "^"                            { return symbol(XOR); }
  "%"                            { return symbol(MOD); }
  "<<"                           { return symbol(LSHIFT); }
  ">>"                           { return symbol(RSHIFT); }
  ">>>"                          { return symbol(URSHIFT); }
  "+="                           { return symbol(PLUSEQ); }
  "-="                           { return symbol(MINUSEQ); }
  "*="                           { return symbol(MULTEQ); }
  "/="                           { return symbol(DIVEQ); }
  "&="                           { return symbol(ANDEQ); }
  "|="                           { return symbol(OREQ); }
  "^="                           { return symbol(XOREQ); }
  "%="                           { return symbol(MODEQ); }
  "<<="                          { return symbol(LSHIFTEQ); }
  ">>="                          { return symbol(RSHIFTEQ); }
  ">>>="                         { return symbol(URSHIFTEQ); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }
  
  {NumberLiteral}                { return symbol(NUMBER_LITERAL, new Double(yytext())); }
  
  {LineTerminator}				 { return symbol(NEWLINE); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  {EscapeCharacters}+			 { string.append( yytext() ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}+               { /* ignore */ }
}

/* error fallback */
.|\n                             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { if (completed) return symbol(EOF); completed = true; return symbol(ENDOFFILE); }
