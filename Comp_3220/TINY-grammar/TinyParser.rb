class Parser
  def initialize(lexer)
    @lexer = lexer
    @token = @lexer.nextToken()
  end

  # Match and consume the current token
  def match(expected_type)
    if @token.get_type == expected_type
      @token = @lexer.nextToken()
    else
      raise "Syntax error: Expected #{expected_type}, but got #{@token.get_type}"
    end
  end

  # Start parsing the program
  def parse()
    stmtseq()
  end

  # STMTSEQ --> STMT+
  def stmtseq()
    while @token.get_type != Token::EOF
      stmt()
    end
  end

  # STMT --> ASSIGN | "print" EXP | IFSTMT | LOOPSTMT
  def stmt()
    if @token.get_type == "ID"
      assign()
    elsif @token.get_type == "PRINT"
      match("PRINT")
      exp()
    elsif @token.get_type == "IF"
      ifstmt()
    elsif @token.get_type == "WHILE"
      loopstmt()
    else
      raise "Syntax error in statement"
    end
  end

  # ASSIGN --> ID "=" EXP
  def assign()
    match("ID")
    match("EQL")
    exp()
  end

  # IFSTMT --> if COMPARISON then STMTSEQ
  def ifstmt()
    match("IF")
    comparison()
    match("THEN")
    stmtseq()
  end

  # LOOPSTMT --> while COMPARISON then STMTSEQ
  def loopstmt()
    match("WHILE")
    comparison()
    match("THEN")
    stmtseq()
  end

  # COMPARISON --> FACTOR ( "<" | ">" | "&" ) FACTOR
  def comparison()
    factor()
    if @token.get_type == "LT" || @token.get_type == "GT" || @token.get_type == "ANDOP"
      match(@token.get_type)
      factor()
    else
      raise "Syntax error in comparison"
    end
  end

  # EXP --> TERM ETAIL
  def exp()
    term()
    etail()
  end

  # ETAIL --> "+" TERM ETAIL | "-" TERM ETAIL | EPSILON
  def etail()
    if @token.get_type == "ADDOP"
      match("ADDOP")
      term()
      etail()
    elsif @token.get_type == "SUBOP"
      match("SUBOP")
      term()
      etail()
    end
  end

  # TERM --> FACTOR TTAIL
  def term()
    factor()
    ttail()
  end

  # TTAIL --> "*" FACTOR TTAIL | "/" FACTOR TTAIL | EPSILON
  def ttail()
    if @token.get_type == "MULOP"
      match("MULOP")
      factor()
      ttail()
    elsif @token.get_type == "DIVOP"
      match("DIVOP")
      factor()
      ttail()
    end
  end

  # FACTOR --> "(" EXP ")" | INT | ID
  def factor()
    if @token.get_type == "LPAREN"
      match("LPAREN")
      exp()
      match("RPAREN")
    elsif @token.get_type == "INT"
      match("INT")
    elsif @token.get_type == "ID"
      match("ID")
    else
      raise "Syntax error in factor"
    end
  end
end
