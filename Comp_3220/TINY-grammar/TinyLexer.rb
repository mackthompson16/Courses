#
#  Class Lexer - Reads a TINY program and emits tokens
#
class Lexer
  # Constructor - Is passed a file to scan and outputs a token
  #               each time nextToken() is invoked.
  #   @c        - A one character lookahead 




    def initialize(filename)
      # Need to modify this code so that the program
      # doesn't abend if it can't open the file but rather
      # displays an informative message
      @f = File.open(filename,'r:utf-8')
      
      # Go ahead and read in the first character in the source
      # code file (if there is one) so that you can begin
      # lexing the source code file 
      if (! @f.eof?)
        @c = @f.getc()
      else
        @c = "eof"
        @f.close()
      end
    end
    
    # Method nextCh() returns the next character in the file
    def nextCh()
      if (! @f.eof?)
        @c = @f.getc()
      else
        @c = "eof"
      end
      
      return @c
    end
  

    KEYWORDS = {
      "if" => "IF",
      "then" => "THEN",
      "while" => "WHILE",
      "print" => "PRINT"
    }
  
    OPERATORS = {
      "+" => "ADDOP",
      "-" => "SUBOP",
      "*" => "MULOP",
      "/" => "DIVOP",
      "=" => "EQL",
      "<" => "LT",       
      ">" => "GT",       
      "&" => "ANDOP"     
    }

  def nextToken()
  
    if @c == "eof"
      return Token.new(Token::EOF, "eof")

    # Handle whitespaces
    elsif whitespace?(@c)
      str = ""
      while whitespace?(@c)
        str += @c
        nextCh()
      end
      return Token.new(Token::WS, str)

    # Handle numbers (integers)
    elsif numeric?(@c)
      str = ""
      while numeric?(@c)
        str += @c
        nextCh()
      end
      return Token.new(Token::NUM, str)

    # Handle identifiers and keywords
    elsif letter?(@c)
      str = ""
      while letter?(@c)
        str += @c
        nextCh()
 
      end

      # Check if the string is a reserved keyword
      if KEYWORDS.key?(str)

        return Token.new(KEYWORDS[str], str)
      else

        return Token.new(Token::ID, str)

      end

    #check for operators
    elsif OPERATORS.key?(@c)
      str = @c
      nextCh()
      return Token.new(OPERATORS[str], str)
    else
      # Handle unknown tokens
      tok = Token.new(Token::UNKWN, @c)
      nextCh()
      return tok
    end
  end

  # Helper methods for Scanner
#
  def letter?(lookAhead)
    lookAhead =~ /^[a-z]|[A-Z]$/
  end

  def numeric?(lookAhead)
    lookAhead =~ /^(\d)+$/
  end

  def whitespace?(lookAhead)
    lookAhead =~ /^(\s)+$/
  end

end


#  "TINY" Grammar with Boolean
# PGM		 -->   STMTSEQ
# STMTSEQ    -->   STMT+
# STMT       -->   ASSIGN   |   "print"  EXP   | IFSTMT | LOOPSTMT                        
# IFSTMT	 -->   if COMPARISON then STMTSEQ
# LOOPSTMT	 -->   while COMPARISON then STMTSEQ
# COMPARISON -->   FACTOR ( "<" | ">" | "&" ) FACTOR
# ASSIGN     -->   ID  "="  EXP
# EXP        -->   TERM   ETAIL
# ETAIL      -->   "+" TERM   ETAIL  | "-" TERM   ETAIL | EPSILON
# TERM       -->   FACTOR  TTAIL
# TTAIL      -->   "*" FACTOR TTAIL  | "/" FACTOR TTAIL | EPSILON
# FACTOR     -->   "(" EXP ")" | INT | ID   
#                  
# ID         -->   ALPHA+
# ALPHA      -->   a  |  b  | … | z  or 
#                  A  |  B  | … | Z
# INT        -->   DIGIT+
# DIGIT      -->   0  |  1  | …  |  9
# WHITESPACE -->   Ruby Whitespace