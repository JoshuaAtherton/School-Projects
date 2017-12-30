#Joshua Atherton     TCSS 142

napierNumber = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z']
numberList = [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192,
          16384, 32768, 65536, 131072, 262144,524288, 1048576, 2097152,
          4194304, 8388608, 16777216, 33554432 ]

def convertNumberNapier(number): #converts number to napier
    napierList = []
    index = 0
    negativeNumber = False
    if number < 0:
        number = number * -1
        negativeNumber = True
    number = number
    while number != 0:
        newNumber = number // 2
        numberRemainder = number % 2
        letter = numberRemainder * numberList[index]
        if letter != 0:
            napierList.append(napierNumber[index])
        index += 1
        number = newNumber
    joined = ''.join(napierList)
    if len(joined) == 0:
        return ("''")
    elif negativeNumber == True:
        return '-' + joined
    else:
        return joined
    #takes in a number. Handles negative input, result of 0, & converts to napier

def convertNapierNumber(string): #converts Napier to number
    number = 0
    for character in string:
        index = napierNumber.index(character)
        number += numberList[index]
    return number
    #using parallel lists like in ceasar program to convert napier to a number

def enteringNumbers(number, sequence):  #gets user napier number input
    number = input("Enter Napier's number: ").lower()
    while not number.isalpha():
        print('Something is Wrong. Try again.')
        number = input("Enter Napier's number: ").lower()
    print('The {} number is: {:,}'.format(sequence, convertNapierNumber(number)))
    return convertNapierNumber(number)
    #returns the converted napier number to variable that called the function

def arithmeticOperator(num1, num2): #gets operator and adds numbers together
    operator = input('Enter the desired arithmetic operation: ')
    while not operator == '+' and not operator == '-' \
    and not operator == '*' and not operator == '/':
        print('Something is wrong. Try again.')
        operator = input('Enter the desired arithmetic operation: ')
    if operator == '+':
        result = num1 + num2
    elif operator == '-':
        result = num1 - num2
    elif operator == '*':
        result = num1 * num2
    elif operator == '/':
        result = num1 // num2 # int / to eliminates long floating point answers
    print('The result is {:,} or {}'.format(result, convertNumberNapier(result)))

def main():
    #calling the functions to run the program
    runProgram = 'Y'
    while runProgram == 'Y':
        number1 = enteringNumbers('first', 'first')
        number2 = enteringNumbers('second', 'second')
        arithmeticOperator(number1, number2)
        runProgram = input('Do you want to do another conversion? Y/N ').upper()

main()
