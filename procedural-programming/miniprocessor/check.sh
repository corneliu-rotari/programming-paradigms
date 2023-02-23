#!/bin/bash

TIMEOUT_VAL=300
RESULT=result.txt

make

timeout ${TIMEOUT_VAL} ./run_tests.sh &> $RESULT

if [ ! $? -eq 0 ]
then
    echo "TIMEOUT. Tests exceeded maximum allowed time of $TIMEOUT_VAL" >> $RESULT
fi

# Run linters
echo -e "\nRun cpplint\n" >> $RESULT
cpplint --filter=-legal/copyright *.c &>> $RESULT

echo -e "\nRun clang-tidy-12\n" >> $RESULT
clang-tidy-12 -header-filter='.*'  -checks='-*,cppcoreguidelines*' *.c -- &>> $RESULT

# Display run summary
awk -f parse.awk $RESULT

# Display detailed report
echo -e "\n==================="
echo -e "= Detailed report ="
echo -e "===================\n"
cat $RESULT

make clean