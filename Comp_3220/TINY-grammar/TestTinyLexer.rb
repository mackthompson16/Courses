load "./TinyToken.rb"
load "./TinyLexer.rb"

tok = scan.nextToken()


tokenFile = File.open("tokens", "w")

while (tok.get_type() != Token::EOF)
   
   # The instruction below writes your token into a file.
   tokenFile.puts"#{tok}"
   
   # get the next token available (if there is one)
   tok = scan.nextToken()
end 

# There should be one token left (see the boolean condition above)
# putting the last token in the file and closing the file
tokenFile.puts"#{tok}"
tokenFile.close