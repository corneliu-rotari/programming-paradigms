#!/bin/bash

make build
for i in $(seq 1 5)
do      
        for j in $(ls input/Task$i/)
        do      
		echo "Task $i---------$j"
		valgrind ./bmp < input/Task$i/$j 
        done    
done    

