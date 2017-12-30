#Joshua Atherton  TCSS 142
import math
def main():
    #takes csv file as input and splits it into lists
    def readFile(fileToRead):
        fileToRead.readline()
        for line in fileToRead:
            lineList = line.split(',')
            lnameList.append(lineList[0])
            fnameList.append(lineList[1])
            average = (int(lineList[2]) + int(lineList[3])) / 2
            average = math.ceil(average)
            gradeAve.append(average)
            if average >= 90:
                gradeRanges[4] += 1
            elif average >= 80:
                gradeRanges[3] += 1
            elif average >= 70:
                gradeRanges[2] += 1
            elif average >= 60:
                gradeRanges[1] += 1
            elif average >= 0:
                gradeRanges[0] += 1
    #takes file as input and writes results to csv file
    def writeFile(writeTo):
        pos = 0
        for el in lnameList:
            writeTo.write(el + ',')
            writeTo.write(fnameList[pos] + ',')
            writeTo.write(str(gradeAve[pos]) + '\n')
            pos += 1
    #takes gradeAve list and prints top students names and grade to screen
    def topScorers(topGrades):
        print("top scorers")
        hi = max(topGrades) - 5
        c = 0
        for el in topGrades:
            if el >= hi:
                print(lnameList[c], fnameList[c], el, sep = ', ')
            c += 1
    #Takes gradeRanges list and prints the grade ranges of students
    def ranges(grades):
        print("grade ranges: ")
        lowBound = 0
        for el in grades:
            print(str(el), "students with grades >= ", lowBound)
            if lowBound == 0:
                lowBound += 60
            else:
                lowBound += 10

    ifilevar = open('grades.csv', 'r')
    ofilevar = open('examScores.csv', 'w')
    gradeRanges = [0] * 5
    lnameList = []
    fnameList = []
    gradeAve = []

    readFile(ifilevar)    #reads infile into lists
    ifilevar.close()
    writeFile(ofilevar)   #takes outfile as input and writes results
    ofilevar.close()
    topScorers(gradeAve)  #takes list and prints students with top grades
    ranges(gradeRanges)   #takes list and prints the grade ranges

main()
