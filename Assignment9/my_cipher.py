import string

#appends characters of comparison string that are not already in the base string to the base string
#function will also ignore spaces and non-letters in the base string
def appendNonDuplicate(baseStr, compStr):
    for i in compStr:
        isDuplicate = False
        for j in baseStr:
            if i == j:
                isDuplicate = True
    
        if not isDuplicate and not i.isspace() and i.isalpha():
            baseStr += i
            
    return baseStr

alphabet = string.ascii_uppercase

#get keyword and print out known info
keyword = input("Provide the keyword: ").upper()
print("Keyword: " + keyword)
print("Alphabet:\t\t" + alphabet)

#create simplified key with no repeated letters
simpleKey = appendNonDuplicate("", keyword)

#remove letters from alphabet and create and print cipher
cipher = appendNonDuplicate(simpleKey, alphabet)
print("Transformed Alphabet:\t" + cipher)

#open and read file in read and append mode
file = open("message.txt", "r+")
text = file.read().upper()
encrypted = "\n";

for c in text:
    if c.isalpha():
        #doing it like a hash to cut down on loops
        #ASCII value of 'A' = 65
        #alphabet[0] = A so alphabet[ord(char) - 65] = char
        #therefore cipher[ord(char) - 65] = transformed char
        encrypted += cipher[ord(c) - 65]
    else:
        #account for symbols and spaces
        encrypted += c

#add the encrypted text to the end of the file
file.write(encrypted)

file.close()
    
