class Solution:
    
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        indexes = [0 for i in range(len(lists))]
        out = []
        total_length = sum(len(lists[i]) for i in range(len(lists)))
        while len(out) < total_length:

                current = min(lists[i][indexes[i]].val for i in range(len(lists)))
                out.append(current)
                for i in range(len(indexes)):
                    if lists[i][indexes[i]].val == current:
                        indexes[i] +=1
               
        return out



    def isSumEqual(self, firstWord: str, secondWord: str, targetWord: str) -> bool:
        return sum(list(ord(i) for i in firstWord)) + sum(list(ord(i) for i in secondWord)) == sum(list(ord(i) for i in targetWord))

def checkForLine(rMove, cMove, i , j , color, board: list[list[str]])-> bool:
        
        nbr = 0
    
        while (nbr == 0 or current != "."):
            x = cMove+j*nbr
            y = rMove+i*nbr

            if(x<0 or x>7 or y<0 or y>7):
                return False

            current  = board[y][x]
            if current == color and nbr != 0:
                return nbr > 1
            else:
                nbr +=1
               
        return False
   
class Solution:
    
    def checkMove(self, board: list[list[str]], rMove: int, cMove: int, color: str) -> bool:
    
        neighbors = [(-1,-1), (-1,0), (-1,1),(0,-1),(0,1),(1,-1), (1,0), (1,1)]
    
        for n in neighbors:
            if checkForLine(rMove,cMove,n[0],n[1],color, board):
                return True
        return False

