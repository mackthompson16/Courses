class Token
  attr_accessor :type
  attr_accessor :text

  EOF = "eof"
  LPAREN = "("
  RPAREN = ")"
  ADDOP = "+"
  SUBOP = "-"
  MULOP = "*"
  DIVOP = "/"
  WS = "whitespace"
  UNKWN = "unknown"
  EQL = "="
  LT = "<"
  GT= ">"
  ANDOP = "&"
  ID = "id"
  NUM = "int"

  # constructor
  def initialize(type, text)
    @type = type
    @text = text
  end

  def get_type
    return @type
  end

  def get_text
    return @text
  end

  def to_s
    return "#{@type} #{@text}"
  end
end
