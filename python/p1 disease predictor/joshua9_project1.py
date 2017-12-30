#Joshua Atherton    TCSS 142
fullFileFloat = []
healthyValues = []
sickValues = []
healthyCount = 0
sickCount = 0
index = 0

trainingSet = input('training set: ')
#read input file into a 2 dimensional list
def prepareFile(infileName):
    myFile = []
    infile = open(infileName, 'r')
    for line in infile:
        newLine = line.rstrip()
        splitter = newLine.split(',')
        myFile.append(splitter)
    infile.close()
    return myFile

#get input from the user
fullFile = prepareFile(trainingSet) #read train into a 2d list
# split fullFile list into healthy list and sick list & count groups
for row in fullFile:
    healthyOrSick = float(row[13])
    if healthyOrSick == 0:
        healthyCount += 1
        healthyValues.append(row)
    elif healthyOrSick != 0:
        sickCount += 1
        sickValues.append(row)
    index += 1

print('Total lines processed: {}'.format(healthyCount + sickCount))
print('Total healthy count: {}'.format(healthyCount))
print('Total sicky pooh count: {}'.format(sickCount))

#average lists together adding colums and getting the average
def listAverages(aList):
    totals = [0.00] * 14
    for row in aList:
        for item in range(0, len(row)):
            try:
                totals[item] += float(row[item])
            except ValueError:
                totals[item] += 0
    for i in range(14):
        totals[i] /= len(aList)
    return(totals)

#format input list to two decimal accuracy
def twoDecimalList(listToFormat):
    newList = []
    for item in listToFormat:
        newList.append(float('{:.2f}'.format(item)))
    return newList

#use listAverages function to calculate the averages of lists
avHealthyValues = listAverages(healthyValues)
avSickValues = listAverages(sickValues)
avSickAndHealthy = [avHealthyValues, avSickValues]
sepValue = listAverages(avSickAndHealthy)
sepValues = twoDecimalList(sepValue)

#format and print the results of the patients averages
print('Averages of healthy patients:')
for n in twoDecimalList(avHealthyValues[:13]):
    print(n, end=',')
print()
print('Sicky pooh averages:')
for n in twoDecimalList(avSickValues[:13]):
    print(n, end=',')
print()
print('Seperator Values:')
for n in sepValues[:13]:
    print(n, end=',')
print()

#convert fullfile into float list
for row in fullFile:
    listRow = []
    for item in row:
        try:
            float(item)
        except ValueError:
            item = 0.00
        listRow.append(float(item))
    fullFileFloat.append(listRow)

#compare test list to sepValues to get accuracy
def testAccuracy(listToCheck):
    correctValue = 0
    index = 0
    for row in listToCheck:
        index = 0
        atRisk = 0
        for value in row[:13]:
            if value > sepValues[index]:
                atRisk += 1
            index += 1
        if atRisk > 6 and row[13] > 0:
            correctValue += 1
        elif atRisk <= 6 and row[13] <= 0:
            correctValue += 1
    total = healthyCount + sickCount
    accuracy = (correctValue / total)
    print('Accuracy: {:.2f}'.format(accuracy))

testAccuracy(fullFileFloat) # use test accuracy on fullFile

testSet = input('test set: ')
#Open cleveland file and make into a 2d list. Then compare to sepValues
cleveland = prepareFile(testSet) #turn cleveland into a list
outfileName = input('File name to write output to: ')
outfile = open(outfileName, 'w')
outfile.write('id,disease?\n')
for row in cleveland:
    index = 0
    atrisk = 0
    for value in row[1:]:
        try:
            value = float(value)
        except ValueError:
            value = 0
        if value > sepValues[index]:
            atrisk += 1
        index += 1
    if atrisk > 6:
        outfile.write('{},yes\n'.format(str(row[0])))
    else:
        outfile.write('{},no\n'.format(str(row[0])))
outfile.close()
