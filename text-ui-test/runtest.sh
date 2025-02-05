#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

#delete data file to simulate first run
rm -r ./data

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT 
java -classpath ../bin Bane < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

cp ./data/Bane.txt ACTUAL_DATA.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
diff ACTUAL_DATA.TXT EXPECTED_DATA.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
